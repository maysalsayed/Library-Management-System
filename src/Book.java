import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Book {
    String BooksFileName = "Books.txt";
    String[] arrBooks;
    static List<Book> listOfBooks = new ArrayList<>();
    private int BookID;
    private String BookTitle;
    private String Author;
    private String ISBN;
    private boolean isAvailable;

    public Book() throws IOException {
        try {
            File file = new File(BooksFileName);

            if (!file.exists()) {
                // Create the file (empty)
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.close(); // Just create and close
            }
            FileReader readerr = new FileReader(BooksFileName);
            BufferedReader reader = new BufferedReader(readerr);

            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            this.arrBooks = lines.toArray(new String[0]);
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getBookID(){
        return BookID;
    }
    public void setBookID(int BookID){
        this.BookID = BookID;
    }
    public String getBookTitle(){
        return BookTitle;
    }
    public void setBookTitle(String BookTitle){
        this.BookTitle = BookTitle;
    }
    public String getAuthor(){
        return Author;
    }
    public void setAuthor(String Author){
        this.Author = Author;
    }
    public String getISBN(){
        return ISBN;
    }
    public void setISBN(String ISBN){
        this.ISBN = ISBN;
    }
    public boolean getBookAvailability(){
        return isAvailable;
    }
    public void setBookAvailability(boolean isAvailable){
        this.isAvailable = isAvailable;
    }
    public void writeData(String[] arr) throws IOException {
        FileWriter file = new FileWriter(BooksFileName);
        BufferedWriter writer = new BufferedWriter(file);
        for (String s : arr) {
            writer.write(s + "\n");
        }
        writer.close();
    }

    public String addBook(String BookTitle, String Author, String ISBN) throws IOException {
        for (String s : this.arrBooks) {
            if (this.arrBooks.length == 1 && this.arrBooks[0] == null)
                break;
            else {
                String[] splittedBook = s.split(",");
                if (splittedBook[1].equalsIgnoreCase(BookTitle) && splittedBook[2].equalsIgnoreCase(Author) && splittedBook[3].equalsIgnoreCase(ISBN)) {
                    return "The Book already exists";
                }
            }
        }
        //Book book = new Book(this.arrBooks.length + 1, BookTitle, Author, ISBN, true);
        String[] Books = new String[this.arrBooks.length + 1];
        //listOfBooks.add(book);
        for (int i = 0; i < Books.length; i++) {
            if (i != Books.length - 1)
                Books[i] = this.arrBooks[i];
            else
                Books[i] = this.arrBooks[this.arrBooks.length -1].split(",")[0]+1 + "," + BookTitle + "," + Author + "," + ISBN + "," + true;
        }
        this.writeData(Books);
        return "Book Added Successfully!";
    }

    public String removeBook(String BookTitle, String Author, String ISBN) throws IOException {
        List<String> returnedBooks = new ArrayList<>();
        String returnedReply = "";
        boolean isRemoved = false;
        if (this.arrBooks.length == 0)
            returnedReply = "No Books.";
        else {
            for (int i = 0; i < this.arrBooks.length;i++) {
                String[] splittedBook = this.arrBooks[i].split(",");
                if (splittedBook[1].equalsIgnoreCase(BookTitle) && splittedBook[2].equalsIgnoreCase(Author) && splittedBook[3].equalsIgnoreCase(ISBN)) {
                    //listOfBooks.remove(i);
                    isRemoved = true;
                } else {
                    returnedBooks.add(this.arrBooks[i]);
                    returnedReply = "The Book Doesn't exist!";
                }

            }
            this.writeData(returnedBooks.toArray(new String[0]));
        }
        if (isRemoved)
            return "Book is Removed Successfully!";
        else
            return returnedReply;
    }

    public String editBook(String BookTitle, String Author, String ISBN, int modificationCriteria, String BookInfo) throws IOException {
        boolean match = false;
        if (this.arrBooks.length == 0) {
            return "No Books.";
        } else{
            for (int i = 0; i < this.arrBooks.length; i++) {
                String [] splitted = this.arrBooks[i].split(",");
                if (splitted[1].equalsIgnoreCase(BookTitle) && splitted[2].equalsIgnoreCase(Author) && splitted[3].equalsIgnoreCase(ISBN)) {
                    match = true;
                    if (modificationCriteria == 1) {
                        this.arrBooks[i] = splitted[0] + "," + BookInfo + "," + splitted[2] + "," + splitted[3] + "," + splitted[4];
                    } else if (modificationCriteria == 2) {
                        this.arrBooks[i] = splitted[0] + "," + splitted[1] + "," + BookInfo + "," + splitted[3] + "," + splitted[4];
                    } else if (modificationCriteria == 3) {
                        this.arrBooks[i] = splitted[0] + "," + splitted[1] + "," + splitted[2] + "," + BookInfo + "," + splitted[4];
                    } else {
                        return "You can only 1,2,or 3";
                    }
                }
            }
    }
        if (!match){
            return "The Book doesn't exist!";
        }
        this.writeData(this.arrBooks);
        return "The Book's info is Edited Successfully!";
    }

    public void listBooks(){
        System.out.println("Here's the list of all Books: ");
        for (String s:this.arrBooks){
            System.out.println(s);
        }
    }
}
