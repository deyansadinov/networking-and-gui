package clientsever;

import org.jmock.Expectations;

import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import serverclient.Clock;
import serverclient.Server;
import serverclient.ServerMessages;


import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

import static clientsever.CalendarUtil.january;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class ServerTest {
  private Socket clientSocket;
  private Server server;
  private MockServerMessageListener serverMessageListener;
  private int port = 4444;
  private String host = "localhost";

  Clock clock = new Clock() {
    @Override
    public Date now() {
      return january(2015, 5);
    }
  };

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery() {{
    setThreadingPolicy(new Synchroniser());
  }};

  ServerMessages serverMessages = context.mock(ServerMessages.class);

  @Before
  public void setUp() {
    serverMessageListener = new MockServerMessageListener();
    server = new Server(serverMessageListener, serverMessages, clock);

  }

  @Test
  public void sendMessageToClient() throws Exception {


    context.checking(new Expectations() {{
      oneOf(serverMessages).startServer();
      oneOf(serverMessages).acceptServer();
      oneOf(serverMessages).sayHello();
      will(returnValue("Hello"));

      oneOf(serverMessages).sendMessage();
    }});

    server.startServer(port);

     clientSocket = new Socket(host, port);

    String contentResponse = getContent(clientSocket);

    assertThat(contentResponse, is("Hello 15-05"));

    clientSocket.close();

    server.stopServer();

  }

  @Test
  public void newClientWasConnected() throws IOException {

    context.checking(new Expectations() {
      {
        oneOf(serverMessages).startServer();
        will(returnValue("Server starting on port 4444 and listener for required"));

        oneOf(serverMessages).acceptServer();
        will(returnValue("Server accept new client"));

        oneOf(serverMessages).sayHello();

        oneOf(serverMessages).sendMessage();
        will(returnValue("Server send message to client"));
      }
    });

    server.startServer(port);

     clientSocket = new Socket(host, port);

    assertThat(serverMessageListener.listMessages, hasItems("Server starting on port 4444 and listener for required", "Server accept new client"
            , "Server send message to client"));

    clientSocket.close();

    server.stopServer();
  }

  private String getContent(Socket clientSocket) throws IOException {
    StringBuilder builder = new StringBuilder();

    InputStream in = clientSocket.getInputStream();
    Scanner scanner = new Scanner(in);

    while (scanner.hasNext()) {
      builder.append(scanner.nextLine());
    }
    return builder.toString();

  }

}
