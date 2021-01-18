package com.company;

public class Facility {

    int[][] availability;
    String name;
    int ID;

    public Facility(String name,int ID) {
        /**
         * Facility class requires a name and an ID (just for safety checking)
         * Each instance keep track of its availability, which is a 7 x 24 array of int
         * 0 means available 1 means not available
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
        for(int i =0;i<7;i++) {
            for (int j =0;j<24;j++){
                if (checkAvailability(i,j)) {
                    System.out.print(" x ");
                }
                else System.out.print(" o ");
            }
            System.out.println();
        }
    }

    public void book(){
        System.out.println("For the date: ");
        int date = utils.checkUserIntInput(0,6);
        System.out.println("For the start time: ");
        int starttime = utils.checkUserIntInput(0,23);
        System.out.println("For the end time: ");
        int endtime = utils.checkUserIntInput(0,23);
        for(int i= starttime; i<=endtime;i++) {
            this.availability[date][i] = 1;
        }
        queryAvailability();

    }
    public static void main(String[] args) {
        // write your code here
        Facility pornhub = new Facility("vlxx",0);
        pornhub.book();
    }
}
