package com.clouway.gui.multiserver.client;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.concurrent.DeterministicExecutor;
import org.junit.Rule;
import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class ClientTest {

  private final int port = 4444;
  private final String localHost = "127.0.0.1";

  // ====================================================================================================

  private class FakeServer {
    private final Executor executor;
    private ServerSocket server;
    private Socket client;

    public FakeServer() {
      executor = Executors.newFixedThreadPool(2);
    }

    public void start(int port) throws IOException {
      server = new ServerSocket(port);
      executor.execute(new Runnable() {
        @Override
        public void run() {
          try {
            client = server.accept();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      });

    }

    public void respond(final String state) throws IOException {
      executor.execute(new Runnable() {
        @Override
        public void run() {
          while (client == null) {
            try {
              sleep(10);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
          try {
            PrintWriter out = new PrintWriter(new BufferedOutputStream(client.getOutputStream()));
            out.println(state);
            out.flush();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      });

    }

    private void stop() throws IOException {
      client.close();
      server.close();
    }
  }
  // ====================================================================================================

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  // ------------------------------------------------------------------------------------------------------

  @Test
  public void clientReceivesConnectedMessageFromSerer() throws Exception {
    final IStatusListener listener = context.mock(IStatusListener.class);
    final DeterministicExecutor clientExecutor = new DeterministicExecutor();

    context.checking(new Expectations() {{
      oneOf(listener).onStatusChanged(EStates.CONNECTED.name());
    }});

    FakeServer server = new FakeServer();
    server.start(port);
    server.respond(EStates.CONNECTED.name());

    Client client = new Client(listener, clientExecutor);
    client.connect(localHost, port);
    client.startListening();
    clientExecutor.runUntilIdle();

    server.stop();
  }

  // ------------------------------------------------------------------------------------------------------

  @Test
  public void clientReceivesDisconnectedMessageFromServer() throws Exception {
    final IStatusListener listener = context.mock(IStatusListener.class);
    final DeterministicExecutor clientExecutor = new DeterministicExecutor();

    context.checking(new Expectations() {{
      oneOf(listener).onStatusChanged(EStates.DISCONNECTED.name());
    }});

    FakeServer server = new FakeServer();
    server.start(port);
    server.respond(EStates.DISCONNECTED.name());

    Client client = new Client(listener, clientExecutor);
    client.connect(localHost, port);
    client.startListening();
    clientExecutor.runUntilIdle();

    server.stop();
  }

  // ------------------------------------------------------------------------------------------------------

}