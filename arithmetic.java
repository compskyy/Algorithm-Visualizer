import java.util.Scanner;

/**
 * @class arithmetic
 * @brief This class provides a function to calculate the sum of an arithmetic series.
 */
public class arithmetic {

    /**
     * @fn public static double sumOfSeries(double firstTerm, double commonDiff, int numOfTerms)
     * @brief Calculates the sum of the given arithmetic series.
     * @param firstTerm The first term of the series.
     * @param commonDiff The common difference of the series.
     * @param numOfTerms The number of terms in the series.
     * @return The sum of the series.
     */
    private static double sumOfSeries(
        double firstTerm,
        double commonDiff,
        int numOfTerms
    ) {
        // Calculates the sum of the given arithmetic series.
        return (
            numOfTerms / 2.0 * (2 * firstTerm + (numOfTerms - 1) * commonDiff)
        );
    }

    /**
     * @fn public static void main(String[] args)
     * @brief The main function of the class.
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        // Calculates the sum of the arithmetic series given by the command-line arguments and prints the result.
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the first term: ");
        double firstTerm = scanner.nextDouble();

        System.out.print("Enter the common difference: ");
        double commonDiff = scanner.nextDouble();

        System.out.print("Enter the number of terms: ");
        int numOfTerms = scanner.nextInt();

        double sum = sumOfSeries(firstTerm, commonDiff, numOfTerms);

        System.out.println("The sum of the arithmetic series is: " + sum);

        scanner.close();
    }
}
