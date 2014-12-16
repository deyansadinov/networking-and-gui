package clientsever;


import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;

import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import serverclient.Client;
import serverclient.ClientMessageListener;
import serverclient.Server;
import serverclient.UserMessage;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class ClientTest {

  private FakeServer fakeServer;
  private Client client;
  private MockClientMessageListener clientMessageListener;


  public JUnitRuleMockery context = new JUnitRuleMockery(){{
    setThreadingPolicy(new Synchroniser());
  }};
  final UserMessage userMessage = context.mock(UserMessage.class);

  @Before
  public void setUp() {
    int port = 4444;
    clientMessageListener = new MockClientMessageListener();
    fakeServer = new FakeServer(port, "Hello 2014-12-15");
    client = new Client(clientMessageListener, userMessage, port);
  }


  @Test
  public void clientReceiveWhatWasSendFromServer() throws InterruptedException {

    context.checking(new Expectations() {{
      ignoring(userMessage);
    }});

    new Thread(new Runnable() {
      @Override
      public void run() {
        fakeServer.startServer();
      }
    }).start();

    client.connect("localhost");

    Thread.sleep(10);

    String response = client.getResponse();

    assertThat(response, is("Hello 2014-12-15"));

    fakeServer.stop();

//    client.disconnect();
  }

  @Test
  public void newClientWasConnected() {

    context.checking(new Expectations() {{
      oneOf(userMessage).connectClient();
      will(returnValue("Client is connected to Server on 4444 port"));
    }});

    new Thread(new Runnable() {
      @Override
      public void run() {
        fakeServer.startServer();
      }
    }).start();

    client.connect("localhost");

    assertThat(clientMessageListener.listMessage, hasItems("Client is connected to Server on 4444 port"));

    fakeServer.stop();
  }

}
