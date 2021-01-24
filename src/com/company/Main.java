package com.company;

import java.util.UUID;


public class Main {

    public static void main(String[] args) {
	// write your code here
        Utils utils = new TerminalUtils();
        FacilityMgr Neki = FacilityMgr.getInstance();
        utils.println("Welcome! Please give us a name to address you: ");
        String userID = utils.nextLine();
        while (true) {
            boolean keep_alive = true;
            String welcom_msg = String.format("Welcome User %s to MOLIBMA 2.0", userID).toString();
            System.out.println(welcom_msg);
            while (keep_alive) {
                //choose the facility

                Facility f = Neki.getUserToChooseFacil(utils);

                //print the facil menu
                Main.printFacilMenu();
                //Choosing the options for a facil
                int chosen_option = utils.UserInputOptions(1, 6, "Please choose an option: ",
                        "Invalid options!\nPlease choose a valid option: ");

                switch (chosen_option)
                {
                    case 1:
                        f.queryAvailability(utils);
                        break;
                    case 2:
                        f.book(userID, utils);
                        break;
                    case 3:
                        String bookingID = utils.getBookingID();
                        f.changeBooking(bookingID, utils);
                        break;
                    case 5:
                        f.showRecords(userID,utils);
                        break;
                }

            }
        }
    }

    public static void printFacilMenu(){
        String[] opts = {"Main Menu:",
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
    }
}
