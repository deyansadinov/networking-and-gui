package com.clouway.networkingandgui.clientserver.clientserver_v_4_with_tests;

import org.jmock.Expectations;
import org.jmock.States;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ClientTest {

  private Screen screen;
  private Synchroniser synchroniser = new Synchroniser();
  private FakeServer fakeServer;
  private Client client;
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery() {{
    setThreadingPolicy(synchroniser);
  }};

  @Before
  public void setUp() throws Exception {
    screen = context.mock(Screen.class);
    fakeServer = new FakeServer(8090);
    client = new Client(screen);

  }

//  @After
//  public void tearDown() throws Exception {
//    fakeServer.stop();
//  }

  @Test
  public void connected() throws Exception {

    final States clientConnection = context.states("connecting..");


    context.checking(new Expectations() {{
      oneOf(screen).display("Hello");
      then(clientConnection.is("connected"));
      allowing(screen).display(with(any(String.class)));
    }});
    fakeServer.connect();
    client.connect("172.16.188.17", 8090);
    // fakeServer.stop();
    synchroniser.waitUntil(clientConnection.is("connected"));

  }

  @Test
  public void serverStopped() throws Exception {
    final States clientState = context.states("connecting..");
    FakeServer server2 = new FakeServer(9090);

    context.checking(new Expectations() {{
      oneOf(screen).display("Hello");
      then(clientState.is("connected"));
      oneOf(screen).display(" Stop");
       then(clientState.is("stop"));
    }});
    server2.connect();
    client.connect("172.16.188.17", 9090);
     synchroniser.waitUntil(clientState.is("connected"));
    server2.stop();
     synchroniser.waitUntil(clientState.is("stop"));
  }

  class FakeServer {

    private ServerSocket serverSocket;
    private int port;
    private Socket socket;
    private PrintStream printStream;
    private boolean isAlive = false;
    private final Vector<Socket> socketVector = new Vector<>();

    FakeServer(int port) {
      this.port = port;
    }

    public synchronized void connect() {

      try {
        serverSocket = new ServerSocket(port);

        isAlive = true;
        new Thread(new Runnable() {
          @Override
          public void run() {
            synchronized (serverSocket) {
              while (isAlive) {
                try {
                  socket = serverSocket.accept();
                  printStream = new PrintStream(socket.getOutputStream());
                  printStream.println("Hello" + "\n");
                  printStream.flush();

                  synchronized (socketVector) {
                    socketVector.add(socket);
                  }
                } catch (IOException e) {
                  e.printStackTrace();
                }
              }
            }
          }
        }).start();

      } catch (IOException e) {
        e.printStackTrace();
      }


    }

    public void stop() {

      System.out.println(socketVector.size());

        for (Socket x : socketVector) {
          try {
            printStream = new PrintStream(x.getOutputStream());
            printStream.println(" Stop" + "\n");
            printStream.flush();

          } catch (IOException e) {
            e.printStackTrace();
          }

        }

    }


  }
}