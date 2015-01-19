package suportmultipleclients;

import suportingmultipleclients.ServerMessageListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class MockServerMessageListener implements ServerMessageListener {

  public List<String> listMessage = new ArrayList<String>();

//  private ArrayBlockingQueue<Boolean> waitQueue = new ArrayBlockingQueue<Boolean>(2);

  @Override
  public void newClientWasConnected(String messages) {
    listMessage.add(messages);
//    waitQueue.add(true);
  }

  public List<String> getServerDisplayMessages(){
//    try {
//      waitQueue.take();
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
    return listMessage;
  }
}
