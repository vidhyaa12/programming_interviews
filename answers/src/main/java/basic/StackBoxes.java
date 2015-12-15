package basic;

import java.util.Collections;
import java.util.List;

public class StackBoxes {
    public int getMaxStackHeight(List<Box> boxes) {
        Collections.sort(boxes, new BoxComparator());
        return 0;
    }

    public int getMaxStackHeight(List<Box> boxes, int bottomIndex, int[] solvedHeights) {
        if (bottomIndex < boxes.size() && solvedHeights[bottomIndex] > 0) {
            return solvedHeights[bottomIndex];
        }

        Box bottomBox = boxes.get(bottomIndex);
        int maxHeight = 0;

        for (int i = 0; i < boxes.size(); i++) {
            if (boxes.get(i).getLen() < bottomBox.getLen() &&
                    boxes.get(i).getBreadth() < bottomBox.getBreadth()) {
                maxHeight = Math.max(maxHeight, getMaxStackHeight(boxes, i, solvedHeights));
            }
        }

        maxHeight += bottomBox.getHeight();
        solvedHeights[bottomIndex] = maxHeight;
        return maxHeight;
    }
}
