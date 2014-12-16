package clientsever;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Before;
import org.junit.Test;
import serverclient.Clock;
import serverclient.Server;
import serverclient.ServerMessage;


import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

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
