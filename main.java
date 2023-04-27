import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.lang.reflect.InvocationTargetException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;


public class main
{
    private volatile boolean sortingInProgress = false;


    /**
     * \brief Shuffles the elements of an array in place.
     *
     * \param array The array to shuffle.
     */
    public static void shuffleArray(int[] array) {
        // Create a random number generator.
        Random random = new Random();
    
        // Iterate over the array from the end to the beginning.
        for (int i = array.length - 1; i > 0; i--) {
        // Choose a random index between 0 and i.
        int index = random.nextInt(i + 1);
    
        // Swap the elements at the current index and the random index.
        int temp = array[index];
        array[index] = array[i];
        array[i] = temp;
        }
    }

    /**
     * @brief Plays a sound at the specified pitch.
     *
     * @param pitch The pitch of the sound to play.
     */
    void playSound(int pitch) {
        /**
         * @note This function will only play a sound if the sorting operation is in progress.
         */
        new Thread(() -> {
        try {
            /**
             * @brief Gets a synthesizer object.
             *
             * @return A synthesizer object.
             */
            Synthesizer synth = MidiSystem.getSynthesizer();
            synth.open();
            /**
             * @brief Gets the channels of the synthesizer object.
             *
             * @return An array of channels.
             */
            MidiChannel[] channels = synth.getChannels();
            /**
             * @brief Turns on a note on the specified channel.
             *
             * @param channel The channel to turn on the note on.
             * @param pitch The pitch of the note to turn on.
             */
            channels[0].noteOn(pitch, 80);
            Thread.sleep(100);
            /**
             * @brief Turns off a note on the specified channel.
             *
             * @param channel The channel to turn off the note on.
             * @param pitch The pitch of the note to turn off.
             */
            channels[0].noteOff(pitch);
            synth.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        }).start();
    }
    /**
     * @brief Sorts the given array using the heap sort algorithm.
     *
     * @param arr The array to sort.
     * @param panel The panel to display the sorting progress.
     */
    void heapSort(int arr[], SelectionSortPanel panel) {
        /**
         * @var n The length of the array.
         */
        int n = arr.length;

        /**
         * @var totalIterations The total number of iterations required to sort the array.
         */
        int totalIterations = (n * (n - 1)) / 2;

        /**
         * @var sleepDuration The duration to sleep between each iteration.
         */
        int sleepDuration = 1000000 / totalIterations;

        /**
         * @var sortingInProgress Flag indicating whether the sorting is in progress.
         */
        boolean sortingInProgress = true;

        try {
            for (int i = n / 2 - 1; i >= 0; i--) {
                heapify(arr, n, i, panel, true);
                Thread.sleep(sleepDuration);
            }
            for (int i = n - 1; i >= 0; i--) {
                int temp = arr[0];
                arr[0] = arr[i];
                arr[i] = temp;
                panel.repaint();
                heapify(arr, i, 0, panel, false);
                Thread.sleep(sleepDuration);
            }
        } catch (InterruptedException e) {
            System.out.println("Sorting interrupted");
        }
        sortingInProgress = false;
    }

    /**
     * @brief Performs a heapify operation on the given array.
     *
     * @param arr The array to heapify.
     * @param n The length of the array.
     * @param i The index of the root node.
     * @param panel The panel to display the sorting progress.
     * @param updateParent Flag indicating whether to update the parent node.
     */
    void heapify(int arr[], int n, int i, SelectionSortPanel panel, boolean updateParent) {
        /**
         * @var largest The index of the largest child node.
         */
        int largest = i;

        /**
         * @var l The index of the left child node.
         */
        int l = 2 * i + 1;

        /**
         * @var r The index of the right child node.
         */
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[largest]) {
            largest = l;
        }

        if (r < n && arr[r] > arr[largest]) {
            largest = r;
        }

        if (largest != i) {
            swap(arr, i, largest, panel);
            if (updateParent) {
                heapify(arr, n, largest, panel, true);
            }
        }
    }

