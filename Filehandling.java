package computingproject;

import java.io.BufferedWriter; //used to create a bufferedwriter
import java.io.File; //used to create a file
import java.io.FileWriter; //used to create a filewriter
import java.io.IOException;

public class Filehandling {
    
    public static void FileWrite() throws IOException{ //writes to a file
        File bookingInfo = new File("booking_info.txt"); //specifies the location of the file
        if (!bookingInfo.exists()){ //checks to see if the file doesn't already exist
            bookingInfo.createNewFile(); //creates a new file
        }
        
        FileWriter bookingInfoFWriter = new FileWriter(bookingInfo, true); //creates a new filewriter for the newFile location
        BufferedWriter bookingInfoBWriter = new BufferedWriter(bookingInfoFWriter); //creates a new BufferdWriter for the FileWriter
        /*START OF FILE WRITING STUFF*/
        bookingInfoBWriter.newLine(); //makes sure that booking information is written on a new line each time the program is ran
        bookingInfoBWriter.write(PersonalInfo.title + " " + PersonalInfo.fname + " " + PersonalInfo.lname + " (" + PersonalInfo.phone + ") " + "booked " + Filmbooking.FilmSelected + " in screen " + Filmbooking.ScreenNo + ": ");
        for(int i=0; i<Seatbooking.SeatsBooked.length; i++){
            if(Seatbooking.SeatsBooked[i][0] != -1){ //checks if the data on the row has been modified (if it isn't just a free row)
                if(Seatbooking.SeatsBooked[i][2] == 0){ //check if the seat is a normal seat
                    bookingInfoBWriter.write("Nseat row " + Seatbooking.SeatsBooked[i][0] + " col " + Seatbooking.SeatsBooked[i][1] + ", ");
                }  
                if(Seatbooking.SeatsBooked[i][2] == 1){ //check if the seat is a premium seat
                     bookingInfoBWriter.write("Pseat row " + Seatbooking.SeatsBooked[i][0] + " col " + Seatbooking.SeatsBooked[i][1] + ", ");
                } 
            }
        }
        bookingInfoBWriter.write("Paid £" + Pricecalculating.DiscountedCost + " with choice " + Payment.PaymentChoice + ".");
        /*END OF FILE WRITING STUFF*/
        bookingInfoBWriter.close(); //closes the BufferedWriter, so that the OS doesn't throw an error saying the file is still in use if moved/copied etc
    }
}