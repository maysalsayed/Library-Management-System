import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookOperations {
    String BooksFileName = "Books.txt";
    String [] arrBooks;
    //List<String> Books = new ArrayList<>();


    public BookOperations() throws IOException {
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
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void writeData(String [] arr) throws IOException {
        FileWriter file = new FileWriter(BooksFileName);
        BufferedWriter writer = new BufferedWriter(file);
        for (String s:arr){
            writer.write(s+"\n");
        }
        writer.close();
    }
    public String addBook(String BookTitle, String Author,String ISBN) throws IOException {
        for(String s:this.arrBooks){
            if(this.arrBooks.length==1 && this.arrBooks[0] == null)
                break;
            else {
                String[] splittedBook = s.split(",");
                if (splittedBook[1].equals(BookTitle) && splittedBook[2].equals(Author) && splittedBook[3].equals(ISBN)) {
                    return "The Book already exists";
                }
            }
        }
        Book book = new Book(this.arrBooks.length+1,BookTitle,Author,ISBN,true);
        String [] Books = new String[this.arrBooks.length+1];

        for (int i = 0;i < Books.length;i++){
            if(i != Books.length - 1)
                Books[i] = this.arrBooks[i];
            else
                Books[i] = book.getBookID()+","+book.getBookTitle()+","+book.getAuthor()+","+book.getISBN()+","+book.getBookAvailability();
        }
        writeData(Books);
        return "Book Added Successfully!";
    }
    public String removeBook(String BookTitle, String Author,String ISBN){
        for(String s:this.arrBooks){
            if(this.arrBooks.length==1 && this.arrBooks[0] == null)
                return "No Books.";
            else {
                String[] splittedBook = s.split(",");
                if (splittedBook[1].equals(BookTitle) && splittedBook[2].equals(Author) && splittedBook[3].equals(ISBN)) {
                    return "";
                }
                else {return "";}
            }
        }
    }
}
