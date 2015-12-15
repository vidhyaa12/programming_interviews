package basic;

import java.util.Comparator;

public class BoxComparator implements Comparator<Box> {
    public int compare(Box box, Box otherBox) {
        return otherBox.getHeight() - box.getHeight();
    }
}
