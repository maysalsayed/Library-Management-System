import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookOperations {
    private static String BooksFileName = "Books.txt";
    private static String [] arrBooks = new String[1];
    //List<String> Books = new ArrayList<>();
    private static BookOperations instance;
    private static FileWriter file;
    private static FileReader readerr;
    private static BufferedReader reader;
    static {
        try {
            instance = new BookOperations();
            file = new FileWriter(BooksFileName);
            readerr = new FileReader(BooksFileName);
            reader = new BufferedReader(readerr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BookOperations() throws IOException {
        try {
            String read = reader.readLine();
            if(read == null) {
                read = "";
                arrBooks = new String[1];
            }
            else {
                String regex = "\n";
                this.arrBooks = read.split(regex);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void writeData(String [] arr) throws IOException {
        BufferedWriter writer = new BufferedWriter(file);
        for (String s:arr){
            writer.write(s+"\n");
        }
        writer.close();
    }
    public static String addBook(String BookTitle, String Author,String ISBN) throws IOException {
        for(String s:arrBooks){
            if(arrBooks.length==1 && arrBooks[0] == null)
                break;
            else {
                String[] splittedBook = s.split(",");
                if (splittedBook[1].equals(BookTitle) && splittedBook[2].equals(Author) && splittedBook.equals(ISBN))
                    return "The Book already exists";
            }
        }
        Book book;
        String [] Books;
        if(arrBooks.length == 1){
            book = new Book(arrBooks.length,BookTitle,Author,ISBN,true);
            Books = new String[arrBooks.length];
        }
        else {
            book = new Book(arrBooks.length+1,BookTitle,Author,ISBN,true);
            Books = new String[arrBooks.length+1];
        }

        for (int i = 0;i < Books.length;i++){
            if(i != Books.length - 1)
                Books[i] = arrBooks[i];
            else
                Books[i] = book.getBookID()+","+book.getBookTitle()+","+book.getAuthor()+","+book.getISBN()+","+book.getBookAvailability();
        }
        writeData(Books);
        return "Book Added Successfully!";
    }
    public void removeBook(String BookTitle, String Author,String ISBN){

    }
}
