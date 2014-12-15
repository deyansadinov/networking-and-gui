package com.clouway.networkingandgui.newdownloadagent;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DownloadAgentTest {
  @ClassRule
  public static TemporaryFolder temporaryFolder = new TemporaryFolder();
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();
  ProgressListener progressListener = context.mock(ProgressListener.class);

  @Test
  public void missingURLName() throws Exception {

    DownloadAgent downloadAgent = new DownloadAgent(progressListener);

    int bytesTransfered = downloadAgent.startDownload("", "alabala");

    assertThat(bytesTransfered, is(0));
  }

  @Test
  public void missingFileName() throws Exception {

    DownloadAgent downloadAgent = new DownloadAgent(progressListener);

    int bytesTransferred = downloadAgent.startDownload("aaaa", "");

    assertThat(bytesTransferred, is(0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void incorrectURL() throws Exception {

    DownloadAgent downloadAgent = new DownloadAgent(progressListener);

    downloadAgent.startDownload(String.valueOf(DownloadAgentTest.class.getResource("car.jg")), "sample.txt.txt");
  }

  @Test
  public void downloadSuccessful() throws Exception {
    DownloadAgent downloadAgent = new DownloadAgent(progressListener);
    File testFile = temporaryFolder.newFile("test.txt");
    context.checking(new Expectations() {{
      oneOf(progressListener).onProgressUpdate(0);
      oneOf(progressListener).onProgressUpdate(100);
    }});
    int testFileSize = DownloadAgentTest.class.getResource("FileForTesting").openConnection().getContentLength();
    int bytesTransfered = downloadAgent.startDownload(String.valueOf(DownloadAgentTest.class.getResource("FileForTesting")), testFile.getAbsolutePath());

    assertThat(bytesTransfered, is(testFileSize));
  }

  @Test
  public void progressUpdating() throws Exception {

    DownloadAgent downloadAgent = new DownloadAgent(progressListener);
    context.checking(new Expectations() {{
      oneOf(progressListener).onProgressUpdate(0);
      oneOf(progressListener).onProgressUpdate(100);
    }});
    downloadAgent.startDownload(String.valueOf(DownloadAgentTest.class.getResource("FileForTesting")), "NewTestFile.txt");
  }

}