package clientsever;


import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;

import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Before;
import org.junit.Test;
import serverclient.Client;
import serverclient.Clock;
import serverclient.UserMessages;

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

  private int port = 4444;
  private String host = "localhost";


  public JUnitRuleMockery context = new JUnitRuleMockery() {{
    setThreadingPolicy(new Synchroniser());
  }};
  final UserMessages userMessages = context.mock(UserMessages.class);
  final Clock clock = context.mock(Clock.class);

  @Before
  public void setUp() {
    clientMessageListener = new MockClientMessageListener();
    fakeServer = new FakeServer(port, clock);
    client = new Client(clientMessageListener, userMessages);
  }


  @Test
  public void clientReceiveWhatWasSendFromServer() throws InterruptedException {

    context.checking(new Expectations() {{
      ignoring(userMessages);
      oneOf(clock).date();
      will(returnValue(CalendarUtil.january(2014,15)));
    }});

    fakeServer.startServer();
    client.connect(host, port);

    String response = client.getResponse();

    assertThat(response, is("Hello 2014-01-15"));

    fakeServer.stop();

  }

  @Test
  public void newClientWasConnected() {

    context.checking(new Expectations() {{
      oneOf(userMessages).connectClient();
      will(returnValue("Client is connected to Server on 4444 port"));
      oneOf(clock).date();
      will(returnValue(CalendarUtil.january(2014,15)));
    }});

    fakeServer.startServer();
    client.connect(host, port);

    assertThat(clientMessageListener.listMessage, hasItems("Client is connected to Server on 4444 port"));

    fakeServer.stop();
  }

}
