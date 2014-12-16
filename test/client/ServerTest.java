package client;

import client.fake.MockServerMessageListener;
import client.util.CalendarUtil;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import test5.Clock;
import test5.Server;
import test5.ServerMessages;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class ServerTest {

  private Server server;
  private MockServerMessageListener serverMessageListener;

  Clock clock = new Clock() {
    @Override
    public Date now() {
    CalendarUtil calendar = new CalendarUtil();
//      return february(2014, 5);
      return calendar.february(2014,5);
    }
  };

  Mockery mockery = new JUnit4Mockery() {{
    setThreadingPolicy(new Synchroniser());
  }};

  ServerMessages serverMessages = mockery.mock(ServerMessages.class);

  @Before
  public void setUp() {
    serverMessageListener = new MockServerMessageListener();

    server = new Server(serverMessageListener, serverMessages, clock, 4444);
  }

  @After
  public void closeServer() {

    server.stopServer();
  }

  @Test
  public void sendMessageToClient()throws Exception {


    mockery.checking(new Expectations() {{
      oneOf(serverMessages).startServer();
      oneOf(serverMessages).acceptClient();
      oneOf(serverMessages).gatHello();
      will(returnValue("Hello "));

      oneOf(serverMessages).sendMessage();
    }
    });

    server.startServer();

    Socket clientSocket = new Socket("localhost", 4444);

    String contentResponse = getContent(clientSocket);

    assertThat(contentResponse, is("Hello 2014-12-05"));

    clientSocket.close();
  }

  @Test
  public void newClientWasConnected() throws Exception {

    mockery.checking(new Expectations() {{
      oneOf(serverMessages).startServer();
      will(returnValue("Server starting on port 4444 and listener for required."));

      oneOf(serverMessages).acceptClient();
      will(returnValue("Server accept new client."));

      oneOf(serverMessages).gatHello();

      oneOf(serverMessages).sendMessage();
      will(returnValue("Server send message to client"));
    }
    });

    server.startServer();

    Socket clientSocket = new Socket("localhost", 4444);

    assertThat(serverMessageListener.listMessages, hasItems("Server starting on port 4444 and listener for required.", "Server accept new client.",
            "Server send message to client"));

    clientSocket.close();
  }

  private String getContent(Socket clientSocket) throws IOException {
    StringBuilder builder = new StringBuilder();

    InputStream inputStream = clientSocket.getInputStream();
    Scanner scanner = new Scanner(inputStream);

    while (scanner.hasNext()) {
      builder.append(scanner.nextLine());
    }

    return builder.toString();
  }

}
