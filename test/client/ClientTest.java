package client;


import client.fake.MockClientMessageListener;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import test5.Client;
import test5.UserMessages;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class ClientTest {

  private MockClientMessageListener clientMessageListener;
  private DummyServer dummyServer;
  private Client client;

  Mockery mockery = new JUnit4Mockery() {{
    setThreadingPolicy(new Synchroniser());
  }};

  UserMessages userMessages = mockery.mock(UserMessages.class);


  @Before
  public void setUp() {
    clientMessageListener = new MockClientMessageListener();
    dummyServer = new DummyServer(3333, "Hello 2014-02-05");
    client = new Client(clientMessageListener, userMessages, 3333);
  }

  @After
  public void closeServer() {
    dummyServer.stop();
  }

  @Test
  public void clientReceiveWhatWasSendFromServer() throws Exception {

    mockery.checking(new Expectations() {{
      ignoring(userMessages);
    }
    });

    new Thread(new Runnable() {
      @Override
      public void run() {
        dummyServer.startServer();
      }
    }).start();

    client.connectToServer();

    String response = client.getResponse();

    assertThat(response, is("Hello 2014-02-05"));

    client.stop();
  }

  @Test
  public void newClientWasConnected() throws Exception {

    mockery.checking(new Expectations() {{
      oneOf(userMessages).connectClient();
      will(returnValue("Client is connected to server on port 3333."));

      oneOf(userMessages).readFromServer();
      will(returnValue("Client read message from server."));

      oneOf(userMessages).printMessage();
      will(returnValue("Client print message on the console"));

      oneOf(userMessages).closeClient();
      will(returnValue("Client close connection with server."));
    }
    });

    new Thread(new Runnable() {
      @Override
      public void run() {
        dummyServer.startServer();
      }
    }).start();

    client.connectToServer();

    assertThat(clientMessageListener.listMessages, hasItems("Client is connected to server on port 3333.", "Client read message from server.",
            "Client print message on the console", "Client close connection with server."));
  }
}
