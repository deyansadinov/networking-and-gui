package downloadmanager;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created on 14-12-5.
 *
 * @author Panayot Kulchev <panayotkulchev@gmail.com>
 */
public class ErrorHandlerTest {
  @Rule

  public JUnitRuleMockery context = new JUnitRuleMockery();
  final Display display = context.mock(Display.class);


  @Test
  public void correctData() throws IOException {


    final ErrorListener handler =  new ErrorHandler("http://www.veryicon.com/icon/png/Flag/Rounded%20World%20Flags/Bulgaria%20Flag.png","abc.jpg",display);

    context.checking(new Expectations() {{

      never(display).showWrongURLAddress();
      never(display).showEmptyURLorFileFieldmessage();

    }});

    assertThat(handler.catchUnexistingURLaddres(), is(false));
    assertThat(handler.catchEmptyURLorFileField(), is(false));
  }


  @Test
  public void emptyURL() {


    final ErrorListener handler =  new ErrorHandler("","file.jpg",display);

    context.checking(new Expectations() {{

      oneOf(display).showEmptyURLorFileFieldmessage();

    }});

    assertThat(handler.catchEmptyURLorFileField(), is(true));
  }

  @Test
  public void emptyNameField() {


    final ErrorListener handler =  new ErrorHandler("file.jpg","",display);

    context.checking(new Expectations() {{

      oneOf(display).showEmptyURLorFileFieldmessage();

    }});

    assertThat(handler.catchEmptyURLorFileField(), is(true));
  }

  @Test
  public void emptyURLandNameField() {


    final ErrorListener handler =  new ErrorHandler("","",display);

    context.checking(new Expectations() {{

      oneOf(display).showEmptyURLorFileFieldmessage();

    }});

    assertThat(handler.catchEmptyURLorFileField(), is(true));
  }

  @Test
  public void unexistingURL() throws IOException {


    final ErrorListener handler =  new ErrorHandler("http://abc123.html","abc.jpg",display);

    context.checking(new Expectations() {{

      oneOf(display).showWrongURLAddress();

    }});

    assertThat(handler.catchUnexistingURLaddres(), is(true));
  }


}
