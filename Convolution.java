/**
 * @file Convolution.java
 * @brief Performs the convolution of two arrays.
 */

 import java.util.Scanner;

 public class Convolution {
 
     /**
      * @brief The main function.
      *
      * @param args The command-line arguments.
      */
     public static void main(String[] args) {
         // Create a scanner for input.
         Scanner input = new Scanner(System.in);
 
         // Prompt the user for the length of the first array.
         System.out.print("Enter the length of the first array: ");
         int len1 = input.nextInt();
 
         // Create an array to store the first array.
         double[] A = new double[len1];
 
         // Prompt the user for the elements of the first array.
         System.out.println("Enter the elements of the first array:");
         for (int i = 0; i < len1; i++) {
             A[i] = input.nextDouble();
         }
 
         // Prompt the user for the length of the second array.
         System.out.print("Enter the length of the second array: ");
         int len2 = input.nextInt();
 
         // Create an array to store the second array.
         double[] B = new double[len2];
 
         // Prompt the user for the elements of the second array.
         System.out.println("Enter the elements of the second array:");
         for (int i = 0; i < len2; i++) {
             B[i] = input.nextDouble();
         }
 
         // Close the scanner.
         input.close();
 
         // Calculate the convolution of the two arrays.
         double[] result = convolution(A, B);
 
         // Print the convolution result.
         System.out.println("Convolution Result:");
         for (int i = 0; i < result.length; i++) {
             System.out.print(result[i] + " ");
         }
     }
 
     /**
      * @brief Performs the convolution of two arrays.
      *
      * @param A The first array.
      * @param B The second array.
      * @return The convolution of the two arrays.
      */
     public static double[] convolution(double[] A, double[] B) {
         // Create an array to store the convolution result.
         double[] convolved = new double[A.length + B.length - 1];
 
         // Loop over the elements of the convolution result.
         for (int i = 0; i < convolved.length; i++) {
             // Initialize the sum of the products of the corresponding elements of the two arrays.
             double sum = 0;
 
             // Loop over the elements of the first array, starting at the current index of the convolution result.
             for (int j = Math.max(i - B.length + 1, 0); j < i + 1 && j < A.length; j++) {
                 // Add the product of the corresponding elements of the two arrays to the sum.
                 sum += A[j] * B[i - j];
             }
 
             // Store the sum in the current element of the convolution result.
             convolved[i] = sum;
         }
 
         // Return the convolution result.
         return convolved;
     }
 } 
