package com.company;

import java.io.*;
import java.net.Socket;

public class SocketUtils implements Utils {

    InputStream input;
    InputStreamReader reader ;
    OutputStream output;
    PrintWriter writer;
    public SocketUtils(Socket clientSocket) throws IOException {
        this.input  = clientSocket.getInputStream();
        this.reader = new InputStreamReader(input);
        this.output = clientSocket.getOutputStream();
        this.writer = new PrintWriter(output, true);
    }

    public int checkUserIntInput(int lower, int upper) {
        return 0;
    }

    public int UserInputOptions(int start, int stop,String prompt,String reprompt){
        return 0;
    }
    public String getBookingID(){
        return null;
    }
    public void println(String s) {

    }
    public void print(String s){

    }
}
