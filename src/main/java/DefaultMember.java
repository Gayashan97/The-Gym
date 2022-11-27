import java.util.Date;

public class DefaultMember {

    private String membershipID;
    private String memberName;
    private Date startMembershipDate;

    public DefaultMember(String membershipID, String memberName, Date startMembershipDate) {
        this.setMembershipID(membershipID);
        this.setMemberName(memberName);
        this.setStartMembershipDate(startMembershipDate);
    }

    //getters and setters
    public String getMembershipID() {
        return membershipID;
    }

    public void setMembershipID(String membershipID) {
        this.membershipID = membershipID;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public  Date getStartMembershipDate() {
        return startMembershipDate;
    }

    public void setStartMembershipDate(Date startMembershipDate) {
        this.startMembershipDate = startMembershipDate;
    }

    //printing default member details
    @Override
    public String toString() {
        return "\n Default Member{ MemberID : " + membershipID + " , " +
                "Member Name : " + memberName + " , " +
                " Membership Start Date : " + startMembershipDate +
                "}";
    }
}
