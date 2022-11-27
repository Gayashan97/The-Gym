import java.util.ArrayList;

public interface GymManager {

    void addDefaultMember(DefaultMember defaultMember) throws Exception;

    void addStudentMember(StudentMember studentMember) throws Exception;

    void addOver60Member(Over60Member over60Member) throws Exception;

    void deleteDefaultMember(String memID);

    void deleteStudentMember(String memID);

    void deleteOver60Member(String memID);

    ArrayList<DefaultMember> getDefaultMembers() throws Exception;

    ArrayList<StudentMember> getStudentMembers() throws Exception;

    ArrayList<Over60Member> getOver60Members() throws Exception;

    void saveMemberList() throws Exception;

}
