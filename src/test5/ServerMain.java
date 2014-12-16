package test5;


import java.util.Calendar;
import java.util.Date;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class ServerMain {

  public static void main(String[] args) {

    Clock clock = new Clock() {
      @Override
      public Date now() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
      }
    };

    ServerView serverView = new ServerView();

    ServerMessages serverMessages = new SerMessage();

    Server server = new Server(serverView, serverMessages, clock, 4444);

    serverView.setServer(server);

    server.startServer();
  }

}
