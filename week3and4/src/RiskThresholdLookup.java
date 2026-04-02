import java.util.*;

public class RiskThresholdLookup {

    // 🔵 LINEAR SEARCH (unsorted)
    public static int linearSearch(int[] arr, int target) {
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                System.out.println("Linear Search → Comparisons: " + comparisons);
                return i;
            }
        }

        System.out.println("Linear Search → Comparisons: " + comparisons);
        return -1;
    }

    // 🟢 BINARY SEARCH → insertion point (lower_bound)
    public static int lowerBound(int[] arr, int target) {
        int low = 0, high = arr.length;
        int comparisons = 0;

        while (low < high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        System.out.println("Lower Bound → Comparisons: " + comparisons);
        return low; // insertion index
    }

    // 🟢 BINARY SEARCH → upper_bound
    public static int upperBound(int[] arr, int target) {
        int low = 0, high = arr.length;
        int comparisons = 0;

        while (low < high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        System.out.println("Upper Bound → Comparisons: " + comparisons);
        return low;
    }

    // 🔴 FLOOR (largest ≤ target)
    public static Integer findFloor(int[] arr, int target) {
        int index = lowerBound(arr, target);

        if (index == 0) return null; // no floor
        return arr[index - 1];
    }

    // 🔴 CEILING (smallest ≥ target)
    public static Integer findCeiling(int[] arr, int target) {
        int index = lowerBound(arr, target);

        if (index == arr.length) return null; // no ceiling
        return arr[index];
    }

    // Utility print
    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    // 🚀 MAIN
    public static void main(String[] args) {

        int[] unsorted = {50, 10, 100, 25};
        int target = 30;

        System.out.println("Unsorted Risk Bands:");
        printArray(unsorted);

        // 🔵 Linear Search
        int linearIndex = linearSearch(unsorted, target);
        System.out.println("Linear Search Result → Index: " + linearIndex);

        // 🟢 Sort for Binary Search
        Arrays.sort(unsorted);

        System.out.println("\nSorted Risk Bands:");
        printArray(unsorted);

        // 🟢 Binary Search operations
        int lb = lowerBound(unsorted, target);
        int ub = upperBound(unsorted, target);

        System.out.println("Insertion Point (Lower Bound Index): " + lb);
        System.out.println("Upper Bound Index: " + ub);

        // 🔴 Floor & Ceiling
        Integer floor = findFloor(unsorted, target);
        Integer ceiling = findCeiling(unsorted, target);

        System.out.println("\nFloor(" + target + ") → " + (floor != null ? floor : "None"));
        System.out.println("Ceiling(" + target + ") → " + (ceiling != null ? ceiling : "None"));
    }
}