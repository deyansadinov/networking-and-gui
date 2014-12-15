package com.clouway.downloadagent;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
public class DownloadAgentTest {
  private final String url = "http://www.jusuchyne.com/codingforme/wp-content/uploads/2012/05/java1.jpg";

  @Rule
  public TemporaryFolder tempFolder = new TemporaryFolder();

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Mock
  public DownloadHandler downloadHandler;

  @Test
  public void happyPath() throws IOException, InterruptedException {
    File assets = tempFolder.newFile("Java.jpg");
    DownloadHandler downloadAgentUI = new DownloadAgentUI();
    String tempFolderPath = assets.getAbsolutePath();
    DownloadAgent downloadAgent = new DownloadAgent(downloadAgentUI, url, tempFolderPath);
    downloadAgent.download();

    byte[] fromNet = Files.readAllBytes(Paths.get(tempFolderPath));
    byte[] fromHdd = Files.readAllBytes(Paths.get("test/images/java1.jpg"));
    assertArrayEquals(fromHdd, fromNet);
  }

  @Test
  public void downloadingData() throws IOException {
    File assets = tempFolder.newFile("Java.jpg");
    String tempFolderPath = assets.getAbsolutePath();

    DownloadAgent downloadAgent = new DownloadAgent(downloadHandler, url, tempFolderPath);
    context.checking(new Expectations() {{
      oneOf(downloadHandler).update(10);
    }});
    downloadAgent.update(10d);
  }

  @Test
  public void emptyUrlException() throws IOException {
    exception.expect(EmptyUrlException.class);
    File assets = tempFolder.newFile("Java.jpg");
    DownloadHandler downloadAgentUI = new DownloadAgentUI();
    String tempFolderPath = assets.getAbsolutePath();
    DownloadAgent downloadAgent = new DownloadAgent(downloadAgentUI, "", tempFolderPath);
    downloadAgent.download();
  }

  @Test
  public void incorrectUrl() throws IOException {
    exception.expect(IncorrectUrlException.class);

    File assets = tempFolder.newFile("Java.jpg");
    DownloadHandler downloadAgentUI = new DownloadAgentUI();
    String tempFolderPath = assets.getAbsolutePath();
    DownloadAgent downloadAgent = new DownloadAgent(downloadAgentUI, "asdasd", tempFolderPath);
    downloadAgent.download();
  }
}
