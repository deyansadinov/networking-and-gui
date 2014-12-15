package com.clouway.networkingandgui.clientserver.clientserver_v_4_with_tests;


import com.clouway.networkingandgui.clientserver.clientserver_v_4_with_tests.customexceptions.ServerAlreadyRunningException;
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

  private Screen screen;
  private TheServer server;
  private FakeClient client;

  protected Synchroniser synchroniser = new Synchroniser();
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery() {{
    setThreadingPolicy(synchroniser);
  }};

  @Before
  public  void setUp() throws Exception {

    screen = context.mock(Screen.class);
    server = new TheServer(8080, screen);
    client = new FakeClient("172.16.188.17", 8080, screen);
  }

  @After
  public  void tearDown() throws Exception {
    if (!(server == null)) {
      server.stop();
    }

  }

  @Test
  public  void happyPath() throws Exception {
    final States conection = context.states("searching");
    context.checking(new Expectations() {{
      oneOf(screen).display("/172.16.188.17");
      then(conection.is("new client is connected"));
      allowing(screen).display(with(any(String.class)));
    }});
    server.openConnection();
    client.connect();
    server.stop();
    synchroniser.waitUntil(conection.is("new client is connected"));
  }

  @Test
  public  void multipleClients() throws Exception {
    FakeClient client2 = new FakeClient("172.16.188.17", 8080, screen);
    final States conection = context.states("connecting");
    context.checking(new Expectations() {{
      oneOf(screen).display("/172.16.188.17");
      oneOf(screen).display("/172.16.188.17");

      allowing(screen).display(with(any(String.class)));
      then(conection.is("ok"));
      //allowing(screen).display(with(any(String.class)));
    }});
    server.openConnection();
    client2.connect();
    client.connect();
    //synchroniser.waitUntil(conection.is("ok1"));
    //TO REMOVE JUST FOR TESTING
    //Thread.sleep(10);
    server.stop();
    synchroniser.waitUntil(conection.is("ok"));
  }


  @Test
  public  void offline() throws Exception {
    context.checking(new Expectations() {{
      oneOf(screen).display("Offline!");
    }});
    server.openConnection();
  }

  @Test(expected = ServerAlreadyRunningException.class)
  public  void alreadyRunning() throws Exception {
    final States state = context.states("connecting");
    context.checking(new Expectations() {{
      oneOf(screen).display("/172.16.188.17");
      then(state.is("client connected"));
      allowing(screen).display("Offline!");
    }});
    server.openConnection();
    client.connect();
    server.openConnection();
    synchroniser.waitUntil(state.is("client connected"));
  }


  class  FakeClient {

    private final String host;
    private final int port;
    private Screen screen;
    private boolean isAlive = false;

    private FakeClient(String host, int port, Screen screen) {
      this.host = host;
      this.port = port;
      this.screen = screen;
    }

    private void connect() {
      try {
        final Socket socket = new Socket(host, port);
        isAlive = true;

        new Thread(new Runnable() {
          @Override
          public void run() {
            while (isAlive) {
//              try {
//                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
//                BufferedReader reader = new BufferedReader(inputStreamReader);
//                String message = reader.readLine();
//                screen.display(message);
//                if (reader.readLine() == null) {
//                  socket.setSoLinger(true, 0);
//                  isAlive = false;
//                  socket.close();
//                }
//              } catch (IOException e) {
//                e.printStackTrace();
//              }
            }
          }
        }).start();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}