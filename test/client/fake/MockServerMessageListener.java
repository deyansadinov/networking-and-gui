package client.fake;

import test5.ServerMessageListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class MockServerMessageListener implements ServerMessageListener {

  public List<String> listMessages = new ArrayList<String>();

  @Override
  public void newClientWasConnected(String message) {
    listMessages.add(message);
  }

}
