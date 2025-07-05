import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Bank Management System ===");
            System.out.println("1. Create Account");
            System.out.println("2. View Account");
            System.out.println("3. Update Account");
            System.out.println("4. Delete Account");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: BankManagement.createAccount(); break;
                case 2: BankManagement.viewAccount(); break;
                case 3: BankManagement.updateAccount(); break;
                case 4: BankManagement.deleteAccount(); break;
                case 5: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid option.");
            }

        } while (choice != 5);

        scanner.close();
    }
}
