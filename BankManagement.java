import java.sql.*;
import java.util.Scanner;

public class BankManagement {
    private static final Scanner scanner = new Scanner(System.in);

    public static void createAccount() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Account Number: ");
            String accNum = scanner.nextLine();

            System.out.print("Account Type (Saving/Current): ");
            String accType = scanner.nextLine();

            System.out.print("Initial Balance: ");
            double balance = scanner.nextDouble();
            scanner.nextLine(); // consume newline

            System.out.print("Contact: ");
            String contact = scanner.nextLine();

            String sql = "INSERT INTO customers (name, account_number, account_type, balance, contact) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, accNum);
            ps.setString(3, accType);
            ps.setDouble(4, balance);
            ps.setString(5, contact);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Account created successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewAccount() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("Enter account number: ");
            String accNum = scanner.nextLine();

            String sql = "SELECT * FROM customers WHERE account_number = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, accNum);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Account Number: " + rs.getString("account_number"));
                System.out.println("Type: " + rs.getString("account_type"));
                System.out.println("Balance: " + rs.getDouble("balance"));
                System.out.println("Contact: " + rs.getString("contact"));
            } else {
                System.out.println("Account not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateAccount() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("Enter account number: ");
            String accNum = scanner.nextLine();

            System.out.println("1. Deposit\n2. Withdraw\n3. Update Contact\n4. Change Account Type");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            String sql = null;
            PreparedStatement ps;

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double deposit = scanner.nextDouble();
                    sql = "UPDATE customers SET balance = balance + ? WHERE account_number = ?";
                    ps = conn.prepareStatement(sql);
                    ps.setDouble(1, deposit);
                    ps.setString(2, accNum);
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdraw = scanner.nextDouble();
                    sql = "UPDATE customers SET balance = balance - ? WHERE account_number = ?";
                    ps = conn.prepareStatement(sql);
                    ps.setDouble(1, withdraw);
                    ps.setString(2, accNum);
                    break;

                case 3:
                    System.out.print("Enter new contact: ");
                    String contact = scanner.nextLine();
                    sql = "UPDATE customers SET contact = ? WHERE account_number = ?";
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, contact);
                    ps.setString(2, accNum);
                    break;

                case 4:
                    System.out.print("Enter new account type: ");
                    String accType = scanner.nextLine();
                    sql = "UPDATE customers SET account_type = ? WHERE account_number = ?";
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, accType);
                    ps.setString(2, accNum);
                    break;

                default:
                    System.out.println("Invalid choice.");
                    return;
            }

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Account updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAccount() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("Enter account number to delete: ");
            String accNum = scanner.nextLine();

            String sql = "DELETE FROM customers WHERE account_number = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, accNum);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Account deleted successfully.");
            } else {
                System.out.println("Account not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
