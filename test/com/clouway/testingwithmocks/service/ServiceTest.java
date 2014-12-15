package com.clouway.testingwithmocks.service;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ServiceTest {
  @Rule
  public JUnitRuleMockery mockery = new JUnitRuleMockery();

  @Test
  public void getClientAge() throws Exception {
    Person person=new Person("50");
    Service service = new Service(person);

    assertThat(service.getPersonAge(), is(50));
  }

  @Test
  public void testSaveToDBHappypath() throws Exception {
    final Person person=new Person("50");
    Validator validator=new PersonAgeValidator();
    Service service = new Service(person);
    final MySQL mySQL=mockery.mock(MySQL.class);

    mockery.checking(new Expectations(){{
      oneOf(mySQL).saveInDB(person);
    }});

    service.save(mySQL, validator);
  }

  @Test
  public void saveToDbSuccessful() throws Exception {

    final Person person=new Person("32");
    final Validator validator=mockery.mock(Validator.class);
    Service service = new Service(person);
    final MySQL mySQL=mockery.mock(MySQL.class);

    mockery.checking(new Expectations(){{
      oneOf(validator).validate(person);will(returnValue(true));
      oneOf(mySQL).saveInDB(person);
    }});

    service.save(mySQL, validator);

  }
  @Test
  public void saveToDbFailValidation() throws Exception {

    final Person person=new Person("32");
    final Validator validator=mockery.mock(Validator.class);
    Service service = new Service(person);
    final MySQL mySQL=mockery.mock(MySQL.class);

    mockery.checking(new Expectations(){{
      oneOf(validator).validate(person);will(returnValue(false));
      never(mySQL).saveInDB(person);
    }});

    service.save(mySQL, validator);

  }

  @Test
  public void testGetAgeOfPersonFromDB() throws Exception {

    final Person person=new Person("32");

    Service service = new Service(person);
    final MySQL mySQL=mockery.mock(MySQL.class);
    final Validator validator=mockery.mock(Validator.class);

    mockery.checking(new Expectations(){{
      oneOf(validator).validate(person);will(returnValue(true));
      oneOf(mySQL).saveInDB(person);
      oneOf(mySQL).getFromDB(person);
    }});
    service.save(mySQL, validator);
    assertThat(service.isMature(mySQL),is(false));

  }

}