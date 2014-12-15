package com.clouway.gui.multiserver.server;

import com.clouway.gui.multiserver.client.EStates;
import org.jmock.Expectations;
import org.jmock.States;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.concurrent.DeterministicExecutor;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.BindException;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MultiClientServerTest {

  final private Integer port = 4444;
  final private String localHost = "127.0.0.1";
  final private Synchroniser synchroniser = new Synchroniser();

  private MultiClientServer server;
  private StatusListener listener;
  private DeterministicExecutor executor;
  private FakeClient client;
  private States status;

  // ===================================================================================================================

  private class FakeClient {

    private Socket socket;
    private Executor executor;
    private BufferedReader in;

    public FakeClient() {
      this.executor = Executors.newFixedThreadPool(2);
    }

    public void connect(final String host, final Integer port) {
      executor.execute(new Runnable() {
        @Override
        public void run() {
          try {
            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          } catch (IOException e) {
            fail("Fake client couldn't create socket");
          }
        }
      });
    }

    public void assertThatHasReceivedFromServerOnConnect(final String status) {
      executor.execute(new Runnable() {
        @Override
        public void run() {
          try {
            while (in == null){
              try {
                Thread.sleep(10);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            }
            assertThat(in.readLine(), is(equalTo(status)));
          } catch (IOException e) {
            fail("Fake client couldn't get InputStream");
          }
        }
      });
    }

    public void assertThatHasReceivedFromServerOnDisconnect(final String status) {
      executor.execute(new Runnable() {
        @Override
        public void run() {
          try {
            while (in == null){
              try {
                Thread.sleep(10);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            }
            in.readLine(); // Clear msg from on connect
            assertThat(in.readLine(), is(equalTo(status)));
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      });
    }
  }

  // ===================================================================================================================

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery() {{
    setThreadingPolicy(synchroniser);
  }};

  // -------------------------------------------------------------------------------------------------------------------

  @Before
  public void setUp() throws Exception {
    listener = context.mock(StatusListener.class);
    executor = new DeterministicExecutor();
    server = new MultiClientServer(listener, Executors.newFixedThreadPool(3)); // Arbitrarily chosen 3
    client = new FakeClient();
    status = context.states("connecting").startsAs("connecting");
  }

  // -------------------------------------------------------------------------------------------------------------------

  @Test
  public void serverSendsConnectedMsgOnConnect() throws Exception {
    context.checking(new Expectations() {{
      oneOf(listener).onStatusChanged(EStates.CONNECTED.name());
      when(status.is("connecting"));
      then(status.is("connected"));
      allowing(listener).onStatusChanged(with(any(String.class)));
    }});

    server.start(port);
    client.connect(localHost, port);
    synchroniser.waitUntil(status.is("connected"));
    client.assertThatHasReceivedFromServerOnConnect(EStates.CONNECTED.name());
    server.stop();
  }

  // -------------------------------------------------------------------------------------------------------------------

  @Test
  public void notifyUserThatHeIsDisconnectedWhenServerStops() throws Exception {
    context.checking(new Expectations() {{
      allowing(listener).onStatusChanged(with(any(String.class)));
      when(status.is("connecting"));
      then(status.is("connected"));
      oneOf(listener).onStatusChanged(EStates.DISCONNECTED.name());
      then(status.is("disconnected"));
    }});

    server.start(port);
    client.connect(localHost, port);
    synchroniser.waitUntil(status.is("connected"));
    server.stop();  // Make server send Disconnected
    client.assertThatHasReceivedFromServerOnDisconnect(EStates.DISCONNECTED.name());
    synchroniser.waitUntil(status.is("disconnected"));
  }

  // -------------------------------------------------------------------------------------------------------------------

  @Test
  public void twoConnectedClientsReceiveConnectedMsgFromServer() throws Exception {
    final FakeClient client1 = new FakeClient();
    final FakeClient client2 = new FakeClient();

    context.checking(new Expectations() {{
      exactly(2).of(listener).onStatusChanged(EStates.CONNECTED.name());
      then(status.is("connected"));
      allowing(listener).onStatusChanged(with(any(String.class)));
    }});

    server.start(port);
    client1.connect(localHost, port);
    client2.connect(localHost, port);
    synchroniser.waitUntil(status.is("connected"));
    client1.assertThatHasReceivedFromServerOnConnect(EStates.CONNECTED.name());
    client2.assertThatHasReceivedFromServerOnConnect(EStates.CONNECTED.name());
    server.stop();
  }

  // -------------------------------------------------------------------------------------------------------------------

  @Test
  public void twoDisconnectedClientsReceiveDisconnectMsgFromServer() throws Exception {
    final FakeClient client1 = new FakeClient();
    final FakeClient client2 = new FakeClient();

    context.checking(new Expectations() {{
      exactly(2).of(listener).onStatusChanged(EStates.CONNECTED.name());
      then(status.is("connected"));
      exactly(2).of(listener).onStatusChanged(EStates.DISCONNECTED.name());
      then(status.is("disconnected"));
    }});

    server.start(port);
    client1.connect(localHost, port);
    client2.connect(localHost, port);
    synchroniser.waitUntil(status.is("connected"));
    server.stop(); // Make server send respond with Disconnected msg
    client1.assertThatHasReceivedFromServerOnDisconnect(EStates.DISCONNECTED.name());
    client2.assertThatHasReceivedFromServerOnDisconnect(EStates.DISCONNECTED.name());
    synchroniser.waitUntil(status.is("disconnected"));
  }

  // -------------------------------------------------------------------------------------------------------------------

  @Test(expected = BindException.class)
  public void throwingExceptionWhenStartingTwiceOnSamePort() throws Exception {
    final MultiClientServer server1 = new MultiClientServer(null,Executors.newFixedThreadPool(2));
    final MultiClientServer server2 = new MultiClientServer(null,Executors.newFixedThreadPool(2));
    server1.start(port);
    server2.start(port);

    server1.stop();
    server2.stop();
  }

}