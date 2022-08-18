package computingproject;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Seatbooking {

    static String seatconfirm; //holds the user input for confirming seat choice (Y/N)
    static String bookagain = "y"; //holds the user input for if they want to book another seat (Y/N). Default is Y so the bookagain code runs at least once
    static String confirm; //holds the user input for confirming other things (Y/N)
    static String maxseatconfirm; //holds the user input for confirming what to do with maximum seats
    static Scanner UserInput = new Scanner(System.in); //accepts user input from keyboard
    static Scanner UserInputSeatConfirm = new Scanner(System.in); //accepts user input from keyboard
    static Scanner UserInputBookAgain = new Scanner(System.in); //accepts user input from keyboard
    static Scanner UserInputMaxSeats = new Scanner(System.in); //accepts user input from keyboard
    static boolean IsSeatInputValid = false; //used to know if a seat is valid or not
    static boolean MaxSeatChoiceChosen = false; //used to know if a choice for maximum seats has been chosen
    static int SeatRow = 0; //holds the user input for the seat row
    static int SeatCol = 0; //holds the user input for the seat column  
    static int[][] SeatsBooked = new int[10][3]; //array to hold booked seat info
    static int SeatsBookedCount = 0; //counts how many seats are booked
    static int SeatsBookedMax = 10; //the maximum amount of seats that can be booked. MUST NOT BE MORE THAN THE SEATSBOOKED ARRAY SIZE!

    public static void initseatbookarray() { //fills seatsbooked array with -1 values
        for (int i = 0; i < SeatsBooked.length; i++) {
            for (int n = 0; n < SeatsBooked[i].length; n++) {
                SeatsBooked[i][n] = -1;
            }
        }
    }

    public static void showscreen() { //displays the correct screen information to the user

        System.out.println("\n------------------------------------------------------------\nHere is the current seat booking information for " + Filmbooking.FilmSelected + ", in screen " + Filmbooking.ScreenNo + ":\n");
        for (int i = 0; i < Screens.screens[Filmbooking.ScreenNo - 1].length; i++) { //loops until all the values in the array are processed
            for (int n = 0; n < Screens.screens[Filmbooking.ScreenNo - 1][0].length; n++) { //loops until all the values in each array section are processed
                System.out.print(Screens.screens[Filmbooking.ScreenNo - 1][i][n]); //outputs the array entites on the same line
                System.out.print(" "); //outputs a gap to separate the elements
            }
            System.out.print("\n"); //makes the program output the next elements on a new line
        }
        System.out.println("\n - = free normal seat, p = free premium seat, t = temporarily booked seat, x = booked seat."); //let the clients know what the values in the array mean
    }

    public static void selectseat() { //prompts the client to enter a seat row and seat column before checking it with the checkseat method
        IsSeatInputValid = false;
        while (IsSeatInputValid == false) { //checks to see if valid seat is entered
            System.out.println("\nPlease enter the seat row you would like to book.");
            try { //will attempt to execute the following code
                SeatRow = UserInput.nextInt(); //sets the row to the user input
            } catch (InputMismatchException e) { //run the following code if the userinput is not an integer
                System.err.println("\nYour input is invalid! Please try entering again."); //prints text as error to the console (in red)
                UserInput.nextLine(); //move the scanner a line, so it clears the buffer
                continue; //loop around the while loop again
            }
            System.out.println("\nPlease enter the seat column you would like to book.");
            try { //will attempt to execute the following code
                SeatCol = UserInput.nextInt(); //sets the column to the user input
                IsSeatInputValid = true; //make the code exit the while loop on the next iteration
            } catch (InputMismatchException e) { //run the following code if the userinput is not an integer
                System.out.println("\nYour input is invalid! Please try entering again."); //prints text as error to the console (in red)
                UserInput.nextLine(); //move the scanner a line, so it clears the buffer
                continue; //loop around the while loop again
            }
            checkseat(SeatRow, SeatCol); //executes the checkseat method in the Seatbooking class, passing the SeatRow + SeatCol values
        }
    }

    public static void checkseat(int SeatRow, int SeatCol) { //checks if the seat is valid, and performs suitable actions

        try {
            if ("X ".equals(Screens.screens[Filmbooking.ScreenNo - 1][SeatRow + 1][SeatCol + 1]) || "t ".equals(Screens.screens[Filmbooking.ScreenNo - 1][SeatRow + 1][SeatCol + 1])) { //checks to see if seat selected in the array (screen,x,y) equals X or t (can't book here)
                System.out.println("\nSorry, but that seat is already taken. Please choose another seat!");
                IsSeatInputValid = false; //makes sure that the code within the while loop in the selectseat method is executed 
            }
            if ("  ".equals(Screens.screens[Filmbooking.ScreenNo - 1][SeatRow + 1][SeatCol + 1])) { //checks to see if seat selected in the array (screen,x,y) equals nothing (no seat there)
                System.out.println("\nSorry, but you have chosen an area without a seat. Please choose another seat!");
                IsSeatInputValid = false; //makes sure that the code within the while loop in the selectseat method is executed 
            }
            if ("- ".equals(Screens.screens[Filmbooking.ScreenNo - 1][SeatRow + 1][SeatCol + 1])) { //checks to see if seat selected in the array (screen,x,y) equals - (free normal seat)
                System.out.println("\nYou have chosen a normal seat on row: " + SeatRow + " and column: " + SeatCol + ". This will cost £" + Prices.NSeatPrice + ". Are you sure you want to continue? (Y/N)");
                seatconfirm = UserInputSeatConfirm.next().toLowerCase(); //makes sure that input is always lowercase for checking to work
                if (seatconfirm.startsWith("y")){ //checks if y has been entered as the first character
                    Pricecalculating.addnormal(Prices.NSeatPrice);
                    Screens.screens[Filmbooking.ScreenNo - 1][SeatRow + 1][SeatCol + 1] = "t "; //set the seat location chosen to "t"
                    /* INSERT LIST ADDING STUFF HERE */
                    for (int i = 0; i < SeatsBooked.length; i++) { //loops until the end of the array
                        if (SeatsBooked[i][0] == -1) { //checks if the array in the 1st dimension is empty (no data in it)
                            SeatsBooked[i][0] = SeatRow;
                            SeatsBooked[i][1] = SeatCol;
                            SeatsBooked[i][2] = 0; //0 means normal seat
                            break; //exit the loop, so that not all the array entries are filled with the same booking information
                        }
                    }
                    SeatsBookedCount++; //increases the SeatsBookedCounter by 1
                    System.out.println("\nYou have booked " + SeatsBookedCount + " seat(s), out of a maximum of " + SeatsBookedMax + ".");
                }
                if (!seatconfirm.startsWith("y") && !seatconfirm.startsWith("n")){ //checks if the client input doesn't have a Y or N at the beginning
                    System.out.println("\nThere is something wrong with what you entered! Please try again.");
                    checkseat(SeatRow, SeatCol); //executes the checkseat method, passing the values for SeatRow and SeatCol
                }
            }

            if ("p ".equals(Screens.screens[Filmbooking.ScreenNo - 1][SeatRow + 1][SeatCol + 1])) { //checks to see if seat selected in the array (screen,x,y) equals - (free premium seat)
                System.out.println("\nYou have chosen a premium seat on row: " + SeatRow + " and column: " + SeatCol + ". This will cost £" + Prices.PSeatPrice + ". Are you sure you want to continue? (Y/N)");
                seatconfirm = UserInputSeatConfirm.next().toLowerCase(); //sets seatconfirm equal to the user input, makes sure that input is always lowercase for checking to work
                if (seatconfirm.startsWith("y")) { //checks if y has been entered as the first character
                    Pricecalculating.addpremium(Prices.PSeatPrice);
                    Screens.screens[Filmbooking.ScreenNo - 1][SeatRow + 1][SeatCol + 1] = "t "; //set the seat location chosen to "t"
                    /* INSERT LIST ADDING STUFF HERE */
                    for (int i = 0; i < SeatsBooked.length; i++) { //loops until the end of the array
                        if (SeatsBooked[i][0] == -1) { //checks if the array in the 1st dimension is empty (no data in it)
                            SeatsBooked[i][0] = SeatRow;
                            SeatsBooked[i][1] = SeatCol;
                            SeatsBooked[i][2] = 1; //1 means premium seat
                            break; //exit the loop, so that not all the array entries are filled with the same booking information
                        }
                    }
                    SeatsBookedCount++; //increases the SeatsBookedCounter by 1
                    System.out.println("\nYou have booked " + SeatsBookedCount + " seat(s), out of a maximum of " + SeatsBookedMax + ".");
                }
                if (!seatconfirm.startsWith("y") && !seatconfirm.startsWith("n")){ //checks if the client input doesn't have a Y or N at the beginning
                    System.out.println("\nThere is something wrong with what you entered! Please try again.");
                    checkseat(SeatRow, SeatCol); //executes the checkseat method, passing the values for SeatRow and SeatCol
                }
            }
            if (Screens.screens[Filmbooking.ScreenNo - 1][SeatRow + 1][SeatCol + 1] != "- " && Screens.screens[Filmbooking.ScreenNo - 1][SeatRow + 1][SeatCol + 1] != "p "
                    && Screens.screens[Filmbooking.ScreenNo - 1][SeatRow + 1][SeatCol + 1] != "t " && Screens.screens[Filmbooking.ScreenNo - 1][SeatRow + 1][SeatCol + 1] != "X "
                    && Screens.screens[Filmbooking.ScreenNo - 1][SeatRow + 1][SeatCol + 1] != "  ") { //checks to see if the seat selected in the array (screen,x,y) has a suitable value at all
                System.out.println("\nThere is something wrong with the seat booking information here. Please see a member of staff to correct this!"); //outputs an error message to the console
                System.exit(0); //terminates the program immediately
            }
        } catch (ArrayIndexOutOfBoundsException e) { //code to be run if the value entered is too big for the screen array
            System.out.println("\nA number you entered is too large! Please try again"); //prints text as error to the console
            selectseat(); //executes the selectseat method
        }

    }

    public static void bookanotherseat() { //asks the client if they would like to book another seat. Performs suitable actions
        System.out.println("\nWould you like to book another seat for " + Filmbooking.FilmSelected + "?");
        bookagain = UserInputBookAgain.next().toLowerCase();
        if (!seatconfirm.startsWith("y") && !seatconfirm.startsWith("n")) { //checks if the client input doesn't have a Y or N at the beginning
            System.out.println("\nThere is something wrong with what you entered! Please try again.");
            bookanotherseat(); //executes the bookanotherseat method
        }
    }

    public static void maxseats() { //code to be run if too many seats have been chosen

        while (!MaxSeatChoiceChosen) { //runs the code inside until a suitable choice has been made
            System.out.println("------------------------------------------------------------\nYou have tried to book more than " + SeatsBookedMax + " seats. You may cancel your booking if you wish, or continue with " + SeatsBookedMax + " seats.");
            System.out.println("Would you like to cancel your booking? (Y/N)");
            maxseatconfirm = UserInputMaxSeats.next().toLowerCase();
            if (maxseatconfirm.startsWith("y")) {
                Payment.clearseats();
                MaxSeatChoiceChosen = true; //will exit the while loop
            }
            if (maxseatconfirm.startsWith("n")) {
                MaxSeatChoiceChosen = true; //will exit the while loop
            }
            if (!maxseatconfirm.startsWith("y") && !maxseatconfirm.startsWith("n")) {
                System.out.println("There is an error! Please try again");
            }
        }
    }

    public static void minseats(){ //code to be run if there are no seats chosen
        System.out.println("\n------------------------------------------------------------\nYou haven't booked any seats! We're sorry to see you go!");
        System.exit(0);
    }  
}
