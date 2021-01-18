package com.company;
import java.util.Scanner;

public class utils {
    public static Scanner s = new Scanner(System.in);
    public static int checkUserIntInput(int lower, int upper ) {
        /**
         * This functions return an int value inclusive of upper and lower bound from user
         */
        int i;
        do {
            String prompt = String.format("Please enter a number between %d and %d (inclusive): ",lower,upper);
            System.out.println(prompt);
            i = s.nextInt();
        } while (i>upper || i < lower);
        return i;
    }
}
