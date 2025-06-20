import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Choose the number of the choice:
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
                ManageMembers();
            } else if (userAnswer == 3) {
                manageTransaction();
            } else if (userAnswer == 4) {
                break;
            }
            else {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public static void ManageBooks() throws IOException {
        System.out.println("""
        --- Book Management ---
        Choose the number of the choice:
        1. Add a book
        2. Remove a book
        3. Update a book
        4. List all books
        5. Search books
        6. Back to Main Menu
        """);
        int userAnswer = scanner.nextInt();
        scanner.nextLine();
        Book bookOperations = new Book();
        String [] returned;
        if (userAnswer == 1){
            returned = getBookInfo();
            System.out.println(bookOperations.addBook(returned[0], returned[1], returned[2]));
        }
        else if(userAnswer == 2){
            returned = getBookInfo();
            System.out.println(bookOperations.removeBook(returned[0],returned[1],returned[2]));
        }
        else if (userAnswer == 3){
            returned = getBookInfo();
            System.out.println("""
            What to modify?
            Choose the number of the choice:
            1.Book Title
            2.Book Author
            3.Book ISBN
            """);
            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter the new info needed to modify: ");
            String bookInfoToChange = scanner.nextLine();
            System.out.println(bookOperations.editBook(returned[0],returned[1],returned[2],choice,bookInfoToChange));
        }
        else if (userAnswer == 4){
            bookOperations.listBooks();
        }
        else if (userAnswer == 5){
            String Search_Data;
            while (true) {
                System.out.println("""
                        What do you want to use to search for the book?
                        Please write the number of the choice:
                        1. Book Title
                        2. Book Author
                        3. Book ISBN
                        """);
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (bookOperations.arrBooks.length == 0){
                    System.out.println("No Books to be Searched for!");
                    break;
                }
                if (choice == 1 || choice == 2 || choice ==3){
                    System.out.println("Please enter the data to search for: ");
                    Search_Data = scanner.nextLine();
                    List<String> Returned_Array = bookOperations.searchForBook(choice, Search_Data);
                    if (Returned_Array.isEmpty()) {
                        System.out.println("No Book with this Data.");
                        break;
                    }
                    else {
                        System.out.println(Returned_Array);
                        break;
                    }
                }
                else
                    System.out.println("Please enter a valid choice");
            }

        }
        else if (userAnswer == 6){
            //return to main menu
        }
        else {
            System.out.println("Please enter a valid number.");
        }

    }
    public static String [] getBookInfo(){
        String regex = "^97[89]\\d{10}";
        Pattern pattern = Pattern.compile(regex);
        String [] returned = new String[3];
        System.out.println("Enter Book Title: ");
        String BookTitle = scanner.nextLine();
        returned[0] = BookTitle;
        System.out.println("Enter Author Name: ");
        String AuthorName = scanner.nextLine();
        returned[1] = AuthorName;
        while (true) {
            System.out.println("Enter ISBN: ");
            String ISBN = scanner.nextLine();
            Matcher matcher = pattern.matcher(ISBN.trim());
            boolean isMatch = matcher.matches();
            if (isMatch) {
                returned[2] = ISBN;
                break;
            }
        }
        return returned;
    }

    public static void ManageMembers() throws IOException{
        System.out.println("""
        --- Member Management ---
        Choose the number of the choice:
        1. Add a Member
        2. Remove a Member
        3. Update Member Details
        4. List all Members
        5. Back to Main Menu
        """);
        int userAnswer = scanner.nextInt();
        scanner.nextLine();
        Member memberOperations = new Member();
        String [] returned;
        if (userAnswer == 1){
            returned = getMemberInfo();
            System.out.println(memberOperations.addMember(returned[0], returned[1], returned[2]));
        }
        else if(userAnswer == 2){
            returned = getMemberInfo();
            System.out.println(memberOperations.removeMember(returned[0],returned[1],returned[2]));
        }
        else if (userAnswer == 3){
            returned = getMemberInfo();
            System.out.println("""
            What to modify?
            Choose the number of the choice:
            1.Member Name
            2.Member Email
            3.Member Phone
            """);
            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter the new info needed to modify: ");
            String bookInfoToChange = scanner.nextLine();
            System.out.println(memberOperations.editMember(returned[0],returned[1],returned[2],choice,bookInfoToChange));
        }
        else if (userAnswer == 4){
            memberOperations.listMembers();
        }
        else if (userAnswer == 5){
            //return to main menu
        }
        else {
            System.out.println("Please enter a valid number.");
        }

    }

    public static String [] getMemberInfo(){
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        String [] returned = new String[3];
        System.out.println("Enter Member Name: ");
        returned[0]= scanner.nextLine();
        while (true) {
            System.out.println("Enter valid Member Email: ");
            String memberEmail = scanner.nextLine();
            Matcher matcher = pattern.matcher(memberEmail.trim());
            boolean isMatch = matcher.matches();
            if (isMatch) {
                returned[1] = memberEmail;
                break;
            }
        }
        regex = "^07[789]\\d{7}$";
        pattern = Pattern.compile(regex);
        while (true) {
            System.out.println("Enter Member Phone: ");
            String memberPhone = scanner.nextLine();
            Matcher matcher = pattern.matcher(memberPhone.trim());
            boolean isMatch = matcher.matches();
            if (isMatch) {
                returned[2] = memberPhone;
                break;
            }
        }
        return returned;
    }

    public static void manageTransaction() throws IOException {
        System.out.println("""
        ---  Borrow/Return  ---
        Choose the number of the choice:
        1. Borrow a Book
        2. Return a Book
        3. List all Transactions
        4. Back to Main Menu
        """);
        int userAnswer = scanner.nextInt();
        scanner.nextLine();
        Transaction transOperations = new Transaction();
        Member memberOperations = new Member();
        Book bookOperations = new Book();
        String [] returnedMembers = memberOperations.arrMembers;
        String [] returnedBooks = bookOperations.arrBooks;
        String [] returnedTrans;
        if (userAnswer == 1){
            System.out.println("Enter Member ID: ");
            int memberID= scanner.nextInt();
            System.out.println("Enter Book ID: ");
            int bookID = scanner.nextInt();
            if (bookOperations.arrBooks.length == 0){
                System.out.println("No Books exist.");
            }
            else {
                returnedTrans = transOperations.borrowingBook(returnedMembers, returnedBooks, memberID, bookID);
                if(returnedTrans.length == 1){
                    System.out.println(returnedTrans[0]);
                }
                else {
                    bookOperations.writeData(returnedTrans);
                    System.out.println("The Transaction added Successfully!");
                }
            }
        }
        else if(userAnswer == 2){
            System.out.println("""
            To Return a Book,Enter Transaction ID or Book ID, choose what to enter: 
            1. Transaction ID
            2. Book ID
            3. Return to Main menu
            """);
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1 || choice == 2){
                System.out.println("Enter the value: ");
                int ID = scanner.nextInt();
                scanner.nextLine();
                if (bookOperations.arrBooks.length == 0){
                    System.out.println("No Books exist.");
                } else if (transOperations.arrTrans.length == 0) {
                    System.out.println("The Book isn't Borrowed!");
                } else {
                    String [] returned = transOperations.returningBook(returnedBooks,transOperations.arrTrans, choice, ID);
                    if (returned.length == 1)
                        System.out.println(returned[0]);
                    else
                        bookOperations.writeData(returned);
                }
            }
            else {
                //return to main menu
            }

        } else if (userAnswer == 3) {
            transOperations.listTran();
        }
        else if(userAnswer == 4){
            //return to main menu
        }
    }

}