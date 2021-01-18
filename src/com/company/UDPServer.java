package com.company;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;


public class UDPServer {

    public static final int PORT = 17; //QOTD PORT

    public static void main(String args[]) throws Exception {

        DatagramSocket serverSocket = new DatagramSocket(PORT);
        byte[] rx_buf = new byte[1024];
        do {
            DatagramPacket rxPacket = new DatagramPacket(rx_buf, rx_buf.length);
            serverSocket.receive(rxPacket);
            String sentence = new String(rxPacket.getData(), 0, rxPacket.getLength());
            //Arrays.fill(rx_buf, 0, sentence.length()-1, (byte) 0);
            System.out.println("Client says: " + sentence);
            if (!sentence.equals("BYE")) {
                System.out.println("Length: " + sentence.length());
            } else {
                System.out.println("Client has sent FIN."); //fking TCP
                break;
            }
            InetAddress IPAddress = rxPacket.getAddress();
            int port = rxPacket.getPort();
            Scanner sc = new Scanner(System.in);
            System.out.print("Say something to the client: ");
            String msg = sc.nextLine();
            byte[] tx_buf = msg.getBytes();
            DatagramPacket txPacket = new DatagramPacket(tx_buf, tx_buf.length, IPAddress, port);
            serverSocket.send(txPacket);
        } while (true);
        serverSocket.close();
    }
}