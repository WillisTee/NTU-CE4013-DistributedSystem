package com.company;

public class RRA {
    public static final String ACK = "ENDMSG";

    public static void main(String[] args){
        StringBuilder sb = new StringBuilder();
        for (char c : RRA.ACK.toCharArray()){
            sb.append(c);
        }
        if (sb.toString().equals(RRA.ACK))
                System.out.println("OK IT'S EQUAL");
        else
                System.out.println("HERE WE GO AGAIN");
    }
}
