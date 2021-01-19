package com.company;
import java.util.Scanner;

public class utils {
    public static Scanner s = new Scanner(System.in);
    public static int checkUserIntInput(int lower, int upper ) {
        /**
         * This functions return an int value inclusive of upper and lower bound from user
         */
        int i;
        int tries = 1;
        String prompt;
        do {
            if (tries == 1)
                prompt = String.format("Please enter a number between %d and %d (inclusive): ",lower,upper);
            else
                prompt = String.format("Please reenter a valid number between %d and %d (inclusive): ",lower,upper);
            System.out.println(prompt);
            i = s.nextInt();
            tries++;
        } while (i>upper || i < lower);
        return i;
    }

    public static int UserInputOptions(int start, int stop,String prompt,String reprompt) {
        int i;
        int tries = 1;
        String p;
        do {
            if (tries ==1)
                p = prompt;
            else
                p = reprompt;
            System.out.println(p);
            i = s.nextInt();
        } while (i> stop || i < start);
        return i;
    }
}
