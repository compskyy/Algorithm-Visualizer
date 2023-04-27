package sorts.BubbleSort;

/**
 * @interface SortAlgorithm
 * @brief Sort algorithm interface.
 *
 * This interface defines the methods that must be implemented by a sort algorithm.
 */
public interface SortAlgorithm {

    /*
     * @brief Sorts the given array.
     * @param array The array to sort.
     * @return The sorted array.
     */
    <T extends Comparable<T>> T[] sort(T[] array);
}
