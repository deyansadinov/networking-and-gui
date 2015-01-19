package suportmultipleclients;

import org.jmock.Expectations;
import org.jmock.States;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import suportingmultipleclients.Client;
import suportingmultipleclients.Screen;
import suportingmultipleclients.UserMessages;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class ClientTest {

  private FakeServer fakeServer;
  private String host = "localhost";
  private Client client;
  private int port = 3333;
  private Synchroniser synchroniser = new Synchroniser();
  private Screen screen;


  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery() {{
    setThreadingPolicy(synchroniser);
  }};

  @Before
  public void setUp() {
    fakeServer = new FakeServer(port);
    screen = context.mock(Screen.class);
    client = new Client(screen);
  }

  @After
  public void after(){
    fakeServer.stop();
  }

  @Test
  public void happyPath() throws InterruptedException {
    final States states = context.states("connecting");
    context.checking(new Expectations() {{
      oneOf(screen).display("Hello user 1");
      then(states.is("connected"));

    }});
    fakeServer.startServer();
    client.connect(host, port);

    synchroniser.waitUntil(states.is("connected"));

  }

  @Test
  public void receiveDisconnectMessage() throws InterruptedException {
    final States states = context.states("Preparing");
    context.checking(new Expectations(){{
      oneOf(screen).display("Hello user 1");
      then(states.is("connected"));
      oneOf(screen).display("You are disconnected");
      then(states.is("disconnected"));
    }});

    fakeServer.startServer();
    client.connect(host,port);
    synchroniser.waitUntil(states.is("connected"));
    client.disconnect();
    synchroniser.waitUntil(states.is("disconnected"));

  }

}
