public class librarymangement {
    import java.sql.*;
import java.util.Scanner;

public class LibraryManagement {
    private static Connection connect() throws Exception {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "password");
    }

    // Add new book
    public static void addBook(String title, String author, String publisher) {
        try (Connection con = connect()) {
            String query = "INSERT INTO books(title, author, publisher, available) VALUES (?, ?, ?, true)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, title);
            pst.setString(2, author);
            pst.setString(3, publisher);
            pst.executeUpdate();
            System.out.println("✅ Book added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Display all books
    public static void viewBooks() {
        try (Connection con = connect()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM books");
            while (rs.next()) {
                System.out.println(rs.getInt("book_id") + " - " + rs.getString("title") + " by " + rs.getString("author"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Main menu
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n📚 Library Management System");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    System.out.print("Enter Publisher: ");
                    String publisher = sc.nextLine();
                    addBook(title, author, publisher);
                }
                case 2 -> viewBooks();
                case 3 -> System.exit(0);
                default -> System.out.println("❌ Invalid choice");
            }
        }
    }
}


}
