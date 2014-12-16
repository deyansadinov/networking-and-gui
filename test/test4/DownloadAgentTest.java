package test4;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class DownloadAgentTest {

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();
  ProgessListener progessListener = context.mock(ProgessListener.class);

  @Test
  public void missingURLName() throws Exception {

    DownloadAgent downloadAgent = new DownloadAgent(progessListener);

    int bytesTransfered = downloadAgent.startDownload("", "alabala");

    assertThat(bytesTransfered, is(0));
  }

  @Test
  public void missingFileName() throws Exception {

    DownloadAgent downloadAgent = new DownloadAgent(progessListener);

    int bytesTransfered = downloadAgent.startDownload("aaaa", "");

    assertThat(bytesTransfered, is(0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void incorectURL() throws Exception {

    DownloadAgent downloadAgent = new DownloadAgent(progessListener);

    int bytesTransfered = downloadAgent.startDownload(String.valueOf(DownloadAgentTest.class.getResource("car.jg")), "sample.txt");
  }

  @Test
  public void downloadSuccessful() throws Exception {
    DownloadAgent downloadAgent = new DownloadAgent(progessListener);


    context.checking(new Expectations(){{
      oneOf(progessListener).onProgressUpdate(0);

      oneOf(progessListener).onProgressUpdate(100);

    }});
    int testFileSize = DownloadAgentTest.class.getResource("TestFile").openConnection().getContentLength();
    int bytesTransfered = downloadAgent.startDownload(String.valueOf(DownloadAgentTest.class.getResource("TestFile")), "NewTestFile.txt");

    assertThat(bytesTransfered, is(testFileSize));
  }

  @Test
  public void progressUpdateing() throws Exception {

    DownloadAgent downloadAgent = new DownloadAgent(progessListener);
    context.checking(new Expectations(){{
      oneOf(progessListener).onProgressUpdate(0);
      oneOf(progessListener).onProgressUpdate(100);
    }});
    downloadAgent.startDownload(String.valueOf(DownloadAgentTest.class.getResource("TestFile")), "NewTestFile.txt");
  }



}
