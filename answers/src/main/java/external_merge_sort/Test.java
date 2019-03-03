package external_merge_sort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by vidhyaa on 28/12/17.
 */
public class Test {

    public static void populateList(List l) {
        l.add(new Integer(10));
    }

    public static void main(String args[]) {
        // Don't use raw types WITHOUT type specification
        List<String> l = new ArrayList<String>();
        populateList(l);
        for (String s : l) {
            System.out.println(s);
        }

        System.out.println("Over");

        // Example 2 : Blunder set intersection
        Set<Long> setA = new HashSet<Long>();
        setA.add(1L);
        setA.add(2L);
        setA.add(3L);

        Set<Integer> setB = new HashSet<Integer>();
        setB.add(1);
        setB.add(2);
        setB.add(3);

        int commonElements = getCommonElements(setA, setB);
        System.out.println("Common elements = " + commonElements);
    }

    public static int getCommonElements(Set s1, Set s2) {
        int commonElements = 0;
        for (Object o1 : s1) {
            if (s2.contains(o1)) {
                commonElements++;
            }
        }
        return commonElements;
    }
}
