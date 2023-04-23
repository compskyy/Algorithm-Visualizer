// Java program for implementation of Selection Sort
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


    //Array from 1-200 in mixed order
    public static void shuffleArray(int[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    //Sound effects
    public void playSound(int pitch) {
        new Thread(() -> {
            if (sortingInProgress) {
                try {
                    Synthesizer synth = MidiSystem.getSynthesizer();
                    synth.open();
                    MidiChannel[] channels = synth.getChannels();
                    channels[0].noteOn(pitch, 80);
                    Thread.sleep(100);
                    channels[0].noteOff(pitch);
                    synth.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    //Heap sort FIXME
    void heapSort(int arr[], SelectionSortPanel panel) {
        int n = arr.length;
        int totalIterations = (n * (n - 1)) / 2;
        int sleepDuration = 1000000 / totalIterations;

        sortingInProgress = true;
        
    }
    
    //Selection Sorting
    void selectionSort(int arr[], SelectionSortPanel panel) {
        int n = arr.length;
        int totalIterations = (n * (n - 1)) / 2;
        int sleepDuration = 1000000 / totalIterations;
    
        sortingInProgress = true;
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min_idx])
                    min_idx = j;
            }
    
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
    
            // Update the panel and sleep
            try {
                SwingUtilities.invokeAndWait(() -> panel.setArray(arr.clone()));
                this.playSound(arr[i] + 20); // Adding 20 to avoid very low frequencies
                Thread.sleep(sleepDuration);
            } catch (InterruptedException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        sortingInProgress = false;
    }
    
    void bubbleSort(int arr[], SelectionSortPanel panel) {
        int n = arr.length;
        int totalIterations = (n * (n - 1)) / 2;
        int sleepDuration = 1000000 / totalIterations;
    
        sortingInProgress = true;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    
                // Update the panel and sleep
                try {
                    SwingUtilities.invokeAndWait(() -> panel.setArray(arr.clone()));
                    this.playSound(arr[j] + 20);
                    Thread.sleep(sleepDuration);
                } catch (InterruptedException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        sortingInProgress = false;
    }
    

	// Prints the array
	void printArray(int arr[])
	{
		int n = arr.length;
		for (int i=0; i<n; ++i)
			System.out.print(arr[i]+" ");
		System.out.println();
	}

	// Driver code to test above
// Driver code to test above
// Driver code to test above
public static void main(String args[]) {
    // Create an array with numbers 1-200
    int[] array = new int[200];
    for (int i = 0; i < 200; i++) {
        array[i] = i + 1;
    }

    // Shuffle the array
    shuffleArray(array);

    JFrame frame = new JFrame("Selection Sort Visualization");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(820, 700);

    frame.setLayout(new GridLayout(2, 2)); // Use GridLayout to arrange panels in a 2x2 grid

    main ob = new main();

    // Create four SelectionSortPanel instances and add them to the frame
    for (int i = 0; i < 4; i++) {
        SelectionSortPanel panel = new SelectionSortPanel(array.clone());
        frame.add(panel);
    
        // Run different sorting algorithms for each panel in a separate thread
        if (i == 0) { // Selection Sort
            new Thread(() -> ob.selectionSort(panel.getArray(), panel)).start();
        } else if (i == 1) { // Bubble Sort
            new Thread(() -> ob.bubbleSort(panel.getArray(), panel)).start();
        }
        // Add more sorting algorithms for other panel indices as needed
    }
    

    frame.setVisible(true);
    System.out.println("Sorted array");
    ob.printArray(array);
}


    
}

class SelectionSortPanel extends JPanel {
    private int[] array;

    public int[] getArray() {
        return this.array;
    }

    public SelectionSortPanel(int[] array) {
        this.array = array;
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        int arrayLength = array.length;
        int barWidth = panelWidth / arrayLength;
        int maxBarHeight = panelHeight;

        for (int i = 0; i < arrayLength; i++) {
            int barHeight = (int) ((double) array[i] / arrayLength * maxBarHeight);
            g.setColor(Color.PINK);
            g.fillRect(i * barWidth, panelHeight - barHeight, barWidth, barHeight);
        }
    }

    public void setArray(int[] array) {
        this.array = array;
        repaint();
    }
}




