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
            if (this.arrMembers.length == 1 && this.arrMembers[0].equalsIgnoreCase(""))
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
            else {
                if(Members.length == 1)
                    Members[i] = "1" + "," + MemberName + "," + MemberEmail + "," + MemberPhone;
                else
                    Members[i] = Integer.parseInt(this.arrMembers[this.arrMembers.length - 1].split(",")[0]) + 1 + "," + MemberName + "," + MemberEmail + "," + MemberPhone;
            }
        }
        this.writeData(Members);
        return "Member Added Successfully!";
    }

    public String removeMember(String MemberName, String MemberEmail, String MemberPhone) throws IOException {
        List<String> returnedMembers = new ArrayList<>();
        String returnedReply = "";
        boolean isRemoved = false;
        if (this.arrMembers.length == 0)
            returnedReply = "No Members.";
        else {
            for (int i = 0; i < this.arrMembers.length;i++) {
                String[] splittedMember = this.arrMembers[i].split(",");
                if (splittedMember[1].equalsIgnoreCase(MemberName) && splittedMember[2].equalsIgnoreCase(MemberEmail) && splittedMember[3].equalsIgnoreCase(MemberPhone)) {
                    isRemoved = true;
                } else {
                    returnedMembers.add(this.arrMembers[i]);
                    returnedReply = "The Member doesn't exist!";
                }

            }
            this.writeData(returnedMembers.toArray(new String[0]));
        }
        if (isRemoved)
            return "Member is Removed Successfully!";
        else
            return returnedReply;
    }

    public String editMember(String MemberName, String MemberEmail, String MemberPhone, int modificationCriteria, String MemberInfo) throws IOException {
        boolean match = false;
        if (this.arrMembers.length == 0) {
            return "No Members.";
        } else{
            for (int i = 0; i < this.arrMembers.length; i++) {
                String [] splitted = this.arrMembers[i].split(",");
                 if (splitted[1].equalsIgnoreCase(MemberName) && splitted[2].equalsIgnoreCase(MemberEmail) && splitted[3].equalsIgnoreCase(MemberPhone)) {
                     match = true;
                    if (modificationCriteria == 1) {
                        this.arrMembers[i] = splitted[0] + "," + MemberInfo + "," + splitted[2] + "," + splitted[3];
                    } else if (modificationCriteria == 2) {
                        this.arrMembers[i] = splitted[0] + "," + splitted[1] + "," + MemberInfo + "," + splitted[3];
                    } else if (modificationCriteria == 3) {
                        this.arrMembers[i] = splitted[0] + "," + splitted[1] + "," + splitted[2] + "," + MemberInfo;
                    } else {
                        return "You can only 1,2,or 3";
                    }
                }
            }
        }
        if (!match){
            return "The Member doesn't exist";
        }
        this.writeData(this.arrMembers);
        return "The Members's info is Edited Successfully!";
    }

    public void listMembers(){
        System.out.println("Here's the list of all Members: ");
        for (String s:this.arrMembers){
            System.out.println(s);
        }
    }
}