    /**
     * @brief Swaps the elements at the given indexes in the array.
     *
     * @param arr The array to swap the elements in.
     * @param i The index of the first element to swap.
     * @param j The index of the second element to swap.
     * @param panel The panel to display the sorting progress.
     */
    void swap(int arr[], int i, int j, SelectionSortPanel panel) {
        /**
         * @var temp A temporary variable to store the first element.
         */
        int temp = arr[i];

        /**
         * Store the second element at the first index.
         */
        arr[i] = arr[j];

        /**
         * Store the first element at the second index.
         */
        arr[j] = temp;

        /**
         * Repaint the panel to reflect the change.
         */
        panel.repaint();
    }
    
    /**
     * @brief Sorts an array of integers using the selection sort algorithm.
     *
     * @param arr The array to sort.
     * @param panel The panel to display the sorting progress.
     */
    void selectionSort(int arr[], SelectionSortPanel panel) {
        /**
         * @brief The number of elements in the array.
         */
        int n = arr.length;
    
        /**
         * @brief The total number of iterations required to sort the array.
         */
        int totalIterations = (n * (n - 1)) / 2;
    
        /**
         * @brief The duration of each sleep in milliseconds.
         */
        int sleepDuration = 1000000 / totalIterations;
    
        /**
         * @brief A flag indicating whether the sorting is in progress.
         */
        boolean sortingInProgress = true;
    
        /**
         * Iterate over the array, starting at the beginning.
         */
        for (int i = 0; i < n - 1; i++) {
        /**
         * The index of the minimum element in the array, starting at the current index.
         */
        int min_idx = i;
    
        /**
         * Iterate over the array, starting at the current index + 1.
         */
        for (int j = i + 1; j < n; j++) {
            /**
             * If the current element is less than the minimum element, update the minimum element index.
             */
            if (arr[j] < arr[min_idx]) {
            min_idx = j;
            }
        }
    
        /**
         * Swap the current element with the minimum element.
         */
        int temp = arr[min_idx];
        arr[min_idx] = arr[i];
        arr[i] = temp;
    
        /**
         * Update the panel to display the new sorted array.
         */
        try {
            SwingUtilities.invokeAndWait(() -> panel.setArray(arr.clone()));
            this.playSound(arr[i] + 20);
            Thread.sleep(sleepDuration);
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }
        }
    
        /**
         * Set the sorting in progress flag to false.
         */
        sortingInProgress = false;
    }
    
    /**
     * @brief Sorts an array of integers using the bubble sort algorithm.
     *
     * @param arr The array to sort.
     * @param panel The panel to display the sorting progress.
     */
    void bubbleSort(int arr[], SelectionSortPanel panel) {
        /**
         * @brief The number of elements in the array.
         */
        int n = arr.length;
    
        /**
         * @brief The total number of iterations required to sort the array.
         */
        int totalIterations = (n * (n - 1)) / 2;
    
        /**
         * @brief The duration of each sleep in milliseconds.
         */
        int sleepDuration = 1000000 / totalIterations;
    
        /**
         * @brief A flag indicating whether the sorting is in progress.
         */
        boolean sortingInProgress = true;
    
        /**
         * Iterate over the array, starting at the beginning.
         */
        for (int i = 0; i < n - 1; i++) {
        /**
         * Iterate over the array, starting at the beginning and ending at the current index.
         */
        for (int j = 0; j < n - 1 - i; j++) {
            /**
             * If the current element is greater than the next element, swap them.
             */
            if (arr[j] > arr[j + 1]) {
            int temp = arr[j];
            arr[j] = arr[j + 1];
            arr[j + 1] = temp;
            }
    
                try {
                SwingUtilities.invokeAndWait(() -> panel.setArray(arr.clone()));
                this.playSound(arr[j] + 20);
                Thread.sleep(sleepDuration);
                } catch (InterruptedException | InvocationTargetException e) {
                e.printStackTrace();
                }
            }
        }
    
        /**
         * Set the sorting in progress flag to false.
         */
        sortingInProgress = false;
    }
    

