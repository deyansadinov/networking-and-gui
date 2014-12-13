package multiclientserver;

import java.io.IOException;

/**
 * Created on 14-12-12.
 *
 * @author Panayot Kulchev <panayotkulchev@gmail.com>
 */
public class MainClient {

  public static void main(String[] args) throws IOException, InterruptedException {

    Client client = new Client("127.0.0.1",3333);

    client.startRunning();


  }
}
