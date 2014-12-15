package com.clouway.networkingandgui.clientinformation.clientinformation_v_1.server;

import com.clouway.networkingandgui.clientinformation.clientinformation_v_1.Screen;
import com.clouway.networkingandgui.clientinformation.clientinformation_v_1.Server;
import com.clouway.networkingandgui.clientinformation.clientinformation_v_1.ServerAlreadyRunningException;
import org.jmock.Expectations;
import org.jmock.States;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;

public class ServerTest {

  private Synchroniser synchroniser = new Synchroniser();
  private Server server;
  private Screen screen;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery() {{
    setThreadingPolicy(synchroniser);
  }};
  private States connecting;

  @Before
  public void setUp() throws Exception {
    screen = context.mock(Screen.class);
    server = new Server(8080, screen);
    connecting = context.states("connecting");
  }

  @After
  public void tearDown() throws Exception {
    server.stop();
  }

  @Test
  public void hapyPath() throws Exception {
    final States connecting = context.states("waiting for client");
    FakeClient fakeClient = new FakeClient("172.16.188.17", 8080);
    context.checking(new Expectations() {{
      oneOf(screen).display("Client :/172.16.188.17 just joined.");
      then(connecting.is("client connected"));
      allowing(screen).display(with(any(String.class)));
    }});
    server.openConnection();
    fakeClient.connect();
    synchroniser.waitUntil(connecting.is("client connected"));
  }

  @Test
  public void multipleClients() throws Exception {
    final States connecting = this.connecting;
    FakeClient fakeClient = new FakeClient("172.16.188.17", 8080);
    context.checking(new Expectations() {{
      oneOf(screen).display("Client :/172.16.188.17 just joined.");
      then(connecting.is("client connected1"));
      oneOf(screen).display("Client :/172.16.188.17 just joined.");
      then(connecting.is("client connected2"));
      allowing(screen).display(with(any(String.class)));
    }});
    server.openConnection();
    fakeClient.connect();
    synchroniser.waitUntil(connecting.is("client connected1"));
    fakeClient.connect();
    synchroniser.waitUntil(connecting.is("client connected2"));
  }

  @Test(expected = ServerAlreadyRunningException.class)
  public  void alreadyRunning() throws Exception {
    final States state = connecting;
    FakeClient fakeClient = new FakeClient("172.16.188.17", 8080);
    context.checking(new Expectations() {{
      oneOf(screen).display("Client :/172.16.188.17 just joined.");
      then(state.is("client connected"));
      allowing(screen).display("Offline!");
    }});
    server.openConnection();
    fakeClient.connect();
    synchroniser.waitUntil(state.is("client connected"));
    server.openConnection();
  }

  @Test
  public void stop() throws Exception {
    final States state = connecting;
    context.checking(new Expectations() {{
      oneOf(screen).display("Offline!");
      then(state.is("server stop!"));
      allowing(screen).display("Offline!");
    }});
    server.openConnection();
    server.stop();
    synchroniser.waitUntil(state.is("server stop!"));

  }

  @Test
  public void response() throws Exception {
    final States state = connecting;
    FakeClient fakeClient = new FakeClient("172.16.188.17", 8080);
    context.checking(new Expectations() {{
      oneOf(screen).display("Client :/172.16.188.17 just joined.");
      then(state.is("client connected"));
      oneOf(screen).display("Client :/172.16.188.17 just joined.");
      oneOf(screen).display("Response send.");
      then(state.is("response send"));
      allowing(screen).display("Offline!");
    }});
    server.openConnection();
    fakeClient.connect();
    synchroniser.waitUntil(state.is("client connected"));
    fakeClient.connect();
    synchroniser.waitUntil(state.is("response send"));
  }

  class FakeClient {

    private final String host;
    private final int port;
    private boolean isAlive = false;

    private FakeClient(String host, int port) {
      this.host = host;
      this.port = port;

    }

    private void connect() {
      try {
        final Socket socket = new Socket(host, port);
        isAlive = true;

        new Thread(new Runnable() {
          @Override
          public void run() {
            while (isAlive) {

            }
          }
        }).start();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}