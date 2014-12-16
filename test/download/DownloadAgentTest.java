package download;


import org.jmock.Expectations;

import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;


import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class DownloadAgentTest {

  @ClassRule
  public static TemporaryFolder tempFolder = new TemporaryFolder();
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();


  ProgressListener progressListener = context.mock(ProgressListener.class);

  @Test
  public void missingPageAddress() throws URISyntaxException {

    DownloadAgent downloadAgent = new DownloadAgent(progressListener);

    int transfer = downloadAgent.downloadStart("dadaa", "");

    assertThat(transfer, is(0));

  }

  @Test
  public void missingFilePath() throws URISyntaxException {
    DownloadAgent downloadAgent = new DownloadAgent(progressListener);

    int transfer = downloadAgent.downloadStart("", "sadfasf");

    assertThat(transfer, is(0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void wrongURL() throws Exception {
    DownloadAgent downloadAgent = new DownloadAgent(progressListener);

    File file = tempFolder.newFile("file1.txt");

    downloadAgent.downloadStart(file.toString(), String.valueOf(DownloadAgent.class.getResource("ffhf.txt")));


  }


  @Test
  public void successfulDownload() throws IOException, URISyntaxException {
    DownloadAgent downloadAgent = new DownloadAgent(progressListener);

    File file = tempFolder.newFile("file2.txt");

    context.checking(new Expectations() {{
      oneOf(progressListener).onProgressWasUpdate(0);
      oneOf(progressListener).onProgressWasUpdate(100);
    }});

    int fileTest = DownloadAgentTest.class.getResource("test.txt").openConnection().getContentLength();
    int transfer = downloadAgent.downloadStart(file.toString(), String.valueOf(DownloadAgentTest.class.getResource("test.txt")));

    assertThat(transfer, is(fileTest));
  }

  @Test
  public void updating() throws Exception {
    DownloadAgent downloadAgent = new DownloadAgent(progressListener);

     File file = tempFolder.newFile("file3.txt");


    context.checking(new Expectations() {{

      oneOf(progressListener).onProgressWasUpdate(0);
      oneOf(progressListener).onProgressWasUpdate(25);
      oneOf(progressListener).onProgressWasUpdate(50);
      oneOf(progressListener).onProgressWasUpdate(75);
      oneOf(progressListener).onProgressWasUpdate(100);

    }});

      downloadAgent.downloadStart(file.toString(), String.valueOf(DownloadAgentTest.class.getResource("Test.txt")));


  }


}
