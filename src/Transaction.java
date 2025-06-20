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
    private final String TransFileName = "Transactions.txt";
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
        String[] Transactions = new String[this.arrTrans.length + 1];
        for (int i = 0; i < booksArray.length; i++) {
            if (Integer.parseInt(booksArray[i].split(",")[0]) == bookID) {
                String[] bookParts = booksArray[i].split(",");
                if (Boolean.parseBoolean(bookParts[4])) {
                    bookParts[4] = "false"; // Update availability
                    booksArray[i] = String.join(",", bookParts);
                    for (int j = 0; j < Transactions.length; j++) {
                        if (j != Transactions.length - 1)
                            Transactions[j] = this.arrTrans[j];
                        else {
                            if (Transactions.length == 1)
                                Transactions[j] = "1" + "," + bookID + "," + memberID + "," + String.valueOf(LocalDate.now()) + ",,Borrowed";
                            else
                                Transactions[j] = Integer.parseInt(this.arrTrans[this.arrTrans.length - 1].split(",")[0]) + 1 + "," + bookID + "," + memberID + "," + String.valueOf(LocalDate.now()) + ",,Borrowed";
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

    public String[] returningBook(String[] booksArray, String[] transArray, int choice, int ID) throws IOException {
        String[] returned = new String[1];
        boolean match = false;
        int index = 0;
        if (choice == 1) {
            for (int i = 0; i < transArray.length; i++) {
                if (Integer.parseInt(transArray[i].split(",")[0]) == ID) {
                    match = true;
                    index = Integer.parseInt(transArray[i].split(",")[1]);
                    String[] transParts = transArray[i].split(",");
                    transParts[4] = String.valueOf(LocalDate.now());
                    transParts[5] = "Returned";
                    transArray[i] = String.join(",", transParts);
                }
            }
            if (!match) {
                returned[0] = "The Transaction doesn't exist! You should use valid Trans ID.";
                return returned;
            } else {
                this.writeData(transArray);
                for (int i = 0; i < booksArray.length; i++) {
                    if (Integer.parseInt(booksArray[i].split(",")[0]) == index) {
                        String[] bookParts = booksArray[i].split(",");
                        if (!Boolean.parseBoolean(bookParts[4])) {
                            bookParts[4] = "true"; // Update availability
                            booksArray[i] = String.join(",", bookParts);
                            return booksArray;
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < transArray.length; i++) {
                if (Integer.parseInt(transArray[i].split(",")[1]) == ID) {
                    match = true;
                    for (int j = 0; j < booksArray.length; j++) {
                        if (!Boolean.parseBoolean(booksArray[j].split(",")[4]) && Integer.parseInt(booksArray[j].split(",")[0]) == ID) {
                            String[] bookParts = booksArray[j].split(",");
                            bookParts[4] = "true"; // Update availability
                            booksArray[j] = String.join(",", bookParts);
                            String[] transParts = transArray[i].split(",");
                            transParts[4] = String.valueOf(LocalDate.now());
                            transParts[5] = "Returned";
                            transArray[i] = String.join(",", transParts);
                        }

                    }
                    this.writeData(transArray);
                    return booksArray;
                }
            }
        }
        returned[0] = "The Transaction doesn't Exist! You should use valid Book ID";
        return returned;
    }
    public void listTran(){
        System.out.println("Here's the list of all Trans: ");
        for (String s:this.arrTrans){
            System.out.println(s);
        }
    }
}