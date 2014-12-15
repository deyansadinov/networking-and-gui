package com.clouway.testingwithmocks.newservice;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 11:42 Oct 14-10-24
 */
public class TestWithRealValidator {
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Test
  public void saveToDataBase() throws Exception {
    Service service = new Service();
    final Person person = new Person("Ivan", "27");
    final DataBase dataBase = context.mock(DataBase.class);
    final Validator validator = new AgeValidator();

    context.checking(new Expectations() {{

      oneOf(dataBase).save(person);
    }});
    service.saveToDB(person, validator,dataBase);
  }

  @Test
  public void saveToDBFailValidation() throws Exception {
    Service service = new Service();
    final Person person = new Person("Ivan", "aaa");
    final DataBase dataBase = context.mock(DataBase.class);
    final Validator validator = new AgeValidator();

    context.checking(new Expectations() {{

      never(dataBase).save(person);
    }});
    service.saveToDB(person, validator,dataBase);

  }

  @Test
  public void isNotAdult() throws Exception {
    Service service = new Service();
    final Person person = new Person("Ivan", "27");
    final DataBase dataBase = context.mock(DataBase.class);

    context.checking(new Expectations(){{
      oneOf(dataBase).ectractPersonAge(person);will(returnValue(11));
    }});

    assertThat(service.isAdult(person, dataBase),is(false));
  }
  @Test
  public void isAdult() throws Exception {
    Service service = new Service();
    final Person person = new Person("Ivan", "27");
    final DataBase dataBase = context.mock(DataBase.class);

    context.checking(new Expectations(){{
      oneOf(dataBase).ectractPersonAge(person);will(returnValue(20));
    }});

    assertThat(service.isAdult(person, dataBase),is(true));
  }

}
