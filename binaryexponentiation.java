/**
 * @class binaryexponentiation
 * @brief This class provides a function to calculate the binary exponentiation of an integer.
 */
public class binaryexponentiation {

    /**
     * @fn public static int binPow(int a, int p)
     * @brief Calculates the binary exponentiation of the given integer.
     * @param a The base of the exponentiation.
     * @param p The exponent.
     * @return The result of the exponentiation.
     */
    public static int binPow(int a, int p) {
        // Calculates the binary exponentiation of the given integer.
        int res = 1;
        while (p > 0) {
            if ((p & 1) == 1) {
                res = res * a;
            }
            a = a * a;
            p >>>= 1;
        }
        return res;
    }

    /**
     * @fn public static void main(String[] args)
     * @brief The main function of the class.
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        // Calculates the binary exponentiation of the given integers and prints the result.
        if (args.length == 2) {
            int a = Integer.parseInt(args[0]);
            int p = Integer.parseInt(args[1]);
            int result = binPow(a, p);
            System.out.println("The result of " + a + " raised to the power of " + p + " is " + result);
        } else {
            System.out.println("Please provide two command-line arguments: a (the base) and p (the exponent).");
            System.exit(0);
        }
    }
}
