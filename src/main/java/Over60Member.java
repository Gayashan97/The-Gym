import java.util.Date;

public class Over60Member extends DefaultMember {

    private int age;

    public Over60Member(String membershipID, String memberName, Date startMembershipDate, int age) {
        super(membershipID,memberName,startMembershipDate);
        this.setAge(age);
    }

    //getters and setters
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //printing over 60 member details
    @Override
    public String toString() {
        return "\n Over 60 Member{ " + "MemberID : "+super.getMembershipID() + " , Member Name : "+super.getMemberName()+" , Membership Start Date : "+super.getStartMembershipDate()+" , Age : " + age + "}";
    }

}
