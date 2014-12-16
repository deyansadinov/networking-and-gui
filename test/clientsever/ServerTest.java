package clientsever;

import org.jmock.Expectations;

import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import serverclient.Clock;
import serverclient.Server;
import serverclient.ServerMessage;


import javax.management.ObjectName;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

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
      return calendar.date(2014, 12, 15);
    }
  };
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery() {{
    setThreadingPolicy(new Synchroniser());
  }};

  ServerMessage serverMessage = context.mock(ServerMessage.class);

  @Before
  public void setUp() {
    serverMessageListener = new MockServerMessageListener();
    server = new Server(serverMessageListener, serverMessage, clock, 4444);
  }

  @Test
  public void sendMessageToClient() throws Exception {


    context.checking(new Expectations() {{
      oneOf(serverMessage).startServer();
      oneOf(serverMessage).acceptServer();
      oneOf(serverMessage).sayHello();
      will(returnValue("Hello"));

      oneOf(serverMessage).sendMessage();
    }});

    server.startServer();

    Socket clientSocket = new Socket("localhost", 4444);

    String contentResponse = getContent(clientSocket);

    assertThat(contentResponse, is("Hello15-01-15"));

    clientSocket.close();

    server.stopServer();

  }

  @Test
  public void newClientWasConnected() throws IOException {

    context.checking(new Expectations(){{
      oneOf(serverMessage).startServer();
      will(returnValue("Server starting on port 4444 and listener for required"));

      oneOf(serverMessage).acceptServer();
      will(returnValue("Server accept new client"));

      oneOf(serverMessage).sayHello();

      oneOf(serverMessage).sendMessage();
      will(returnValue("Server send message to client"));
    }
    });

    server.startServer();

    Socket clientSocket = new Socket("localhost",4444);

    assertThat(serverMessageListener.listMessages,hasItems("Server starting on port 4444 and listener for required","Server accept new client"
            ,"Server send message to client"));

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
