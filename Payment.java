package computingproject;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Payment {

    static int PaymentChoice = -1; //holds the user input for the payment type. Set to -1 so the while loop works below
    static Scanner UserInput = new Scanner(System.in); //allows user input from the keyboard
    static int CardNumber; //stores user input for their card number
    static int SecurityCode; //stores user input for their card security code

    public static void selectpayment() { //allows the user to pay for their booking
        System.out.println("\n------------------------------------------------------------\nThe cost of your booking for today is as follows:\n\nSeat charge: £"
                + Pricecalculating.StandardCost + "\nMulti-seat discount: -£" + (Pricecalculating.StandardCost - Pricecalculating.discountcalc())
                + "\nTotal cost: £" + Pricecalculating.discountcalc());

        while (PaymentChoice == -1) { //code below is always ran whilst paymentchoice is -1

            System.out.println("\n------------------------------------------------------------\nWould you like to pay with either (1) Cash, (2) Credit or debit card or (3) PayPal?\nPlease type the payment number.");
            try { //will attempt to execute the following code
                PaymentChoice = UserInput.nextInt(); //integer used in the switch
            } catch (InputMismatchException e) { //run the following code if the userinput is not an integer
                System.out.println("\nYou have entered a value which is not in a suitable format! Please try again.");
                UserInput.nextLine(); //move the scanner a line, so it clears the buffer
                PaymentChoice = -1; //makes sure that the while loop iterates again
                continue;
            }

            switch (PaymentChoice) {
                case 1: //CASH PAYMENT CODE GOES HERE
                    System.out.println("\nPlease enter your cash into the slots below. It will automatically count and give change.");
                    break; //breaks out of the case

                case 2: //CARD PAYMENT CODE GOES HERE
                    System.out.println("\nCould you please enter your card number.");
                    CardNumber = UserInput.nextInt();
                    System.out.println("\nAnd now your security code (the final 3 numbers on the back of your card).");
                    SecurityCode = UserInput.nextInt();
                    System.out.println("\nThank you, we will process your card payment now... Please wait...");
                    break; //breaks out of the case

                case 3: //PAYPAL PAYMENT CODE GOES HERE
                    System.out.println("\nPlease wait... loading the external PayPal payment interface...");
                    break; //breaks out of the case

                default: //ERROR MESSAGE CODE GOES HERE
                    System.out.println("\nYou have entered a number which isn't a payment method! Please try again.");
                    PaymentChoice = -1; //this will make sure that the while loop loops again, so the client is prompted for another input
                    break; //breaks out of the case
            }
        }
        finalizeseats(); //calls the finalize seats method in the payment class
        confirmation(); //calls the confirmation method in the payment class
    }

    public static void finalizeseats() { //replaces all the t's in the array to X's to show the seat bookings are now final
        for (int i = 0; i < Seatbooking.SeatsBooked.length; i++) { //loops for all rows in the array
            if (Seatbooking.SeatsBooked[i][0] != -1) { //checks if the data on the row has been modified (if it isn't just a free row)
                Screens.screens[Filmbooking.ScreenNo - 1][Seatbooking.SeatsBooked[i][0] + 1][Seatbooking.SeatsBooked[i][1] + 1] = "X "; //replace "t " in screens array with "X "      
            }
        }
    }

    public static void clearseats() { //removes all the t's in the array back to -'s or p's depending on the seat type
        for (int i = 0; i < Seatbooking.SeatsBooked.length; i++) {
            if (Seatbooking.SeatsBooked[i][0] != -1) { //checks if the data on the row has been modified (if it isn't just a free row)
                if (Seatbooking.SeatsBooked[i][2] == 0) { //check if the seat is a normal seat
                    Screens.screens[Filmbooking.ScreenNo - 1][Seatbooking.SeatsBooked[i][0] + 1][Seatbooking.SeatsBooked[i][1] + 1] = "- "; //replace "t " in screens array with "X "      
                }
                if (Seatbooking.SeatsBooked[i][2] == 1) { //check if the seat is a premium seat
                    Screens.screens[Filmbooking.ScreenNo - 1][Seatbooking.SeatsBooked[i][0] + 1][Seatbooking.SeatsBooked[i][1] + 1] = "p "; //replace "t " in screens array with "p "      
                }
            }
        }
        System.out.println("\nYour seats have been cleared! We are sorry for any inconvenience this has caused!");
    }

    public static void confirmation() { //displays the user confirmation message
        System.out.println("\n------------------------------------------------------------\nThank you " + PersonalInfo.title + " " + PersonalInfo.fname.charAt(0) + " " + PersonalInfo.lname
                + ".\nYou have successfully booked the following seats for " + Filmbooking.FilmSelected + " in screen " + Filmbooking.ScreenNo + ":\n");

        for (int i = 0; i < Seatbooking.SeatsBooked.length; i++) { //displays all of the seats that the user has booked
            if (Seatbooking.SeatsBooked[i][0] != -1) { //checks if the data on the row has been modified (if it isn't just a free row)
                if (Seatbooking.SeatsBooked[i][2] == 0) { //check if the seat is a normal seat
                    System.out.println("A normal seat on row " + Seatbooking.SeatsBooked[i][0] + " at column " + Seatbooking.SeatsBooked[i][1]);
                }
                if (Seatbooking.SeatsBooked[i][2] == 1) { //check if the seat is a premium seat
                    System.out.println("A premium seat on row " + Seatbooking.SeatsBooked[i][0] + " at column " + Seatbooking.SeatsBooked[i][1]);
                }
            }
        }

        System.out.println("\nYou have been charged £" + Pricecalculating.DiscountedCost + ".\n\nThank you for visiting Mond Movies! We hope you enjoy the film!");
        System.out.println("\n------------------------------------------------------------");
    }
}
