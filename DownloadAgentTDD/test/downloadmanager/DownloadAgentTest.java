package downloadmanager;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created on 14-12-3.
 *
 * @author Panayot Kulchev <panayotkulchev@gmail.com>
 */
public class DownloadAgentTest {


  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  String URLAddress = "http://www.veryicon.com/icon/png/Flag/Rounded%20World%20Flags/Bulgaria%20Flag.png";
  String fileName = "flag.jpg";

  final Display display = context.mock(Display.class);
  final ErrorListener errorListener = context.mock(ErrorListener.class);
  final Downloader downloadAgent = new DownloadAgent(display, errorListener);


  @Test
  public void happyPath() throws IOException {


    context.checking(new Expectations() {{

      oneOf(errorListener).catchEmptyURLorFileField();
      will(returnValue(false));
      oneOf(errorListener).catchUnexistingURLaddres();
      will(returnValue(false));

      allowing(display).udateProgresBar(with(any(Integer.class)));

    }});

    int downloadedBytes = downloadAgent.processDownloading(URLAddress, fileName);

    int expectedBytes = new URL(URLAddress).openConnection().getContentLength();


    assertThat(downloadedBytes, is(expectedBytes));

  }


  @Test
  public void emptyURLField() throws IOException {


    context.checking(new Expectations() {{

      oneOf(errorListener).catchEmptyURLorFileField();
      will(returnValue(true));

      never(display).udateProgresBar(with(any(Integer.class)));

    }});

    int downloadedBytes = downloadAgent.processDownloading("", fileName);


    assertThat(downloadedBytes, is(0));

  }


  @Test
  public void UnexistingURL() throws IOException {


    context.checking(new Expectations() {{

      oneOf(errorListener).catchEmptyURLorFileField();
      will(returnValue(false));
      oneOf(errorListener).catchUnexistingURLaddres();
      will(returnValue(true));

      never(display).udateProgresBar(with(any(Integer.class)));

    }});

    int downloadedBytes = downloadAgent.processDownloading(URLAddress, fileName);

    assertThat(downloadedBytes, is(0));

  }


  @Test
  public void updateTheProgressBar() throws IOException {


    context.checking(new Expectations() {{


      oneOf(errorListener).catchEmptyURLorFileField();
      will(returnValue(false));
      oneOf(errorListener).catchUnexistingURLaddres();
      will(returnValue(false));

      exactly(3).of(display).udateProgresBar(with(any(Integer.class)));

    }});

    downloadAgent.processDownloading(URLAddress, fileName);

  }

}
