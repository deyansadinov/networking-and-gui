package multiclientserver;

import java.io.IOException;

/**
 * Created on 14-12-12.
 *
 * @author Panayot Kulchev <panayotkulchev@gmail.com>
 */
public class MainServer {

  public static void main(String[] args) throws IOException {

    Server server =new Server();
    server.startRunning();

  }

}
