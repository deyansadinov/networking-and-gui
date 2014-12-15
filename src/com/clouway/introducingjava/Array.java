package com.clouway.introducingjava;


import java.util.Arrays;
import java.util.Random;

public class Array {
  /**
   * Generate a random string with changing length.
   *
   * @param length-the lenght of the random string.
   */
  private static final String CHARS = "abcdefghijklmnopqrstuwxyzABCDEFGHIJKLMNOPQRSTUWXYZ";

  private int[] array;

  public Array(int[] array) {

    this.array = array;
  }

  /**
   * Finds the GCD (most greatest common divisor)from two numbers.
   *
   * @param a-first  number
   * @param b-second number
   */
  public int mostGreatestCommonDivisor(int a, int b) {

    while (a != b) {
      if (a > b) {
        a = a - b;
      } else {
        b = b - a;
      }

    }
    return a;
  }

  /**
   * Finds the least common multiple from two numbers.
   *
   * @param a-first  number
   * @param b-second number
   */
  public int leastCommonMultiple(int a, int b) {

    int x = mostGreatestCommonDivisor(a, b);
    return a / x * b / x * x;
  }

  /**
   * Prints all the elements of an array as a string.
   */
  public void print() {

    for (int element : array) {
      System.out.print(element);
    }
  }

  /**
   * Prints out the min element in an array.
   */
  public int getMinElement() {
    int min = array[0];
    for (int i = 1; i < array.length; i++) {
      if (min > array[i]) {
        min = array[i];
      }
    }
    return min;
  }

  /**
   * Prints out the sum of all the elements of an array.
   */
  public int getSum() {
    int sum = 0;
    for (int x : array) {
      sum += x;
    }
    return sum;
  }

  public void sort(int[] array) {
    int low = 0;
    int heigh = array.length - 1;
    quickSort(array, low, heigh);
  }

  /**
   * Reverse an array.
   */
  public int[] reverse() {
    int temp;
    int len = array.length;
    for (int i = 0; i < len; i++) {
      temp = array[i];
      array[i] = array[len - 1];
      array[len - 1] = temp;
      len--;
    }
    return array;
  }

  /**
   * A sort sort of an array.
   *
   * @param array
   * @param low=lowest   index.
   * @param high=highest index.
   */

  private int[] quickSort(int[] array, int low, int high) {


    int middle = low + (high - low) / 2;

    int pivot = array[middle];

    int i = low, j = high;

    while (i <= j) {
      while (array[i] < pivot) {
        i++;
      }
      while (array[j] > pivot) {
        j--;
      }
      if (i <= j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        i++;
        j--;
      }
    }
    if (low < j) {
      quickSort(array, low, j);
    }
    if (high > i) {
      quickSort(array, i, high);
    }
    return array;

  }

  /**
   * Gets the strings from String final CHARS
   *
   * @param length-the lenght of the string
   * @return
   */
  public String randomString(int length) {
    Random rand = new Random();
    StringBuilder buf = new StringBuilder();
    for (int i = 0; i < length; i++) {
      buf.append(CHARS.charAt(rand.nextInt(CHARS.length())));
    }
    return buf.toString();
  }

}
