package suportmultipleclients;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import suportingmultipleclients.Server;
import suportingmultipleclients.ServerMessages;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.List;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class ServerTest {


  private Server server;
  private MockServerMessageListener serverMessageListener;
  private DummyClient dummyClient;
  private DummyClient dummyClient2;
  private Integer port = 3333;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery() {{
    setThreadingPolicy(new Synchroniser());
  }};



  ServerMessages serverMessages = context.mock(ServerMessages.class);

  @Before
  public void setUp() {
    serverMessageListener = new MockServerMessageListener();
    server = new Server(serverMessages, serverMessageListener);
    dummyClient = new DummyClient(port);
    dummyClient2 = new DummyClient(port);
  }

  @After
  public void closeServer() {
    server.stop();
  }

  @Test
  public void newClientWasConnected() {
    context.checking(new Expectations() {
      {
        oneOf(serverMessages).connectNewClient(1);
        will(returnValue("The server is connected client 1"));

        oneOf(serverMessages).sendFirstMessageToClient(1);
      }
    });

    server.start(port);

    dummyClient.connect();

    List<String> serverMessage = serverMessageListener.getServerDisplayMessages();

    assertThat(serverMessage, hasItems("The server is connected client 1"));


  }

  @Test
  public void clientNumberIsIncremented() {
    context.checking(new Expectations() {
      {
        oneOf(serverMessages).connectNewClient(1);
        will(returnValue("The server is connected client 1"));

        oneOf(serverMessages).sendFirstMessageToClient(1);

        oneOf(serverMessages).connectNewClient(2);
        will(returnValue("The server is connected client 2"));

        oneOf(serverMessages).sendFirstMessageToClient(2);

        oneOf(serverMessages).sendMessageToAllClients(2);
      }
    });

    server.start(port);

    dummyClient.connect();

    List<String> firstServerMessage = serverMessageListener.getServerDisplayMessages();

    dummyClient2.connect();

    List<String> allServerMessages = serverMessageListener.getServerDisplayMessages();

    assertThat(firstServerMessage,hasItems("The server is connected client 1"));
    assertThat(allServerMessages,hasItems("The server is connected client 2"));

    assertThat(serverMessageListener.listMessage.size(),is(2));

  }
  
  @Test
  public void sendMessageToClientThatHasConnected() {
    context.checking(new Expectations(){{
      oneOf(serverMessages).connectNewClient(1);
      oneOf(serverMessages).sendFirstMessageToClient(1);
      will(returnValue("You are client number 1"));

    }});

    server.start(port);

    System.out.println("After start");
    dummyClient.connect();

    String connectRespond = dummyClient.getResponse();

    assertThat(connectRespond,is("You are client number 1"));

  }

  @Test
  public void sendMessageToClientWhenNewClientWasConnected() {
    context.checking(new Expectations(){
      {
        oneOf(serverMessages).connectNewClient(1);

        oneOf(serverMessages).sendFirstMessageToClient(1);
        will(returnValue("You are client number 1"));

        oneOf(serverMessages).connectNewClient(2);

        oneOf(serverMessages).sendFirstMessageToClient(2);
        will(returnValue("You are client number 2"));

        oneOf(serverMessages).sendMessageToAllClients(2);
        will(returnValue("Client number 2 is connected"));
    }});

    server.start(port);

    dummyClient.connect();

    String client1FirstResponse = dummyClient.getResponse();

    dummyClient2.connect();

    String client2FirstResponse = dummyClient2.getResponse();
    String client1SecondResponse = dummyClient.getResponse();

    assertThat(client1FirstResponse,is("You are client number 1"));
    assertThat(client2FirstResponse,is("You are client number 2"));
    assertThat(client1SecondResponse,is("You are client number 1" + "Client number 2 is connected"));
  }


}
