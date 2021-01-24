package com.company;



import java.io.*;
import java.net.Socket;
import java.util.UUID;

/**
 * This is the what the server will execute for each thread it spawns.
 */
public class WorkerRunnable implements Runnable {
    private static final long POLLING_DURATION = 10;  //seconds

    protected Socket clientSocket = null;
    protected String serverText   = null;
    public static int numThreads = 0;
    public long thisThreadLastModified;

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

            utils.println(welcome_msg);                         //piggy back the input's ACK

            //choose facility
            Facility f = Neki.getUserToChooseFacil(utils);

            //print the menu
            printFacilMenu(utils);                             //piggy back the input's ACK

            //get the data they want
            int choice = utils.UserInputOptions(1, 6, "Please choose an option: ",
                    "Invalid options!\nPlease choose a valid option: ");                    //thank you for the carry

            long time = System.currentTimeMillis();
            System.out.println("[Thread " + id + "]: Received this from client: " + choice +" at time "+time);

            switch (choice) {
                case 1:
                    f.queryAvailability(utils);

                    break;
                case 2:
                    f.book(userID,utils);
                    thisThreadLastModified = System.currentTimeMillis();
                    break;
                case 3:
                    String bookingID = utils.getBookingID();
                    f.changeBooking(bookingID,utils);
                    thisThreadLastModified = System.currentTimeMillis();
                    break;
                case 4:
                    monitorFacility(f,utils);
                    break;
                case 5:
                    f.showRecords(utils);
                    break;

            }


        }

    }
    private void monitorFacility(Facility f, Utils utils) {
        int dur = utils.UserInputOptions(1, 100,
                "Enter the duration (in hours) to monitor from (1-100) inclusive",
                "Please reenter the duration (1-100) inclusive: ");
        long wakeTime = System.currentTimeMillis() + dur*1000; //  do this for ${dur}  seconds
        Runnable listener = new Runnable() {
            @Override
            public void run() {
                //every polling duration, get the lastModified of that facility
                do {
                    long serverlastModified = f.getLastModified();

                    //changes were made by this thread, do nothing
                    if (thisThreadLastModified + POLLING_DURATION > serverlastModified) ;

                        // if some changes were made by other clients
                        // get latest changes via query, then change last modified
                    else {
                        long l = f.getLastModified();
                        String noti = String.format("=====  %s AT %02d:%02d  =====",f.name.toUpperCase(), (l/36000/1000)%24,(l/1000/60)%60);
                        utils.println(noti);
                        f.queryAvailability(utils);
                        thisThreadLastModified = l;
                    }

                    //then go sleep for polling duration (10s)
                    try {
                        Thread.sleep(POLLING_DURATION * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while(System.currentTimeMillis() < wakeTime);
            }
        };
        new Thread(listener).start();

        //Make it synchronous because i dont like to fk up the input output stream
        try {
            Thread.sleep(dur * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }   finally {
            utils.println("Stopping monitor");
        }


        //this function will piggy back on the menu ACK so we don't send any ACK here

    }

    private void printFacilMenu(Utils utils) {
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

}
