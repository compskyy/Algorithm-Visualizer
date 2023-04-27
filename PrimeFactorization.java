import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @class PrimeFactorization
 * @brief This class provides a function to calculate the prime factors of an integer.
 */
public class PrimeFactorization {

    /**
     * @fn public static List<Integer> pfactors(int n)
     * @brief Calculates the prime factors of the given integer.
     * @param n The integer to factorize.
     * @return A list of the prime factors of n.
     */
    public static List<Integer> pfactors(int n) {
        // Calculates the prime factors of the given integer.
        List<Integer> primeFactors = new ArrayList<>();
    
        if (n == 0) {
            // If n is 0, return an empty list.
            return primeFactors;
        }
    
        while (n % 2 == 0) {
            // While n is even, add 2 to the list of prime factors and divide n by 2.
            primeFactors.add(2);
            n /= 2;
        }
    
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            // For each odd integer i from 3 to the square root of n, do the following:
            while (n % i == 0) {
                // While n is divisible by i, add i to the list of prime factors and divide n by i.
                primeFactors.add(i);
                n /= i;
            }
        }
    
        if (n > 2) {
            // If n is greater than 2, add n to the list of prime factors.
            primeFactors.add(n);
        }
        return primeFactors;
    }

    /**
     * @fn public static void main(String[] args)
     * @brief The main function of the class.
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        // Calculates the prime factors of the given integers and prints the result.
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a positive integer: ");
            int n = scanner.nextInt();
            List<Integer> primeFactors = pfactors(n);
            System.out.println("Prime factors of " + n + " are: " + primeFactors);
        }
    }
}
