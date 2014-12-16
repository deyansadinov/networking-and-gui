package serverclient;



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

    ServerView view = new ServerView();

    ServerMessage serverMessage = new SerMessages();

    Server server = new Server(view,serverMessage,clock,4444);

    view.setServer(server);

    server.startServer();
  }


}
