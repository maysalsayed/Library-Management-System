import java.io.IOException;
import java.util.Scanner;

public class Main{
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        try{
            startingOptions();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void startingOptions() throws IOException {
        while (true) {
            System.out.println("""
        === Library Management System ===
        1. Manage Books
        2. Manage Members
        3. Borrow/Return Books
        4. Exit
        """);
            int userAnswer = scanner.nextInt();
            //scanner.next();
            if (userAnswer == 1) {
                ManageBooks();
            } else if (userAnswer == 2) {

            } else if (userAnswer == 3) {

            } else if (userAnswer == 4) {
                break;
            }
        }
    }

    public static void ManageBooks() throws IOException {
        System.out.println("""
        --- Book Management ---
        1. Add a book
        2. Remove a book
        3. Update a book
        4. Search books
        5. List all books
        6. Back to Main Menu
        """);
        int userAnswer = scanner.nextInt();
        scanner.nextLine();
        BookOperations bookOperations = new BookOperations();
        if (userAnswer == 1){
            System.out.println("Enter Book Title: ");
            String BookTitle = scanner.nextLine();
            System.out.println("Enter Author Name: ");
            String AuthorName = scanner.nextLine();
            System.out.println("Enter ISBN: ");
            String ISBN = scanner.nextLine();
            System.out.println(bookOperations.addBook(BookTitle,AuthorName,ISBN));

        }
        else if(userAnswer == 2){

        }
        else if (userAnswer == 3){

        }
        else if (userAnswer == 4){

        }
        else if (userAnswer == 5){

        }
        else if (userAnswer == 6){

        }

    }
}