package clientserver;

import java.io.IOException;

/**
 * Created on 14-12-10.
 *
 * @author Panayot Kulchev <panayotkulchev@gmail.com>
 */
public class MainClient {

  public static void main(String[] args) throws IOException {

    Client client =new Client("127.0.0.1");
    client.startRunning();

  }

}
