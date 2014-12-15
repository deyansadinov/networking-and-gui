package com.clouway.inputoutput.dataclass;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class DataClass {
  /**
   * Saves the paramether o in the output stream
   *  @param out
   * @param o
   */
  public void saveObject(OutputStream out, Object o) throws IOException {
    ObjectOutputStream objOut = new ObjectOutputStream(out);
    objOut.writeObject(o);

  }

  /**
   * Reads  the input stream from a class and returns in as a result.
   * @param in
   * @return
   */
  public Object getObject(InputStream in) throws IOException, ClassNotFoundException {
    ObjectInputStream objIn = new ObjectInputStream(in);

    return objIn.readObject();

  }

}