    /**
     * \brief Prints the elements of an array to standard output.
     *
     * \param arr The array to print.
     */
    void printArray(int arr[]) {
        // Get the length of the array.
        int n = arr.length;
    
        // Iterate over the array and print each element.
        for (int i = 0; i < n; ++i) {
        System.out.print(arr[i] + " ");
        }
    
        // Print a newline character.
        System.out.println();
    }

/**
 * \brief The main function of the program.
 *
 * \param args The command-line arguments.
 */
public static void main(String[] args) {
    // Create an array of 200 elements.
    int[] array = new int[200];
  
    // Initialize the elements of the array to their index + 1.
    for (int i = 0; i < 200; i++) {
      array[i] = i + 1;
    }
  
    // Shuffle the elements of the array.
    shuffleArray(array);
  
    // Create a JFrame with the title "Selection Sort Visualization".
    JFrame frame = new JFrame("Selection Sort Visualization");
  
    // Set the default close operation of the frame to exit the program.
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
    // Set the size of the frame to 820 x 700 pixels.
    frame.setSize(820, 700);
  
    // Set the layout of the frame to a 2x2 grid layout.
    frame.setLayout(new GridLayout(2, 2));
  
    // Create a new instance of the main class.
    main ob = new main();
  
    // Create four SelectionSortPanel objects.
    for (int i = 0; i < 4; i++) {
      SelectionSortPanel panel = new SelectionSortPanel(array.clone());
      frame.add(panel);
  
      // If the current index is 0, start a new thread to sort the array using selection sort.
      if (i == 0) {
        new Thread(() -> ob.selectionSort(panel.getArray(), panel)).start();
      } else if (i == 1) {
        new Thread(() -> ob.bubbleSort(panel.getArray(), panel)).start();
      }
    }
  
    // Make the frame visible.
    frame.setVisible(true);
  
    // Print a message to standard output indicating that the array is sorted.
    System.out.println("Sorted array");
  
    // Print the array to standard output.
    ob.printArray(array);
  }  
}

/**
 * \brief A panel that visualizes the selection sort algorithm.
 */
class SelectionSortPanel extends JPanel {

    /**
     * \brief The array of integers to be sorted.
     */
    private int[] array;
  
    /**
     * \brief Gets the array of integers to be sorted.
     *
     * \return The array of integers to be sorted.
     */
    public int[] getArray() {
      return this.array;
    }
  
    /**
     * \brief Creates a new SelectionSortPanel object.
     *
     * \param array The array of integers to be sorted.
     */
    public SelectionSortPanel(int[] array) {
      this.array = array;
      setBackground(Color.WHITE);
    }
  
    /**
     * \brief Paints the component.
     *
     * \param g The graphics context.
     */
    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
  
      // Get the width and height of the panel.
      int panelWidth = getWidth();
      int panelHeight = getHeight();
  
      // Get the length of the array.
      int arrayLength = array.length;
  
      // Get the width of each bar.
      int barWidth = panelWidth / arrayLength;
  
      // Get the maximum height of each bar.
      int maxBarHeight = panelHeight;
  
      // Iterate over the array and draw a bar for each element.
      for (int i = 0; i < arrayLength; i++) {
        // Get the height of the bar.
        int barHeight = (int) ((double) array[i] / arrayLength * maxBarHeight);
  
        // Set the color of the graphics context to pink.
        g.setColor(Color.PINK);
  
        // Fill a rectangle for the bar.
        g.fillRect(i * barWidth, panelHeight - barHeight, barWidth, barHeight);
      }
    }
  
    /**
     * \brief Sets the array of integers to be sorted.
     *
     * \param array The array of integers to be sorted.
     */
    public void setArray(int[] array) {
      this.array = array;
      repaint();
    }
  }
