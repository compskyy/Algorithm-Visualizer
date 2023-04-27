/**
 * @file PronicNumber.java
 *
 * @brief This file contains the implementation of a Java program to check if a given number is a pronic number or not.
 */

 import java.util.Scanner;

 /**
  * This class provides a method to check if a given number is a pronic number or not.
  */
 public class PronicNumber {
 
     /**
      * This method checks if the given number is a pronic number or not.
      *
      * @param input_number The input number to check.
      * @return true if the input number is a pronic number, false otherwise.
      */
     static boolean isPronic(int input_number) {
         for (int i = 0; i <= input_number; i++) {
             if (i * (i + 1) == input_number && i != input_number) {
                 return true;
             }
         }
         return false;
     }
 
     /**
      * This is the main method of the program. It reads an integer input from the user and checks if it's a pronic number or not.
      *
      * @param args The command line arguments (not used in this program).
      */
     public static void main(String[] args) {
         Scanner input = new Scanner(System.in);
 
         System.out.print("Enter a number: ");
         int input_number = input.nextInt();
 
         boolean isPronicNumber = isPronic(input_number);
 
         if (isPronicNumber) {
             System.out.println(input_number + " is a pronic number.");
         } else {
             System.out.println(input_number + " is not a pronic number.");
         }
 
         input.close();
     }
 } 
