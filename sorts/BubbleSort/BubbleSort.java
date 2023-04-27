package sorts.BubbleSort;
import java.util.Scanner;
 
 /**
  * @class BubbleSort
  * @brief Bubble sort algorithm.
  */
 public class BubbleSort implements SortAlgorithm {
 
     /*
      * @brief Main function.
      * @param args Command-line arguments.
      */
     public static void main(String[] args) {
         try (Scanner scanner = new Scanner(System.in)) {
             System.out.print("Enter the number of elements to sort: ");
             int n = scanner.nextInt();
             Integer[] array = new Integer[n];
             System.out.println("Enter " + n + " integers:");
             for (int i = 0; i < n; i++) {
                 array[i] = scanner.nextInt();
             }
             BubbleSort bubbleSort = new BubbleSort();
             Integer[] sortedArray = bubbleSort.sort(array);
             System.out.println("Sorted array:");
             for (int i = 0; i < n; i++) {
                 System.out.print(sortedArray[i] + " ");
             }
         }
     }
 
     /*
      * @brief Sorts the given array.
      * @param array The array to sort.
      * @return The sorted array.
      */
     @Override
     public <T extends Comparable<T>> T[] sort(T[] array) {
         /*
          * Bubble sort algorithm.
          *
          * Iterate through the array, comparing adjacent elements.
          * If two elements are in the wrong order, swap them.
          * Repeat this process until no more swaps are needed.
          */
         for (int i = 1, size = array.length; i < size; ++i) {
             /*
              * Flag to indicate if any swaps were made in the current iteration.
              */
             boolean swapped = false;
             for (int j = 0; j < size - i; ++j) {
                 /*
                  * Compare the current element with the next element.
                  * If they are in the wrong order, swap them.
                  */
                 if (greater(array[j], array[j + 1])) {
                     swap(array, j, j + 1);
                     swapped = true;
                 }
             }
             /*
              * If no swaps were made in the current iteration, the array is sorted.
              */
             if (!swapped) {
                 break;
             }
         }
         return array;
     }
 
     /*
      * @brief Compares two elements.
      * @param element1 The first element.
      * @param element2 The second element.
      * @return True if the first element is greater than the second element, false otherwise.
      */
     private <T extends Comparable<T>> boolean greater(T element1, T element2) {
         return element1.compareTo(element2) > 0;
     }
 
     /*
      * @brief Swaps two elements in an array.
      * @param array The array.
      * @param i The index of the first element.
      * @param j The index of the second element.
      */
     private <T> void swap(T[] array, int i, int j) {
         T temp = array[i];
         array[i] = array[j];
         array[j] = temp;
     }
 } 
