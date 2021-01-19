package com.company;

public class Facility {

    int[][] availability;
    String name;
    int ID;

    public Facility(String name,int ID) {
        /**
         * Facility class requires a name and an ID (just for safety checking)
         * Each instance keep track of its availability, which is a 7 x 24 array of int
         * 0 means available not ID means not available
         */

        this.name = name;
        this.ID =ID;
        this.availability = new int[7][24];
    }

    public boolean checkAvailability(int date, int time) {
        /**
         * Check if a single time slot is available
         */

        if (this.availability[date][time] == 0)
            return true;
        else return false;
    }

    public void queryAvailability() {
        /**
         * Print the availability of each facility
         */
        char[] days = {'M', 'T', 'W', 'T', 'F', 'S', 'S'};
        System.out.println("   |  0100|  0200|  0300|  0400|  0500|  0600|  0700|  0800|  0900|  1000| " +
                " 1100|  1200|  1300|  1400|  1500|  1600|  1700|  1800|  1900|  2000|  2100|  2200|  2300|  2400|");
        System.out.println("--------------------------------------------------------------------------------------" +
                "--------------------------------------------------------------------------------------");
        for(int i = 0; i<7; i++){
            System.out.print(days[i] + "  | ");
            for(int j = 0; j<24; j++){
                if (checkAvailability(i, j)) {
                    System.out.print("  O  | ");
                }
                else System.out.print("  X  | ");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("O = Available \nX = Booked \n");

    }

    public void book(int ID){
        System.out.println("For the day (0: Monday, 1: Tuesday, 2: Wednesday, 3: Thursday, 4: Friday, 5: Saturday, 6: Sunday): ");
        int date = utils.checkUserIntInput(0,6);
        System.out.println("For the start time: ");
        int startTime = utils.checkUserIntInput(0,23);
        System.out.println("For the end time: ");
        int endTime = utils.checkUserIntInput(0,23);
        for(int i= startTime; i<=endTime;i++) {
            this.availability[date][i] = ID;
        }
        queryAvailability();
    }

    public void changeBooking(int bookingID) {


    }

}
