package computingproject;

import java.io.IOException;

public class Computingproject {

    public static void main(String[] args) throws IOException { //main class of the program
        Seatbooking.initseatbookarray(); //initialise Seatbooking array (fill it with -1 values)
        System.out.println("------------------------------------------------------------\nWelcome to Mond Movies Bristol!\nWe currently accept a maximum booking of " 
                + Seatbooking.SeatsBookedMax + " seats. If you require more than this, please see a member of staff!\n\nBelow are the films available to book:\n ");
        Filmbooking.showfilms(); //executes the showfilms method I made in the filmbooking class
        Filmbooking.selectfilms(); //executes the selectfilms method in the filmbooking class    
        while(Seatbooking.bookagain.startsWith("y") && Seatbooking.SeatsBookedCount<Seatbooking.SeatsBookedMax){ //loop whilst under the max value of booked seats and the user says they want to book another seat
            Seatbooking.showscreen(); //executes the showscreen method in the seatbooking class
            Seatbooking.selectseat(); //executes the selectseat method in the seatbooking class
            Seatbooking.bookanotherseat(); //executes the bookanotherseat method in the seatbooking class
        }
        if(Seatbooking.bookagain.startsWith("n") && Seatbooking.SeatsBookedCount == 0){ //checks if the user has booked no seats
            Seatbooking.minseats(); //calls the maxseats method in the seatbooking class
        }
        if(Seatbooking.bookagain.startsWith("y") && Seatbooking.SeatsBookedCount <= Seatbooking.SeatsBookedMax){ //checks if the user has exceeded max booked value
            Seatbooking.maxseats(); //calls the maxseats method in the seatbooking class
        }
        PersonalInfo.info(); //calls the info method in the personalinfo class
        Payment.selectpayment(); //calls the selectpayment method in the payment class
        Filehandling.FileWrite(); //calls the FileWrite method in the Filehanding class
        }
}