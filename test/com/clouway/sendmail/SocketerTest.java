package com.clouway.sendmail;

import com.clouway.socketstodelete.SocketAdapter;
import com.clouway.socketstodelete.SocketListener;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
public class SocketerTest {
  @Mock
  SocketListener socketListener;
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

//  @Test
//  public void happyPath() {
//    SocketAdapter adapter = new SocketAdapter(socketListener);
//
//    context.checking(new Expectations() {{
//      oneOf(socketListener).read();
//      oneOf(socketListener).write();
//    }});
//    adapter.getMessage();
//
//  }
}
