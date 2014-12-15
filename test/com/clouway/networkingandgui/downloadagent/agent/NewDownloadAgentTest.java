package com.clouway.networkingandgui.downloadagent.agent;

import org.hamcrest.core.Is;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;

public class NewDownloadAgentTest {
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();
  PListener pListener = context.mock(PListener.class);
  File testFile = new File("/home/clouway/Desktop/car.jpg");


  @Test
  public void missingURL() throws Exception {
    NewDownloadAgent newDownloadAgent = new NewDownloadAgent(pListener);

    newDownloadAgent.downloadFile("", "aaa");
  }

  @Test
  public void missingFileName() throws Exception {
    NewDownloadAgent newDownloadAgent = new NewDownloadAgent(pListener);

    newDownloadAgent.downloadFile("/home/clouway/Desktop/car.jpg", "");

  }

  @Test(expected = InvalidURLException.class)
  public void wrongAddressIsProvided() throws Exception {
    NewDownloadAgent downloader = new NewDownloadAgent(pListener);

    downloader.downloadFile("/home/clouway/Desktop/unknownPicture.jp", "aa");
  }

  @Test
  public void downloadSuccessful() throws Exception {
    NewDownloadAgentUI newDownloadAgentUI = new NewDownloadAgentUI();
    NewDownloadAgent downloader = new NewDownloadAgent(newDownloadAgentUI);

    int result;
    result = downloader.downloadFile("/home/clouway/Desktop/car.jpg", "325");
    assertThat(result, Is.<Integer>is((int) testFile.length()));
  }

}