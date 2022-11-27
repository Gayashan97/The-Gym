import java.util.Date;

public class StudentMember extends DefaultMember {

    private String schoolName;

    public StudentMember(String membershipID, String memberName, Date startMembershipDate, String schoolName) {
        super(membershipID,memberName,startMembershipDate);
        this.setSchoolName(schoolName);
    }

    //getters and setters
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    //printing student member details
    @Override
    public String toString() {
        return "\n Student Member{ " + "MemberID : "+super.getMembershipID() + " , Member Name : "+super.getMemberName()+" , Membership Start Date : "+super.getStartMembershipDate()+" , School Name : " + schoolName + "}";
    }

}
