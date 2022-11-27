import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class MyGymManagerTest {

    private MyGymManager myGymManager=new MyGymManager();

    MyGymManagerTest() throws Exception {
    }

    @Test
    void isFull(){
        assertFalse(myGymManager.isFull());
    }

    @Test
    void addDefaultMember() throws Exception {
        String date="12-09-2019";
        Date date1=Start.parseDate(date);
        DefaultMember defaultMember=new DefaultMember("D60","Osuri",date1);
        myGymManager.addDefaultMember(defaultMember);
    }

    @Test
    void addStudentMember() throws Exception{
        String date="12-09-2019";
        Date date1=Start.parseDate(date);
        StudentMember studentMember=new StudentMember("S50","Shane",date1,"Joseph");
        myGymManager.addStudentMember(studentMember);
    }

    @Test
    void addOver60Member() throws Exception{
        String date="12-09-2019";
        Date date1=Start.parseDate(date);
        Over60Member over60Member=new Over60Member("O90","Madush",date1,25);
        myGymManager.addOver60Member(over60Member);
    }

    @Test
    void deleteDefaultMember() {
        String dm="D01";
        myGymManager.deleteDefaultMember(dm);
    }

    @Test
    void deleteStudentMember() {
        String sm="S01";
        myGymManager.deleteStudentMember(sm);
    }

    @Test
    void deleteOver60Member() {
        String om="O01";
        myGymManager.deleteOver60Member(om);
    }

    @Test
    void getDefaultMembers() throws Exception{
        ArrayList <DefaultMember> defaultMembers=new ArrayList<>();
        defaultMembers=myGymManager.getDefaultMembers();
    }

    @Test
    void getStudentMembers() throws Exception{
        ArrayList <StudentMember> studentMembers=new ArrayList<>();
        studentMembers=myGymManager.getStudentMembers();
    }

    @Test
    void getOver60Members() throws Exception{
        ArrayList <Over60Member> over60Members=new ArrayList<>();
        over60Members=myGymManager.getOver60Members();
    }

    @Test
    void saveMemberList() throws Exception{
        myGymManager.saveMemberList();
    }
}

