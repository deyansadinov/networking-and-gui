package suportmultipleclients;

import suportingmultipleclients.ClientMessageListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class MockClientMessageListener implements ClientMessageListener {

  public List<String> listMessages = new ArrayList<String>();

  @Override
  public void onResponseWasReceived(String message) {
    listMessages.add(message);
  }
}
