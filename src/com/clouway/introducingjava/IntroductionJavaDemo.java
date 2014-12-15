package com.clouway.introducingjava;


import java.util.Arrays;

public class IntroductionJavaDemo {
  public static void main(String[] args) {
    int[] array = new int[]{5, 1, 3, 8, 6, 7, 10};
    Array array1 = new Array(array);


    System.out.println("The most greatest common divisor is : " + array1.mostGreatestCommonDivisor(84, 18));
    System.out.println("The least common multiple is :" + array1.leastCommonMultiple(12, 4));


    array1.print();
    int min = array1.getMinElement();
    System.out.println("The min is :" + min);
    int sum = array1.getSum();
    System.out.println("The sum is : " + sum);


    array1.sort(array);
    System.out.println("Quick Sort : " + Arrays.toString(array));

    int[] reverse = array1.reverse();
    System.out.println("The reverse array is : " + Arrays.toString(reverse));
    array1.randomString(6);

    String x = array1.randomString(5);
    System.out.println("Random string : " + x);
  }
}
