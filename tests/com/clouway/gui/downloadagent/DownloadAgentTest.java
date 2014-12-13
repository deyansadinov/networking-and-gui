package com.clouway.gui.downloadagent;

import com.clouway.gui.downloadagent.downloader.URLAddressException;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;
import sun.misc.IOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertArrayEquals;

public class DownloadAgentTest {

  private ProgressUpdateListener progressUpdater;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Rule
  public ExpectedException expectedException = ExpectedException.none();


  @Rule
  public TemporaryFolder tmpFolder = new TemporaryFolder();

  @Before
  public void setUp() throws Exception {
    tmpFolder.create();
    progressUpdater = context.mock(ProgressUpdateListener.class);
  }


  @Test
  public void updateProgressWhileDownloading() throws Exception {
    String urlAddress = "https://docs.oracle.com/javase/tutorial/getStarted/application/examples/HelloWorldApp.java";
    String onlineFileName = tmpFolder.newFile("something").getPath();

    context.checking(new Expectations() {{
      atLeast(1).of(progressUpdater).onUpdate(with(54));
      atLeast(1).of(progressUpdater).onUpdate(with(100));
    }});

    DownloadAgent agent = new DownloadAgent(progressUpdater);
    agent.download(urlAddress, onlineFileName);
  }

  @Test
  public void fileDownloadedCorrectly() throws Exception {
    String urlAddress = "https://docs.oracle.com/javase/tutorial/getStarted/application/examples/HelloWorldApp.java";
    String fileNameToCompareWith = tmpFolder.newFile().getPath();

    context.checking(new Expectations() {{
      allowing(progressUpdater).onUpdate(with(any(int.class)));
    }});

    DownloadAgent agent = new DownloadAgent(progressUpdater);
    agent.download(urlAddress, fileNameToCompareWith);

    byte[] originalBytes = Files.toByteArray(new File("tests/com/clouway/gui/downloadagent/HelloWorldApp.content"));
    byte[] copiedBytes = Files.toByteArray(new File(fileNameToCompareWith));

    assertArrayEquals(originalBytes, copiedBytes);
  }

  @Test
  public void downloadFromIncorrectUrl() throws Exception {
    String incorrectURL = "incorrectAddress";

    expectedException.expect(URLAddressException.class);
    expectedException.expectMessage(String.format("Incorrect URL Address: '%s'", incorrectURL));

    context.checking(new Expectations() {{
      never(progressUpdater).onUpdate(with(any(int.class)));
    }});

    DownloadAgent agent = new DownloadAgent(progressUpdater);
    agent.download(incorrectURL, "testFilesDirectory/fileName");
  }

  @Test
  public void downloadFromEmptyUrl() throws Exception {
    String emptyUrl = "";

    expectedException.expect(URLAddressException.class);
    expectedException.expectMessage(String.format("Incorrect URL Address: '%s'", emptyUrl));

    context.checking(new Expectations() {{
      never(progressUpdater).onUpdate(with(any(int.class)));
    }});

    DownloadAgent agent = new DownloadAgent(progressUpdater);
    agent.download(emptyUrl, "theFile");
  }

  @Test
  public void downloadWithoutSpecifyingFileName() throws Exception {
    String urlAddress = "https://docs.oracle.com/javase/tutorial/getStarted/application/examples/HelloWorldApp.java";

    expectedException.expect(FileNotFoundException.class);
    expectedException.expectMessage("No such file or directory");

    context.checking(new Expectations() {{
      never(progressUpdater).onUpdate(with(any(int.class)));
    }});

    DownloadAgent agent = new DownloadAgent(progressUpdater);
    agent.download(urlAddress, "");
  }

  @After
  public void tearDown() throws Exception {
    tmpFolder.delete();
  }

}