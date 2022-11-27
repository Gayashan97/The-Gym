import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class MyGymManager implements GymManager {

    private MongoCollection<Document> defaultMembers;
    private MongoCollection<Document> studentMembers;
    private MongoCollection<Document> over60Members;

    public MyGymManager() throws Exception{

        //getting mongo connection
        MongoDatabase mongoDatabase = MongoDBConnection.createConnection().getInstance();

        //creating collections
        defaultMembers = mongoDatabase.getCollection("default");
        studentMembers = mongoDatabase.getCollection("student");
        over60Members = mongoDatabase.getCollection("over60");
    }

    //checking if system is full
    public boolean isFull(){
        if((defaultMembers.count()+ studentMembers.count()+over60Members.count())>=100){
            System.out.println("No spaces available!");
            return true;
        }else return false;
    }

    //adding members to the system

    @Override
    public void addDefaultMember(DefaultMember defaultMember) throws Exception {
        try {
            Document document=new Document()
                    .append("memID",defaultMember.getMembershipID())
                    .append("memName",defaultMember.getMemberName())
                    .append("memStartDate",defaultMember.getStartMembershipDate());

            IndexOptions indexOptions = new IndexOptions().unique(true);
            defaultMembers.createIndex(Indexes.ascending("memID"),indexOptions);

            defaultMembers.insertOne(document);
            System.out.println("Successfully added member");

        }catch (MongoException e){
            System.err.println("Member already exists");
            Start.addDetails();
        }
    }

    @Override
    public void addStudentMember(StudentMember studentMember) throws Exception{
        try {
            Document document=new Document()
                    .append("memID",studentMember.getMembershipID())
                    .append("memName",studentMember.getMemberName())
                    .append("memStartDate",studentMember.getStartMembershipDate())
                    .append("schoolName",studentMember.getSchoolName());

            IndexOptions indexOptions = new IndexOptions().unique(true);
            studentMembers.createIndex(Indexes.ascending("memID"),indexOptions);

            studentMembers.insertOne(document);
            System.out.println("Successfully added member");

        }catch (MongoException e){
            System.out.println("Member already exists");
            Start.addDetails();
        }
    }

    @Override
    public void addOver60Member(Over60Member over60Member) throws Exception{
        try {
            Document document=new Document()
                    .append("memID",over60Member.getMembershipID())
                    .append("memName",over60Member.getMemberName())
                    .append("memStartDate",over60Member.getStartMembershipDate())
                    .append("age",over60Member.getAge());

            IndexOptions indexOptions = new IndexOptions().unique(true);
            over60Members.createIndex(Indexes.ascending("memID"),indexOptions);

            over60Members.insertOne(document);
            System.out.println("Successfully added member");

        }catch (MongoException e){
            System.out.println("Member already exists");
            Start.addDetails();
        }
    }

    //deleting members from the system

    @Override
    public void deleteDefaultMember(String memID) {
        defaultMembers.deleteOne(eq("memID", memID));
        System.out.println("Successfully deleted regular member with ID "+memID);
        long count=100-(defaultMembers.count()+studentMembers.count()+over60Members.count());
        System.out.println("Member spaces available : "+count);
    }

    @Override
    public void deleteStudentMember(String memID) {
        studentMembers.deleteOne(eq("memID", memID));
        System.out.println("Successfully deleted student member with ID "+memID);
        long count=100-(defaultMembers.count()+studentMembers.count()+over60Members.count());
        System.out.println("Member spaces available : "+count);
    }

    @Override
    public void deleteOver60Member(String memID) {
        over60Members.deleteOne(eq("memID", memID));
        System.out.println("Successfully deleted over 60 member with ID "+memID);
        long count=100-(defaultMembers.count()+studentMembers.count()+over60Members.count());
        System.out.println("Member spaces available : "+count);
    }

    //getting list of default members
    @Override
    public ArrayList<DefaultMember> getDefaultMembers() throws Exception {
        try {
            ArrayList<DefaultMember> defaultMemberArrayList = new ArrayList<>();
            List<Document> documents = defaultMembers.find().into(
                    new ArrayList<>());

            for (Document document : documents) {
                defaultMemberArrayList.add(new DefaultMember(
                        document.getString("memID"),
                        document.getString("memName"),
                        document.getDate("memStartDate")
                ));
            }
            return defaultMemberArrayList;
        } catch (MongoException ex) {
            throw new Exception("Mongo socket is busy");
        }
    }

    //getting list of student members
    @Override
    public ArrayList<StudentMember> getStudentMembers() throws Exception {
        try {
            ArrayList<StudentMember> studentMemberArrayList = new ArrayList<>();
            List<Document> documents = studentMembers.find().into(
                    new ArrayList<>());

            for (Document document : documents) {
                studentMemberArrayList.add(new StudentMember(
                        document.getString("memID"),
                        document.getString("memName"),
                        document.getDate("memStartDate"),
                        document.getString("schoolName")
                ));
            }
            return studentMemberArrayList;
        } catch (MongoException ex) {
            throw new Exception("Mongo socket is busy");
        }
    }

    //getting list of over 60 members
    @Override
    public ArrayList<Over60Member> getOver60Members() throws Exception {
        try {
            ArrayList<Over60Member> over60MemberArrayList = new ArrayList<>();
            List<Document> documents = over60Members.find().into(
                    new ArrayList<>());

            for (Document document : documents) {
                over60MemberArrayList.add(new Over60Member(
                        document.getString("memID"),
                        document.getString("memName"),
                        document.getDate("memStartDate"),
                        document.getInteger("age")
                ));
            }
            return over60MemberArrayList;
        } catch (MongoException ex) {
            throw new Exception("Mongo socket is busy");
        }
    }

    //writing member list to a text file
    @Override
    public void saveMemberList() throws Exception {
        FileWriter fileWriter=new FileWriter("MemberList",false);
        ArrayList<DefaultMember> defaultMembers=getDefaultMembers();
        ArrayList<StudentMember> studentMembers=getStudentMembers();
        ArrayList<Over60Member> over60Members=getOver60Members();
        ArrayList<DefaultMember> members=new ArrayList<>();
        members.addAll(defaultMembers);
        members.addAll(studentMembers);
        members.addAll(over60Members);
        members.sort(new NameSorter());
        String memberList=members.toString();
        fileWriter.write(memberList);
        System.out.println("File Write Successful");
        System.out.println();
        fileWriter.close();
    }
}
