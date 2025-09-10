package com.pro1.sms;
import java.util.Scanner;
public class InputHelper {
    private static Scanner inp = new Scanner (System.in);

        
        //A reusable method to get a non-empty string from the user
        public static String getStringInput(String prompt){
            System.out.println(prompt);
            String input = inp.nextLine().trim();

            //this loop runs as long as the user's input is just empty spaces
            while(input.trim().isEmpty()){
                System.out.println("Input cannot be empty. Please try again.");
                System.out.println(prompt);
                input = inp.nextLine().trim();
            }
            return input.trim();
        }

        //this method gets an integer from the user and protects against crashes
        public static int getIntInput(String prompt){
            System.out.println(prompt);

            //this loop runs as long as the user types something that is NOT an integer
            while(!inp.hasNextLine()){
                System.out.println("Invalid input. Please enter a valid integer.");
                System.out.println(prompt);
                inp.next(); //clear the invalid input
            }

            //once we reach here, we are sure the user has typed an integer
            int number = inp.nextInt();
            inp.nextLine(); // This consumes the leftover "enter" key press
            return number;
        }
    
}
