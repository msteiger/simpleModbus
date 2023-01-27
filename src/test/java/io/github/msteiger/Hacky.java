package io.github.msteiger;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class Hacky {

    public static void main(String[] args) {
        connectToServer();
    }

    public static void connectToServer() {
        //Try connect to the server on an unused port eg 9991. A successful connection will return a socket
      System.out.println("Started ..");
      while (true) {
        try(ServerSocket serverSocket = new ServerSocket(9991)) {
            Socket connectionSocket = serverSocket.accept();
            System.out.println("Connected");

            //Create Input&Outputstreams for the connection
            InputStream inputToServer = connectionSocket.getInputStream();
            OutputStream outputFromServer = connectionSocket.getOutputStream();

            PrintWriter serverPrintOut = new PrintWriter(new OutputStreamWriter(outputFromServer, "UTF-8"), true);

            int data = 0;
            String text = "";

            while(data != -1) {
                data = inputToServer.read();

                System.out.print(String.format("0x%02X", data));

                text += Character.toString((char) data);
            }
            System.out.println("\nDONE");
            System.out.println("TEXT: " + text + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
      }
    }
}

