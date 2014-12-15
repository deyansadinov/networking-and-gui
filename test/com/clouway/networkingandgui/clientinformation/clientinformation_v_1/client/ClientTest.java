package com.clouway.networkingandgui.clientinformation.clientinformation_v_1.client;


import com.clouway.networkingandgui.clientinformation.clientinformation_v_1.Client;
import com.clouway.networkingandgui.clientinformation.clientinformation_v_1.Screen;
import org.jmock.Expectations;
import org.jmock.States;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ClientTest {

  private Synchroniser synchroniser = new Synchroniser();
  private FakeServer fakeServer;
  private Client client;
  private Screen screen;
  private String host="172.16.188.17";
  private int port=9080;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery() {{
    setThreadingPolicy(synchroniser);
  }};

  @Before
  public void setUp() throws Exception {
    screen = context.mock(Screen.class);
    fakeServer = new FakeServer(9080);
    client = new Client(screen);

  }

  @After
  public void tearDown() throws Exception {
    fakeServer.stop();

  }

  @Test
  public void hapyPath() throws Exception {
    final States states = context.states("connecting..");
    context.checking(new Expectations() {{
      oneOf(screen).display("Hello .User 1");
      then(states.is("connected"));
    }});
    fakeServer.openConnection();
    client.connect(host, port);
    synchroniser.waitUntil(states.is("connected"));
  }

  @Test
  public void multipleClients() throws Exception {
    final States states = context.states("connecting..");
    context.checking(new Expectations() {{
      oneOf(screen).display("Hello .User 1");
      then(states.is("connected1"));
      oneOf(screen).display("Hello .User 2");
      then(states.is("connected2"));
     oneOf(screen).display(with(any(String.class)));
      then(states.is("server response"));
    }});
    fakeServer.openConnection();
    client.connect(host, port);
    synchroniser.waitUntil(states.is("connected1"));
    client.connect(host, port);
    synchroniser.waitUntil(states.is("connected2"));
    synchroniser.waitUntil(states.is("server response"));
     Thread.sleep(100);
    fakeServer.stop();
  }

  @Test
  public void response() throws Exception {
    final States states = context.states("connecting..");
    context.checking(new Expectations() {{
      oneOf(screen).display("Hello .User 1");
      then(states.is("connected1"));
      oneOf(screen).display("Hello .User 2");
      then(states.is("connected2"));
      oneOf(screen).display("User 2 just connected.");
      then(states.is("server response"));
    }});
    fakeServer.openConnection();
    client.connect(host, port);
    synchroniser.waitUntil(states.is("connected1"));
    client.connect(host, port);
    synchroniser.waitUntil(states.is("connected2"));
    synchroniser.waitUntil(states.is("server response"));

  }



  class FakeServer {

    private int port;
    private ServerSocket serverSocket;
    private Socket socket;
    private PrintStream printStream;
    private Integer count = 0;
    private boolean serverIsRunning = false;
    private Vector<Socket> socketVector = new Vector<>();

    public FakeServer(int port) {

      this.port = port;
    }

    public void openConnection() {
      try {
        serverSocket = new ServerSocket(port);
        serverIsRunning = true;
        new Thread(new Runnable() {

          @Override
          public void run() {
            while (serverIsRunning) {
              try {
                socket = serverSocket.accept();

                synchronized (count) {
                  count++;
                }
                synchronized (socketVector) {
                  socketVector.add(socket);
                }


                printStream = new PrintStream(socket.getOutputStream());
                printStream.println("Hello .User " + count + "\n");
                printStream.flush();

                if (socketVector.size() > 1) {
                  giveResponse();
                }

              } catch (IOException e) {
              }
            }
          }
        }).start();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    private void giveResponse() {
      synchronized (socketVector) {
        for (int i = 0; i < socketVector.size() - 1; i++) {
          try {
            try {
              Thread.sleep(10);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            printStream = new PrintStream(socketVector.get(i).getOutputStream());
            printStream.println("User 2 just connected." + "\n");
            printStream.flush();
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    }

    public void stop() {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      serverIsRunning = false;
//      synchronized (socketVector) {
//        for (Socket socket1 : socketVector) {
//          try {
//            printStream = new PrintStream(socket1.getOutputStream());
//            printStream.println("Offline!");
//            printStream.flush();
//          } catch (IOException e) {
//            e.printStackTrace();
//          }
//        }
//      }
      try {
        serverSocket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}