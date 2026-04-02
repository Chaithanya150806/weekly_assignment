import java.util.*;

class Client {
    String name;
    int riskScore;
    double accountBalance;

    public Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return name + ":" + riskScore + " ($" + accountBalance + ")";
    }
}

public class ClientRiskRanking {

    // 🔵 Bubble Sort (Ascending riskScore) + swap visualization
    public static void bubbleSortAscending(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {

                    // Print swap visualization
                    System.out.println("Swapping: " + arr[j] + " ↔ " + arr[j + 1]);

                    // Swap
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break; // Early stop
        }

        System.out.println("Total Swaps: " + swaps);
    }

    // 🟢 Insertion Sort (DESC riskScore + ASC accountBalance)
    public static void insertionSortDesc(Client[] arr) {
        int n = arr.length;
        int shifts = 0;

        for (int i = 1; i < n; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 &&
                    (arr[j].riskScore < key.riskScore ||
                            (arr[j].riskScore == key.riskScore &&
                                    arr[j].accountBalance > key.accountBalance))) {

                arr[j + 1] = arr[j];
                j--;
                shifts++;
            }

            arr[j + 1] = key;
        }

        System.out.println("Total Shifts: " + shifts);
    }

    // 🔴 Get Top N High Risk Clients
    public static List<Client> getTopRiskClients(Client[] arr, int topN) {
        List<Client> result = new ArrayList<>();

        for (int i = 0; i < Math.min(topN, arr.length); i++) {
            result.add(arr[i]);
        }

        return result;
    }

    // Utility print
    public static void printArray(Client[] arr) {
        for (Client c : arr) {
            System.out.println(c);
        }
    }

    // 🚀 Main
    public static void main(String[] args) {

        Client[] clients = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 2000),
                new Client("clientB", 50, 3000)
        };

        System.out.println("Original Clients:");
        printArray(clients);

        // 🔵 Bubble Sort
        System.out.println("\nBubble Sort (Ascending Risk):");
        bubbleSortAscending(clients);
        printArray(clients);

        // 🟢 Insertion Sort
        System.out.println("\nInsertion Sort (Descending Risk + Balance):");
        insertionSortDesc(clients);
        printArray(clients);

        // 🔴 Top 3 Risk Clients
        System.out.println("\nTop High-Risk Clients:");
        List<Client> topClients = getTopRiskClients(clients, 10);

        for (Client c : topClients) {
            System.out.println(c.name + " (" + c.riskScore + ")");
        }
    }
}