package com.clouway.testingwithmocks.today;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 17:00 Nov 14-11-19
 */
public class Person {
  private String name;
  private int age;

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }
}
//  @Test
//  public void bodyTooBig() throws Exception {
//
//    Sms sms = new Sms("1234567898", "a","ghjjdsajsksajl");
//    SmsValidator smsValidator = new SmsValidator(10,1,5);
//
//    Errors errors = smsValidator.validate(sms);
//    assertThat(errors,hasError("body is too big"));
//
//
//    assertThat("test",is(equalTo("test")));
//
//  }