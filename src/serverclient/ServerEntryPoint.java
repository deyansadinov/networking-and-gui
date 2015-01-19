package serverclient;



import java.util.Calendar;
import java.util.Date;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class ServerEntryPoint {
  public static void main(String[] args) {

    Clock clock = new Clock() {
      @Override
      public Date date() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
      }
    };

    ServerView view = new ServerView();

    ServerMessages serverMessages = new ServerMessages() {
      @Override
      public String startServer() {
        return "Server starting on port 4444 and listener for required.";
      }

      @Override
      public String acceptServer() {
        return "Server accept new client.";
      }

      @Override
      public String sayHello() {
        return "Hello ";
      }

      @Override
      public String sendMessage() {
        return "Server send message to client";
      }

    };

    Server server = new Server(view, serverMessages,clock);

    view.setServer(server);

    server.startServer(4444);
  }


}
