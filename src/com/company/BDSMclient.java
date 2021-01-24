package com.company;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * This is the client class that gonna book the facility
 */
public class BDSMclient {

    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 9000;

        try (Socket socket = new Socket(hostname, port)) {

            Utils utils = new SocketUtils(socket);                  //comm to server
            Scanner sc = new Scanner(System.in);                    //comm to user

            String s = "Welcome to the system. Type some shit";     //welcome to user
            System.out.println(s);


            while (true) {


                //The server gonna dump some welcome message
                String server_msg;
                while(true) {
                    server_msg = utils.nextLine();
                    if (server_msg.equals(RRA.ACK))
                        break;
                    else
                        System.out.println(server_msg);
                }
                //System.out.println("Reply to server: ");
                String choice = sc.nextLine();
                utils.println(choice);
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
