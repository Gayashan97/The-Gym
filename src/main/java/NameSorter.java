import java.util.Comparator;

public class NameSorter implements Comparator<DefaultMember> {

    //used to sort member names in ascending order
    @Override
    public int compare(DefaultMember o1, DefaultMember o2) {
        return o1.getMemberName().compareToIgnoreCase(o2.getMemberName());
    }
}
