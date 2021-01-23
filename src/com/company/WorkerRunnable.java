package com.company;



import java.io.*;
import java.net.Socket;
import java.util.UUID;


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
        Facility f = Neki.getFacility("pornhub");
        Utils utils = null;
        try {
            utils = new SocketUtils(clientSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            InputStream input  = clientSocket.getInputStream();
            InputStreamReader reader = new InputStreamReader(input);
            OutputStream output = clientSocket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            String welcome_msg = String.format("Welcome User %s to MOLIBMA 2.0", userID).toString();
            while (true) {

                writer.println(welcome_msg);

                //print the menu
                this.printMenu(writer);
                writer.println("Choose a valid option: ");
                this.sendACK(writer);


                //get the data they want
                char character;
                StringBuilder data = new StringBuilder();

                while ((character = (char) reader.read()) != '\n') {
                    data.append((char) character);
                }
                if (data.length() == 2 && data.charAt(0) == '.')
                    break;
                System.out.println("[Thread " + id + "]: Received this from client: " + data);
                long time = System.currentTimeMillis();

                Integer choice = Integer.valueOf(String.valueOf(data));
                switch (choice) {
                    case 1:
                        f.queryAvailability(utils);
                        break;
                    case 2:
                        f.book(userID,utils);
                        break;
                }
                writer.println("The current time is: "+time);
                writer.println(".");

            }
            output.close();
            input.close();
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }


    private void printMenu(PrintWriter writer) {
        String[] opts = {"Main Menu:",
                "\t1. Query a facility",
                "\t2. Book a facility",
                "\t3. Change a booking",
                "\t4. Monitor a facility",
                "\t5. TBC 1",
                "\t6. TBC 2"
        };
        for (String opt : opts) {
            writer.println(opt);
        }
    }

    private void sendACK(PrintWriter writer) {
        writer.println(".");
    }
}
