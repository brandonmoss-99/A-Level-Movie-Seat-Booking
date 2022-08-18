package computingproject;

import java.util.Scanner;

public class Filmbooking {
    
    static int pointer = 0; //used for iterating through the for loop of the films
    static int ScreenNo; //stores the film screen number chosen. Static means it can be accessed without creating an object
    static String FilmSelected; //stores the film name chosen
    static String FilmChoice; //The user input for what film they want to book
    static Scanner UserInput = new Scanner(System.in); //handles all the user inputs from the keyboard.
    static boolean HasFilmChosen = false; //checks if the film name entered is valid or not. Default of false to allow the film select loop to iterate at least once.
    
    public static void showfilms(){ //prints out the film array. void means it won't return a value to the main class
        
        //for loop to repeat through all of the rows and columns of the array
        for(int i=0; i<Films.films.length; i++){ //repeats until all of the rows of the 2d array are processed
            for(int n=0; n<Films.films[0].length; n++){ //repeats until all of the columns of the 2d array are processed
                System.out.print(Films.films[i][n]); //prints out the array value at the given position
                System.out.print(" "); //leaves a gap between the array values to make it look more natural
            }
            System.out.print("\n"); //makes the next iteration of the for loop print on a new line
        }
        System.out.println("\n------------------------------------------------------------");
    }
    
    public static void selectfilms(){
        while(!HasFilmChosen){ //checks if a film has been chosen
            System.out.println("Please enter the film you would like to book (including the _ if necessary):"); //prompts the user to enter a film title
            FilmChoice = UserInput.next(); //sets the FilmChoice equal to the next input from the UserInput Scanner

            Filmbooking.checkfilm(FilmChoice); //executes the film checking process I made in the films class

            if(!Filmbooking.checkfilm(FilmChoice)){ //checks if the film checking process returned false
                System.out.println("Sorry, you've made a mistake with the name. Please check your spelling and try again!\n");
                HasFilmChosen = false; //will make the while loop iterate again
            }
            else{
                HasFilmChosen = true; //will exit the while loop when it iterates again
            }
        }
    }
    
    public static boolean checkfilm(String FilmChoice){ //checks the userinput to see if it's valid
        
        for(int i=0; i<Films.films.length; i++){ //repeats until all of the filmnames of the array are processed
            if(FilmChoice.equalsIgnoreCase(Films.films[i][0])){
                pointer = i+1; //sets the pointer equal to the loop cycle + 1 to show which film was chosen
                break; //exit the for loop
            }
        }
            if(pointer == 0){
                return false; //return value to the main class
            }
            else{
                FilmSelected = Films.films[(pointer-1)][0]; //set filmselected to the film array title chosen
                ScreenNo = Integer.parseInt(Films.films[(pointer-1)][Films.films[0].length-1]); //set screenno to the film array screen chosen
                return true; //return value to whatever called the method
            }
    }
    
}