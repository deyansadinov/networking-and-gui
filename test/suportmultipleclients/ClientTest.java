package suportmultipleclients;

import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.concurrent.Synchroniser;
import suportingmultipleclients.UserMessages;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class ClientTest {

  public JUnitRuleMockery context = new JUnitRuleMockery() {{
    setThreadingPolicy(new Synchroniser());
  }};

  UserMessages userMessages = context.mock(UserMessages.class);


}
