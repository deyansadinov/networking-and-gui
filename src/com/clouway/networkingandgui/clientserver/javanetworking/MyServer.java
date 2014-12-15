package com.clouway.networkingandgui.clientserver.javanetworking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 16:20 Dec 14-12-3
 */
public class MyServer {
  public static void main(String[] args) {
    Socket connection = null;
    Socket connection2 = null;
    ServerSocket serverSocket=null;
    try {
      serverSocket = new ServerSocket(8080);
      connection = serverSocket.accept();

     connection2=serverSocket.accept();
      Scanner scanner = new Scanner(System.in);
      StringBuilder builder = new StringBuilder();
      StringBuilder builder2 = new StringBuilder();
      while (true) {

        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        InputStream in = connection.getInputStream();
        InputStreamReader reader = new InputStreamReader(in);
        PrintWriter writer = new PrintWriter(connection.getOutputStream());

        InputStream inTwo = connection2.getInputStream();
        InputStreamReader readerTwo = new InputStreamReader(inTwo);
        PrintWriter writerTwo = new PrintWriter(connection2.getOutputStream());


        BufferedReader reader1 = new BufferedReader(reader);
        BufferedReader reader2 = new BufferedReader(readerTwo);
        while (true) {
          String marqn = reader1.readLine();
          System.out.println("User Marqn :"+ marqn);
          String pepo = reader2.readLine();
          System.out.println("User Pepo :"+ pepo);
StringBuilder builder1=new StringBuilder();
          builder1.append("User Marqn :"+ marqn+"\n"+"User Pepo :"+ pepo+"\n");

          String text = scanner.nextLine();
          writer.println(builder1.toString()+"Ivan :"+text);
          writerTwo.println(builder1.toString()+"Ivan :"+text);
          writer.flush();
          writerTwo.flush();
        }

      }
    } catch (IOException e) {
      e.printStackTrace();
    }finally {
      try {
        serverSocket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      try {
        connection.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      try {
        connection2.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
