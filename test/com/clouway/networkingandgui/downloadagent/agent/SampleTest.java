package com.clouway.networkingandgui.downloadagent.agent;

import com.google.common.io.ByteStreams;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 17:52 Dec 14-12-1
 */
public class SampleTest {

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();


  interface DataTransfer {

    // file size
    int startTransfer(InputStream stream);

    // transfers next token
    int transferNext();

  }

  interface ProgressListener {
    void onProgressUpdated(int value);
  }

  class DownloadAgent {

    private DataTransfer dataTransfer;
    private ProgressListener progressListener;

    public DownloadAgent(DataTransfer dataTransfer, ProgressListener progressListener) {
      this.dataTransfer = dataTransfer;
      this.progressListener = progressListener;
    }

    public void download(InputStream stream) {

    }
  }

  @Test
  public void test() throws Exception {
    final DataTransfer dataTransfer = context.mock(DataTransfer.class);
    final ProgressListener listener = context.mock(ProgressListener.class);
    final InputStream resourceAsStream = SampleTest.class.getResourceAsStream("newText.txt");

    DownloadAgent agent = new DownloadAgent(dataTransfer, listener);

    context.checking(new Expectations() {{
      oneOf(dataTransfer).startTransfer(resourceAsStream);
      will(returnValue(10));


      oneOf(listener).onProgressUpdated(0);

      oneOf(dataTransfer).transferNext();
      will(returnValue(5));


      oneOf(listener).onProgressUpdated(50);

      oneOf(dataTransfer).transferNext();
      will(returnValue(5));

      oneOf(listener).onProgressUpdated(100);

    }});

    agent.download(resourceAsStream);
  }





  @Test
  public void testtt() throws Exception {
    byte[] content = ByteStreams.toByteArray(SampleTest.class.getResourceAsStream("newText.txt"));

    File f = File.createTempFile("test","txt");

    copy(SampleTest.class.getResourceAsStream("newText.txt"), f);

    byte[] copiedContent = ByteStreams.toByteArray(new FileInputStream(f));

    assertArrayEquals(content, copiedContent);

    f.delete();
  }

  // production code
  private void copy(InputStream inputStream, File file) {
    try {
      byte[] content = ByteStreams.toByteArray(inputStream);
      FileOutputStream fout = new FileOutputStream(file);
      fout.write(content);
      fout.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
