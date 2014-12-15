package com.clouway.networkingandgui.downloadagent.downloadagenttdd;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class NewDownloadAgentAgentTest {
  @Rule
  public JUnitRuleMockery mockery = new JUnitRuleMockery();
  ProgressListener progressListener = mockery.mock(ProgressListener.class);
  File testFile = new File("/home/clouway/Desktop/car.jpg");

  @Test
  public void emptyURL() throws Exception {
    DownloadAgent downloadAgent = new DownloadAgent(progressListener);

    mockery.checking(new Expectations() {{
      never(progressListener).onProgressUpdate(0);
    }});
    downloadAgent.downloadStart("", "abc");
  }

  @Test
  public void emptyFileName() throws Exception {
    DownloadAgent downloadAgent = new DownloadAgent(progressListener);
    mockery.checking(new Expectations() {{
      never(progressListener).onProgressUpdate(0);
    }});
    downloadAgent.downloadStart("12", "");
  }

  @Test
  public void correctFileExtension() throws Exception {
    DownloadAgent downloadAgent = new DownloadAgent(progressListener);
    String result = downloadAgent.getFileExtension("12.xxx");
    assertThat(result, is("xxx"));
  }

  @Test
  public void downloadFile() throws Exception {
   //DownloadAgentUI downloadAgentUI=new DownloadAgentUI();
    DownloadAgent downloadAgent = new DownloadAgent(progressListener);

    int result=downloadAgent.downloadStart("/home/clouway/Desktop/car.jpg","a");
  // assertThat(result, Is.<Integer>is((int) testFile.length()));
  }

}