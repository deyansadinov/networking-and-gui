package com.clouway.inputoutput.dataclass;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataClassDemo{
  public static void main(String[] args) throws IOException, ClassNotFoundException {

    Person person = new Person("Ivan", "Ivan Sarafov");
    Person copyPerson;

    DataClass dc = new DataClass();

    OutputStream out = new FileOutputStream("serializable.ser");
    dc.saveObject(out, person);

    InputStream in = new FileInputStream("serializable.ser");
    copyPerson = (Person) dc.getObject(in);

    System.out.println(copyPerson.getName() + "    " + copyPerson.getAddress());

  in.close();
  out.close();

  }
}
