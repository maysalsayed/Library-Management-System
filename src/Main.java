import java.io.IOException;
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

        }
        else if (userAnswer == 6){

        }
        else {
            System.out.println("Please enter a valid number.");
        }

    }
    public static String [] getBookInfo(){
        String regex = "\\b(?:ISBN(?:-13)?:?\\s*)?(97[89])[- ]?\\d{1,5}[- ]?\\d{1,7}[- ]?\\d{1,7}[- ]?\\d\\b\n";
        Pattern pattern = Pattern.compile(regex);
        String [] returned = new String[3];
        System.out.println("Enter Book Title: ");
        String BookTitle = scanner.nextLine();
        returned[1] = BookTitle;
        System.out.println("Enter Author Name (): ");
        String AuthorName = scanner.nextLine();
        returned[2] = AuthorName;
        while (true) {
            System.out.println("Enter ISBN: ");
            String ISBN = scanner.nextLine();
            Matcher matcher = pattern.matcher(ISBN.trim());
            boolean isMatch = matcher.matches();
            if (isMatch) {
                returned[3] = ISBN;
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

        }
        else {
            System.out.println("Please enter a valid number.");
        }

    }

    public static String [] getMemberInfo(){
        System.out.println("Enter Member Name: ");
        String memberName = scanner.nextLine();
        System.out.println("Enter Member Email: ");
        String memberEmail = scanner.nextLine();
        System.out.println("Enter Member Phone: ");
        String memberPhone = scanner.nextLine();
        String [] returned = {memberName, memberEmail, memberPhone};
        return returned;
    }
}