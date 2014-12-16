package client.fake;

import test5.ClientMessageListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class MockClientMessageListener implements ClientMessageListener {

  public List<String> listMessages = new ArrayList<String>();

  @Override
  public void onNewMessageReceived(String message) {
    listMessages.add(message);
  }
}
