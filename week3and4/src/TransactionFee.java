import java.util.*;

class Transaction {
    String id;
    double fee;
    String timestamp; // HH:mm format

    public Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return id + ":" + fee + "@" + timestamp;
    }
}

public class TransactionFee {

    // 🔵 Bubble Sort (by fee)
    public static void bubbleSortByFee(List<Transaction> list) {
        int n = list.size();
        int passes = 0, swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break; // Early termination
        }

        System.out.println("Bubble Sort → Passes: " + passes + ", Swaps: " + swaps);
    }

    // 🟢 Insertion Sort (by fee + timestamp)
    public static void insertionSortByFeeAndTime(List<Transaction> list) {
        int shifts = 0;

        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            while (j >= 0 &&
                    (list.get(j).fee > key.fee ||
                            (list.get(j).fee == key.fee &&
                                    list.get(j).timestamp.compareTo(key.timestamp) > 0))) {

                list.set(j + 1, list.get(j));
                j--;
                shifts++;
            }

            list.set(j + 1, key);
        }

        System.out.println("Insertion Sort → Shifts: " + shifts);
    }

    // 🔴 High-fee outliers (> $50)
    public static List<Transaction> findHighFeeOutliers(List<Transaction> list) {
        List<Transaction> outliers = new ArrayList<>();

        for (Transaction t : list) {
            if (t.fee > 50) {
                outliers.add(t);
            }
        }

        return outliers;
    }

    // ⚙️ Processing logic
    public static void processTransactions(List<Transaction> list) {
        int size = list.size();

        if (size <= 100) {
            bubbleSortByFee(list);
        } else if (size <= 1000) {
            insertionSortByFeeAndTime(list);
        } else {
            System.out.println("Large dataset → Use advanced sort (Merge/Quick Sort)");
        }

        List<Transaction> outliers = findHighFeeOutliers(list);

        System.out.println("\nSorted Transactions:");
        for (Transaction t : list) {
            System.out.println(t);
        }

        System.out.println("\nHigh-fee Outliers (>50):");
        if (outliers.isEmpty()) {
            System.out.println("None");
        } else {
            for (Transaction t : outliers) {
                System.out.println(t);
            }
        }
    }

    // 🚀 Main method (Test)
    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();

        // Sample Input
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        System.out.println("Original Transactions:");
        for (Transaction t : transactions) {
            System.out.println(t);
        }

        System.out.println("\nProcessing...\n");

        processTransactions(transactions);
    }
}