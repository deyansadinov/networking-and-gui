package calculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created on 14-12-2.
 *
 * @author Panayot Kulchev <panayotkulchev@gmail.com>
 */

@RunWith(Parameterized.class)
public class CorrectCalculationsTest {


  private String expression;

  private String expectedResult;

  public CorrectCalculationsTest(String expression,
                                 String expectedResult) {
    this.expression = expression;
    this.expectedResult = expectedResult;
  }

  @Parameterized.Parameters
  public static Collection primeNumbers() {
    return Arrays.asList(new Object[][]{
            {"5+1",     "6.0"},
            {"3.5+2.0", "5.5"},
            {"5-1",     "4.0"},
            {"3.5-2.0", "1.5"},
            {"5*1",     "5.0"},
            {"4*2.0",   "8.0"},
            {"5/1",     "5.0"},
            {"6/2",     "3.0"},
            {"6.0/2.0", "3.0"},
    });
  }

  @Test
  public void allOperationsWithTwoNumbers() {

    MathProcessor processor = new CalculationProcessor();

    assertThat(processor.calculate(expression), is(expectedResult));

  }


}