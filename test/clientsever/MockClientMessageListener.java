package clientsever;

import serverclient.ClientMessageListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class MockClientMessageListener  implements ClientMessageListener{

  public List<String> listMessage = new ArrayList<String>();

  @Override
  public void onNewMessageReceive(String message) {
    listMessage.add(message);
  }
}
