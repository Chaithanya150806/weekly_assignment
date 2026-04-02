import java.util.*;

public class AccountIdLookup {

    // 🔵 LINEAR SEARCH → First Occurrence
    public static int linearSearchFirst(String[] arr, String target) {
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                System.out.println("Linear First → Comparisons: " + comparisons);
                return i;
            }
        }

        System.out.println("Linear First → Comparisons: " + comparisons);
        return -1;
    }

    // 🔵 LINEAR SEARCH → Last Occurrence
    public static int linearSearchLast(String[] arr, String target) {
        int comparisons = 0;
        int result = -1;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                result = i;
            }
        }

        System.out.println("Linear Last → Comparisons: " + comparisons);
        return result;
    }

    // 🟢 BINARY SEARCH → First Occurrence
    public static int binarySearchFirst(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int comparisons = 0;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid].equals(target)) {
                result = mid;
                high = mid - 1; // move left
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Binary First → Comparisons: " + comparisons);
        return result;
    }

    // 🟢 BINARY SEARCH → Last Occurrence
    public static int binarySearchLast(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int comparisons = 0;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid].equals(target)) {
                result = mid;
                low = mid + 1; // move right
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Binary Last → Comparisons: " + comparisons);
        return result;
    }

    // 🔴 COUNT OCCURRENCES (using binary results)
    public static int countOccurrences(String[] arr, String target) {
        int first = binarySearchFirst(arr, target);
        int last = binarySearchLast(arr, target);

        if (first == -1) return 0;
        return last - first + 1;
    }

    // Utility print
    public static void printArray(String[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    // 🚀 MAIN
    public static void main(String[] args) {

        String[] logs = {"accB", "accA", "accB", "accC"};

        System.out.println("Original Logs:");
        printArray(logs);

        // 🔵 Linear Search
        int firstLinear = linearSearchFirst(logs, "accB");
        int lastLinear = linearSearchLast(logs, "accB");

        System.out.println("Linear First accB → Index: " + firstLinear);
        System.out.println("Linear Last accB → Index: " + lastLinear);

        // 🟢 Sort for Binary Search
        Arrays.sort(logs);

        System.out.println("\nSorted Logs:");
        printArray(logs);

        // 🟢 Binary Search
        int firstBinary = binarySearchFirst(logs, "accB");
        int lastBinary = binarySearchLast(logs, "accB");

        int count = countOccurrences(logs, "accB");

        System.out.println("Binary First accB → Index: " + firstBinary);
        System.out.println("Binary Last accB → Index: " + lastBinary);
        System.out.println("Total Occurrences of accB → " + count);
    }
}