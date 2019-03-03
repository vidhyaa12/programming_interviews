import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vidhyaa on 08/12/17.
 */
public class ListAddRemoveChecker {
    public static void main(String[] args) {
        List<String> items = new ArrayList<>();
        items.add("a");

//        System.out.println(Arrays.toString(items.toArray()));

        List<String> items2 = items;
        items.clear();
        items2.add("b");

//        System.out.println(Arrays.toString(items2.toArray()));

        List<String> items3 = new ArrayList<>(items2);
        items3.add("c");
//        System.out.println(Arrays.toString(items2.toArray()));

        System.out.println(Arrays.toString(items.toArray()));
        System.out.println(Arrays.toString(items2.toArray()));
        System.out.println(Arrays.toString(items3.toArray()));
    }
}
