package com.company;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class BDSMclient {

    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 9000;

        try (Socket socket = new Socket(hostname, port)) {

            String s = "Welcome to the system. Type some shit";
            System.out.println(s);
            Scanner sc = new Scanner(System.in);

            InputStream input = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(input);
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            //Wait for user to type some shit
            //sc.nextLine();

            boolean receiving = true;
            while (true) {

                //receive from server
                char character;
                StringBuilder data = new StringBuilder();
                while(receiving) {

                    while ((character = (char) reader.read()) != '\n') {
                        data.append((char) character);
                    }
                    if (data.length() == 2 && (char) '.' == data.charAt(0)) {
                        receiving = false;
                    } else {
                        System.out.println(data);
                        data.setLength(0);
                    }
                }
                receiving = true;
                System.out.println("Reply to server: ");
                String choice = sc.nextLine();
                writer.println(choice);
                if (choice.equals("Quit"))
                    break;
            }

        } catch (UnknownHostException ex) {

            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {

            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
