import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Member {

    private int MemberID;
    private String MemberName;
    private String MemberEmail;
    private String MemberPhone;
    String MembersFileName = "Membes.txt";
    String [] arrMembers;
    public Member() throws IOException {
        try {
            File file = new File(MembersFileName);

            if (!file.exists()) {
                // Create the file (empty)
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.close(); // Just create and close
            }
            FileReader readerr = new FileReader(MembersFileName);
            BufferedReader reader = new BufferedReader(readerr);

            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            this.arrMembers = lines.toArray(new String[0]);
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getMemberID(){
        return MemberID;
    }
    public void setMemberID(int MemberID){
        this.MemberID = MemberID;
    }
    public String getMemberName(){
        return MemberName;
    }
    public void setMemberName(String MemberName){
        this.MemberName = MemberName;
    }
    public String getMemberEmail(){
        return MemberEmail;
    }
    public void setMemberEmail(String MemberEmail){
        this.MemberEmail = MemberEmail;
    }
    public String getMemberPhone(){
        return MemberPhone;
    }
    public void setMemberPhone(String MemberPhone){
        this.MemberPhone = MemberPhone;
    }

    public void writeData(String[] arr) throws IOException {
        FileWriter file = new FileWriter(MembersFileName);
        BufferedWriter writer = new BufferedWriter(file);
        for (String s : arr) {
            writer.write(s + "\n");
        }
        writer.close();
    }

    public String addMember(String MemberName, String MemberEmail, String MemberPhone) throws IOException {
        for (String s : this.arrMembers) {
            if (this.arrMembers.length == 1 && this.arrMembers[0] == null)
                break;
            else {
                String[] splittedMember = s.split(",");
                if (splittedMember[1].equalsIgnoreCase(MemberName) && splittedMember[2].equalsIgnoreCase(MemberEmail) && splittedMember[3].equalsIgnoreCase(MemberPhone)) {
                    return "The Member already exists";
                }
            }
        }
        String[] Members = new String[this.arrMembers.length + 1];
        //listOfBooks.add(book);
        for (int i = 0; i < Members.length; i++) {
            if (i != Members.length - 1)
                Members[i] = this.arrMembers[i];
            else
                Members[i] = Members.length+1 + "," + MemberName + "," + MemberEmail + "," + MemberPhone;
        }
        this.writeData(Members);
        return "Book Added Successfully!";
    }
}
