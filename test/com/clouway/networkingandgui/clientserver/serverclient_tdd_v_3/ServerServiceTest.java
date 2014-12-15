package com.clouway.networkingandgui.clientserver.serverclient_tdd_v_3;

import com.clouway.networkingandgui.clientserver.serverclient_tdd_v_3.customexceptions.ServerAlreadyRunningException;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 9:19 Dec 14-12-10
 */
public class ServerServiceTest {

  public Server server;
  public Screen screen;
  public Synchroniser synchroniser = new Synchroniser();

  class FakeClient {
    private final String host;
    private final int port;
    private Socket socket;

    FakeClient(String host, int port) {
      this.host = host;
      this.port = port;
    }

    public void connect() {
      try {
        socket = new Socket(host, port);
      } catch (IOException e) {
      }
    }

    public String getClientHost() {
      return this.host;
    }

    public boolean isConnected() {
      if (socket.isConnected()) {
        return true;
      }
      return false;
    }
  }

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery() {{
    setThreadingPolicy(synchroniser);
  }};
  @Before
  public void setUp() throws Exception {
    screen = context.mock(Screen.class);
    server = new Server(8080, screen);

  }
  @After
  public void tearDown() throws Exception {
    server.stop();

  }

  @Test
  public void happyPath() throws Exception {
    final States conection = context.states("searching");
    FakeClient fakeClient = new FakeClient("172.16.188.17", 8080);
    context.checking(new Expectations() {{
      oneOf(screen).display("/172.16.188.17");
      then(conection.is("ok"));
      allowing(screen).display(with(any(String.class)));
    }});
    server.openConnection();
    fakeClient.connect();
    synchroniser.waitUntil(conection.is("ok"));
  }

  @Test(expected = ServerAlreadyRunningException.class)
  public void startServerTwice() throws Exception {
    server.openConnection();
    server.openConnection();
  }

  @Test(timeout = 100)
  public void multipleClients() throws Exception {
    final States  searchStates=context.states("clientLogIn");
    FakeClient fakeClient = new FakeClient("172.16.188.17", 8080);
    FakeClient fakeClient2 = new FakeClient("172.16.188.17", 8080);

    context.checking(new Expectations() {{
      exactly(2).of(screen).display("/172.16.188.17");
      then(searchStates.is("clientLogIn"));
    }});
    server.openConnection();
    fakeClient.connect();
    fakeClient2.connect();
    assertThat(fakeClient.isConnected(), is(true));
    assertThat(fakeClient2.isConnected(), is(true));
    synchroniser.waitUntil(searchStates.is("clientLogIn"));

  }

  @Test(timeout = 100)
  public void updateClientsListOnClientLogIn() throws Exception {
    final States  searchStates=context.states("clientLogIn");
    final FakeClient fakeClient = new FakeClient("172.16.188.17", 8080);

    context.checking(new Expectations() {{
      oneOf(screen).display("/" + fakeClient.getClientHost());
      then(searchStates.is("clientLogIn"));
    }});
    server.openConnection();
    fakeClient.connect();
    synchroniser.waitUntil(searchStates.is("clientLogIn"));
  }

}
