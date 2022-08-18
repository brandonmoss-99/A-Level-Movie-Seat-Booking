package computingproject;

import java.util.Scanner;

public class PersonalInfo {

    static String title; //holds the client's title
    static String fname; //holds the client's first name
    static String lname; //holds the client's last name
    static String phone; //holds the client's phone number
    static String email; //holds the client's email address
    static Scanner UserInput = new Scanner(System.in); //handles the userinput from the keyboard

    public static void info() { //asks the user to input information
        System.out.println("\n------------------------------------------------------------\nYou have successfully selected " + Seatbooking.SeatsBookedCount + " seats!\nTo continue booking, we will need some personal information from you.");
        System.out.println("\nPlease could you enter your title (Mr, Mrs, Miss etc).");
        title = UserInput.next();
        System.out.println("\nNow could you please enter your FIRST name.");
        fname = UserInput.next();
        System.out.println("\nNow could you please enter your LAST name.");
        lname = UserInput.next();
        System.out.println("\nTo make sure that we can contact you in case something happens, could you please enter your phone number.");
        phone = UserInput.next();
        System.out.println("\nAnd finally, your email address. Don't worry, we won't send any spam!");
        email = UserInput.next();
        System.out.println("\nThank you " + title + " " + fname.charAt(0) + " " + lname + ".");
    }
}
