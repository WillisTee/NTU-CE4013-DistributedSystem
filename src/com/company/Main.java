package com.company;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
	// write your code here

        Facility pornhub = new Facility("vlxx",0);
        String  userID = UUID.randomUUID().toString();
        while (true) {
            String customized_welcome_msg = String.format("Welcome to the facility booking system, user %s. Below are the options:",userID);
            String[] opts = {customized_welcome_msg,
                    "\t1. Query a facility",
                    "\t2. Book a facility",
                    "\t3. Change a booking",
                    "\t4. Monitor a facility",
                    "\t5. TBC 1",
                    "\t6. TBC 2"
            };

            //print the menu
            for (String opt : opts) {
                System.out.println(opt);
            }
            int chosen_option = utils.UserInputOptions(1, opts.length - 1, "Please choose an option: ",
                    "Invalid options!\nPlease choose a valid option: ");
            switch (chosen_option) {
                case 1:
                    pornhub.queryAvailability();
                    break;
                case 2:
                    pornhub.book(userID);
                    break;
                case 3:
                    pornhub.showRecords();
                    break;
                default:
                    return;
            }
            userID = UUID.randomUUID().toString();
        }
    }
}
