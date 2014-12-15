package com.clouway.objectsinjava;

public class BinaryTree {
  private class Node {

    int key;
    Node leftChild;
    Node rightChild;

    Node(int key) {
      this.key = key;
    }

    public String toString() {
      return "The node with value :" + key;
    }
  }

  private Node root;

  public void add(int key) {

    Node newNode = new Node(key);

    if (root == null) {
      root = newNode;
    } else {
      Node focusNode = root;

      Node parent;

      while (true) {

        parent = focusNode;

        if (focusNode.key == newNode.key) {
          return;
        }
        if (key < focusNode.key) {

          focusNode = focusNode.leftChild;

          if (focusNode == null) {
            parent.leftChild = newNode;
            return;
          }
        } else {
          focusNode = focusNode.rightChild;
          if (focusNode == null) {

            parent.rightChild = newNode;
            return;
          }
        }
      }


    }
  }

  public Node find(int key) {
    Node focusNode = root;
    while (focusNode.key != key) {
      if (key < focusNode.key) {
        focusNode = focusNode.leftChild;
      } else {
        focusNode = focusNode.rightChild;
      }
      if (focusNode == null) {
        return null;
      }
    }
    return focusNode;
  }

  public void preOrderTraverseTree() {

    if (root != null) {
      Node focusNode = root;
      preOrderTraverse(focusNode);
    }
  }


  private void preOrderTraverse(Node focusNode) {
    if (focusNode != null) {
      System.out.println(focusNode);
      preOrderTraverse(focusNode.leftChild);
      preOrderTraverse(focusNode.rightChild);
    }
  }
}
