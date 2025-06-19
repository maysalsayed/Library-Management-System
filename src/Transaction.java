import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private int TransID;
    private int BookID;
    private int MemberID;
    private String BorrowDate;
    private String ReturnDate;
    private String Status;
    private String TransFileName = "Transactions.txt";
    String [] arrTrans;
    public Transaction() throws IOException {
        try {
            File file = new File(TransFileName);

            if (!file.exists()) {
                // Create the file (empty)
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.close(); // Just create and close
            }
            FileReader readerr = new FileReader(TransFileName);
            BufferedReader reader = new BufferedReader(readerr);

            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            this.arrTrans = lines.toArray(new String[0]);
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void borrowingBook(String [] membersArray, String [] booksArray, int memberID,int bookID){

    }

}
