package com.clouway.networkingandgui.clientserver.serverclient_tdd_v_3;

import org.jmock.Expectations;
import org.jmock.States;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientTest {

  class FakeServer {

    private Boolean isStarted = false;

    private Socket socket;
    private PrintStream printStream;
    private List<Socket> socketList = new ArrayList<>();
    private ServerSocket server;

    protected void start(Integer port) {
      isStarted = true;
      try {
        server = new ServerSocket(port);

        new Thread(new Runnable() {
          @Override
          public void run() {
            while (isStarted) {
              try {
                socket = server.accept();
                printStream = new PrintStream(socket.getOutputStream());
                printStream.println("Hello " + socket.getInetAddress() + "\n");
                printStream.flush();

                socketList.add(socket);

              } catch (IOException e) {
                e.printStackTrace();
              }
            }
            try {
              server.close();
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }).start();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    protected void stop() {
      isStarted=false;
      for (Socket x : socketList) {
        try {
          printStream = new PrintStream(x.getOutputStream());
          printStream.println(" Server is gone fishing."+"\n");
          printStream.flush();

        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

  }

  private FakeServer fakeServer;
  private Screen screen;
  public Synchroniser synchroniser = new Synchroniser();

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery() {{
    setThreadingPolicy(synchroniser);
  }};

  @Before
  public void setUp() throws Exception {
    fakeServer = new FakeServer();
    fakeServer.start(8081);
    screen = context.mock(Screen.class);
  }

  @After
  public void tearDown() throws Exception {
    fakeServer.stop();
  }

  @Test
  public void messages() throws Exception {
    Client client = new Client(screen);
    final States newState=context.states("connecting");
    context.checking(new Expectations() {{
      oneOf(screen).display("Hello /172.16.188.17");
      then(newState.is("connected"));
      oneOf(screen).display(" Server is gone fishing.");
      then(newState.is("fakeServer stop"));
    }});
    client.connect("172.16.188.17", 8081);
    synchroniser.waitUntil(newState.is("connected"));
    fakeServer.stop();
   synchroniser.waitUntil(newState.is("fakeServer stop"));
  }

}