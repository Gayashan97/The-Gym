import javafx.application.Application;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Start {

    //main
    public static void main(String[] args) throws Exception{
        displayIntro();
        loginManager();
    }

    //intro
    public static void displayIntro(){
        System.out.println("----------  THE GYM  ----------\n");
    }

    //login to console
    public static void loginManager() throws Exception{
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter username:");
        String username=scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        if(username.equalsIgnoreCase("admin") & password.equals("admin@123")){
            displayMenu();
        }
        else{
            System.err.println("Wrong username or password!\n");
            loginManager();
        }

    }

    //displaying main menu
    public static void displayMenu() throws Exception{
        Scanner scanner=new Scanner(System.in);
        System.out.println("Please enter a number from 1 to 4 only!");
        System.out.println("Add a member       -   1");
        System.out.println("Delete a member    -   2");
        System.out.println("Print member list  -   3");
        System.out.println("Save member list   -   4");
        System.out.println("Exit application   -   0");

        int num=0;
        try {
            num=scanner.nextInt();
        }catch (InputMismatchException e){
            System.err.println("Wrong input!");
            displayMenu();
        }
        switch (num){
            case 1:
                if (!new MyGymManager().isFull())
                    addDetails();
                else displayMenu();
                break;
            case 2: deleteDetails();
                break;
            case 3: viewAll();
                break;
           case 4: new MyGymManager().saveMemberList();
                displayMenu();
                break;
            case 0:
                System.out.println("Thank you");
                System.exit(0);
            default:
                displayMenu();
        }
    }

    //add details
    public static void addDetails() throws Exception{
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter 1 to add a default member");
        System.out.println("Enter 2 to add a student member");
        System.out.println("Enter 3 to add a over 60 member");
        System.out.println("Enter 0 to exit");

        String ans=scanner.nextLine();

        switch (ans){
            case "1":
                try {
                    System.out.println("Enter membership ID : ");
                    String id=scanner.nextLine();
                    id="D".concat(id);
                    System.out.println("Enter member name : ");
                    String name=scanner.nextLine();
                    System.out.println("Enter membership start date : (dd-mm-yyyy)");
                    String date = scanner.nextLine();
                    Date date1=parseDate(date);
                    new MyGymManager().addDefaultMember(new DefaultMember(id,name,date1));
                    System.out.println();
                    displayMenu();
                }catch (InputMismatchException e){
                    System.err.println("Wrong input");
                    addDetails();
                }
                break;

            case "2":
                try{
                    System.out.println("Enter membership ID : ");
                    String id=scanner.nextLine();
                    id="S".concat(id);
                    System.out.println("Enter member name : ");
                    String name=scanner.nextLine();
                    System.out.println("Enter membership start date : (dd-mm-yyyy)");
                    String date = scanner.nextLine();
                    Date date1=parseDate(date);
                    System.out.println("Enter school name : ");
                    String schoolName=scanner.nextLine();
                    new MyGymManager().addStudentMember(new StudentMember(id,name,date1,schoolName));
                    System.out.println();
                    displayMenu();

                }catch (InputMismatchException e){
                    System.err.println("Wrong input");
                    addDetails();
                }
                break;

            case "3":
                try {
                    System.out.println("Enter membership ID : ");
                    String id=scanner.nextLine();
                    id="O".concat(id);
                    System.out.println("Enter member name : ");
                    String name=scanner.nextLine();
                    System.out.println("Enter membership start date : (dd-mm-yyyy)");
                    String date = scanner.nextLine();
                    Date date1=parseDate(date);
                    System.out.println("Enter age : ");
                    int age=scanner.nextInt();
                    new MyGymManager().addOver60Member(new Over60Member(id,name,date1,age));
                    System.out.println();
                    displayMenu();

                } catch (InputMismatchException e) {
                    System.err.println("Wrong input");
                    addDetails();
                }
                break;

            case "0" :
                displayMenu();
                break;

            default:
                System.out.println("Wrong input");
                addDetails();

        }
    }

    //delete details
    public static void deleteDetails() throws Exception{
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter 1 to delete a default member");
        System.out.println("Enter 2 to delete a student member");
        System.out.println("Enter 3 to delete an over 60 member");
        System.out.println("Enter 0 to exit");

        String ans=scanner.nextLine();
        switch (ans){
            case "1" :
                System.out.println("Enter membership no of member to delete :");
                String delete = scanner.nextLine();
                new MyGymManager().deleteDefaultMember(delete);
                System.out.println();
                displayMenu();
                break;

            case "2" :
                System.out.println("Enter membership no of member to delete :");
                delete=scanner.nextLine();
                new MyGymManager().deleteStudentMember(delete);
                System.out.println();
                displayMenu();
                break;

            case "3" :
                System.out.println("Enter membership no of member to delete :");
                delete = scanner.nextLine();
                new MyGymManager().deleteOver60Member(delete);
                System.out.println();
                displayMenu();
                break;

            case "0" :
                displayMenu();
                break;

            default :
                System.out.println("Wrong input");
                deleteDetails();
        }

    }

    //viewing all members in ascending order of name
    public static void viewAll() throws Exception{
        ArrayList<DefaultMember> defaultMembers=new MyGymManager().getDefaultMembers();
        ArrayList<StudentMember> studentMembers=new MyGymManager().getStudentMembers();
        ArrayList<Over60Member> over60Members=new MyGymManager().getOver60Members();
        ArrayList<DefaultMember> members=new ArrayList<>();
        members.addAll(defaultMembers);
        members.addAll(studentMembers);
        members.addAll(over60Members);
        members.sort(new NameSorter());
        System.out.println(members);
        System.out.println();
        displayMenu();
    }

    public static Date parseDate(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
        Date date1=null;
        try {
            //Parsing the String
            date1 = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }

}
