package com.clouway.objectsinjava;


public class BinaryTreeDemo {
  public static void main(String[] args) {
    BinaryTree tr = new BinaryTree();

    tr.add(50);
    tr.add(62);
    tr.add(63);
    tr.add(64);
    tr.add(70);
    tr.add(70);

    tr.preOrderTraverseTree();
    System.out.println("Search for 40 and 30 :");
    System.out.println(tr.find(40) + "  " + tr.find(30));

  }
}
