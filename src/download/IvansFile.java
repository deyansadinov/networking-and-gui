package download;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class IvansFile {

  public static void main(String[] args) {
    try {
      Socket socket=new Socket("172.16.188.17",8080);
      while (true){
        InputStreamReader inputStreamReader=new InputStreamReader(socket.getInputStream());
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);

        System.out.println(bufferedReader.readLine());
        if(bufferedReader.readLine()==null){
          return;
        }

      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
