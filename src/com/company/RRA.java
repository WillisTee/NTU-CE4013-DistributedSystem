package com.company;

public class RRA {
    /**
     * This class provides the constants we need to implement the Reply-Request-Acknowledge Protocol
     */
    public static final String ACK = "ENDMSG";
    public static final String SESSION_TERMINATE = "SESSTERM";

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
