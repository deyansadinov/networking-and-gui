package com.clouway.gui.client_count;

import org.jmock.Expectations;
import org.jmock.States;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Rule;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CountServerTest {

  private Synchroniser synchroniser = new Synchroniser();

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery() {{
    setThreadingPolicy(synchroniser);
  }};

  @Test
  public void sendingCountToConnectedClient() throws Exception {
    final CountListener listener = context.mock(CountListener.class);
    final CountServer server = new CountServer(listener, Executors.newFixedThreadPool(3));

    final States connecting = context.states("connecting").startsAs("connecting");

    context.checking(new Expectations() {{
      oneOf(listener).onCountChanged(1);
      when(connecting.is("connecting"));
      then(connecting.is("connected"));
    }});
    server.start(4444);


    Socket client = new Socket("127.0.0.1", 4444);
    synchroniser.waitUntil(connecting.is("connected"));
//    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
//    assertThat(in.readLine(), is(equalTo("Client: #1")));
    server.stop();
  }
}