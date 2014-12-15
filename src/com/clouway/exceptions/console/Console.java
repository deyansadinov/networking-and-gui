package com.clouway.exceptions.console;

import java.util.Scanner;

public class Console {
  /**
   * Reads numbers from the console trys and catches NumberOutOfBoundsException
   */
  public void readNumbers() {
    try (Scanner scanner = new Scanner(System.in)) {
      System.out.println("Enter some numbers from [0-100] :");
      Integer number = null;
      while ((number = scanner.nextInt()) != -1) {
        checkNumber(number);
        System.out.println("Valid number");
      }

    } catch (NumberOutOfBoundsException e) {
      System.out.println(e.toString());
    }
  }

  /**
   * @param num-number to check
   *           and throws an NumberOutOfBounds exception
   * @throws NumberOutOfBoundsException
   */
  private void checkNumber(int num) throws NumberOutOfBoundsException {
    if (num < 0 || num > 100) {
      throw new NumberOutOfBoundsException();

    }
  }

}
