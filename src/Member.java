public class Member {

    private int MemberID;
    private String MemberName;
    private String MemberEmail;
    private String MemberPhone;

    public Member(int MemberID, String MemberName,String MemeberEmail, String MemberPhone){
        setMemberID(MemberID);
        setMemberName(MemberName);
        setMemberEmail(MemeberEmail);
        setMemberPhone(MemberPhone);
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
}
