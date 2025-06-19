import java.io.*;
import java.time.LocalDate;
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
    String[] arrTrans;

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
    public void writeData(String[] arr) throws IOException {
        FileWriter file = new FileWriter(TransFileName);
        BufferedWriter writer = new BufferedWriter(file);
        for (String s : arr) {
            writer.write(s + "\n");
        }
        writer.close();
    }
    public String[] borrowingBook(String[] membersArray, String[] booksArray, int memberID, int bookID) throws IOException {
        String[] returned = new String[1];
        String [] Transactions = new String[this.arrTrans.length+1];
        for (int i = 0; i < booksArray.length; i++) {
            if (Integer.parseInt(booksArray[i].split(",")[0]) == bookID) {
                if (Boolean.parseBoolean(booksArray[i].split(",")[4])) {
                    booksArray[i].split(",")[4] = "false";
                    for (int j = 0;j < Transactions.length;j++){
                        if (j != Transactions.length - 1)
                            Transactions[j] = this.arrTrans[j];
                        else{
                            if(Transactions.length == 1)
                                Transactions[j] = "1" + "," + bookID + "," + memberID + "," + String.valueOf(LocalDate.now()) + ",,Borrowed";
                            else
                                Transactions[j] = Integer.parseInt(this.arrTrans[this.arrTrans.length -1].split(",")[0])+1 + "," + bookID + "," + memberID + "," + String.valueOf(LocalDate.now()) + ",,Borrowed";
                        }

                    }
                    this.writeData(Transactions);
                    return booksArray;
                } else {
                    returned[0] = "The Book is not available for Borrowing!";
                    return returned;
                }
            }
        }

        returned[0] = "The Book doesn't Exist!";
        return returned;
    }

}
