package com.clouway.testing.sample.tasckobjects;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 11:41 Oct 14-10-6
 */
public class Sumator {
  private String a;
  private String b;

  public Sumator(String a, String b) {
    this.a = a;
    this.b = b;
  }
  /**
   * @param a-string
   * @param b-string
   * @return-integer
   */
  public int sum() {
    return Integer.parseInt(a) + Integer.parseInt(b);
  }
}
