package com.clouway.networkingandgui.downloadagent.agent;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 13:46 Nov 14-11-29
 */
public class ProgressBarTest {

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();
  PListener pListener = context.mock(PListener.class);

  @Test
  public void zeroProgressMade() throws Exception {
    NewDownloadAgent newDownloadAgent = new NewDownloadAgent(pListener);
    context.checking(new Expectations() {{
      never(pListener).onProgressUpdate(0);
    }});
    newDownloadAgent.downloadFile("", "");
  }

//  @Test
//  public void progressStartUpdate() throws Exception {
//    NewDownloadAgentUI newDownloadAgentUI = new NewDownloadAgentUI();
//    NewDownloadAgent download = new NewDownloadAgent(newDownloadAgentUI);
//    context.checking(new Expectations() {{
//      atLeast(1).of(pListener).onProgressUpdate(5024);
//
//    }});
//    download.downloadFile("/home/clouway/Desktop/car.jpg", "123");
//    assertThat(newDownloadAgentUI.);
//  }
}


