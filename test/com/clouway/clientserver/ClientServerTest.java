package com.clouway.clientserver;

import org.jmock.auto.Mock;
import org.junit.Before;
import org.junit.Test;
import sun.net.TelnetOutputStream;

import java.security.DigestInputStream;
import java.util.Calendar;
import java.util.HashMap;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
public class ClientServerTest {


  @Test
  public void happyPath() {
    Thread server = new Thread(new Server(2222));
    server.start();

    Client client = new Client("localhost", 2222);
    client.start();

    Calendar calendar = Calendar.getInstance();
    assertThat("Hello " + calendar.getTime() + "\n", is(client.getMessage()));
  }


}