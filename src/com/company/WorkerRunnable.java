package com.company;



import java.io.*;
import java.net.Socket;
import java.util.UUID;

/**
 * This is the what the server will execute for each thread it spawns.
 */
public class WorkerRunnable implements Runnable {
    protected Socket clientSocket = null;
    protected String serverText   = null;
    public static int numThreads = 0;
    public WorkerRunnable(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText   = serverText;
        numThreads++;
    }

    public void run() {
        int id = numThreads;
        System.out.println("Thread "+id+ " ready to serve");

        FacilityMgr Neki = FacilityMgr.getInstance();
        String  userID = UUID.randomUUID().toString();

        Utils utils = null;
        try {
            utils = new SocketUtils(clientSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }


        String welcome_msg = String.format("Welcome User %s to MOLIBMA 2.0", userID);

        while (true) {

            utils.println(welcome_msg);

            //choose facility
            Facility f = Neki.getUserToChooseFacil(utils);

            //print the menu
            printMenu(utils);

            //get the data they want
            int choice = utils.UserInputOptions(1, 6, "Please choose an option: ",
                    "Invalid options!\nPlease choose a valid option: ");

            long time = System.currentTimeMillis();
            System.out.println("[Thread " + id + "]: Received this from client: " + choice +" at time "+time);

            switch (choice) {
                case 1:
                    f.queryAvailability(utils);
                    break;
                case 2:
                    f.book(userID,utils);
                    break;
            }


        }

    }


    private void printMenu(Utils utils) {
        String[] opts = {"Main Menu:",
                "\t1. Query a facility",
                "\t2. Book a facility",
                "\t3. Change a booking",
                "\t4. Monitor a facility",
                "\t5. TBC 1",
                "\t6. TBC 2"
        };
        for (String opt : opts) {
            utils.println(opt);
        }
    }

    private void sendACK(Utils utils) {
        utils.println(RRA.ACK);
    }
}
