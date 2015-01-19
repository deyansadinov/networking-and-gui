package clientsever;

import org.jmock.Expectations;

import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import serverclient.Clock;
import serverclient.Server;
import serverclient.ServerMessages;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static clientsever.CalendarUtil.january;
import static org.hamcrest.Matchers.any;
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
    public Date date() {
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

  @After
  public void closeServer(){
    server.stopServer();

  }

  @Test
  public void sendMessageToClient() throws Exception {


    context.checking(new Expectations() {{
      oneOf(serverMessages).startServer();
      will(returnValue("Server starting on port 4444 and listener for required."));
      oneOf(serverMessages).acceptServer();
      will(returnValue("Server accept new client"));
      oneOf(serverMessages).sayHello();
      will(returnValue("Hello"));

      oneOf(serverMessages).sendMessage();
    }});

    server.startServer(port);


    clientSocket = new Socket(host, port);

    String contentResponse = getContent(clientSocket);

    assertThat(contentResponse, is("Hello 2015-01-05"));

    clientSocket.close();

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
  }

  @Test
  public void multipleClients()throws IOException{

    context.checking(new Expectations(){{
      oneOf(serverMessages).startServer();
      will(returnValue("Server starting on port 4444 and listener for required"));

      oneOf(serverMessages).acceptServer();
      will(returnValue("Server accept new client"));

      oneOf(serverMessages).sayHello();
      will(returnValue("Hello"));

      oneOf(serverMessages).sendMessage();
      will(returnValue("Server send message to client"));

      oneOf(serverMessages).acceptServer();
      will(returnValue("Server accept new client"));

      oneOf(serverMessages).sayHello();
      will(returnValue("Hello"));

      oneOf(serverMessages).sendMessage();
      will(returnValue("Server send message to client"));
    }});

    server.startServer(port);
    clientSocket = new Socket(host,port);
    Socket clientSocket2 = new Socket(host,port);

    String contentResponse = getContent(clientSocket);
    String contentResponse2 = getContent(clientSocket2);

    assertThat(contentResponse, is("Hello 2015-01-05"));
    assertThat(contentResponse2, is("Hello 2015-01-05"));

    clientSocket.close();
    clientSocket2.close();
  }

  private String getContent(Socket clientSocket) throws IOException {
    StringBuilder builder = new StringBuilder();

      InputStream in = clientSocket.getInputStream();

      BufferedReader buffIn = new BufferedReader(new InputStreamReader(in));
      builder.append(buffIn.readLine());
      return builder.toString();

  }



}
