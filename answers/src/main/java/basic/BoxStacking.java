package basic;

import java.util.*;

public class BoxStacking {

    public static int getMaxHeight(List<Box> boxes) {
        int N = boxes.size();
        int[] boxStackHeights = new int[N];
        int[] boxPredecessorIdx = new int[N];
        List<Box> boxPredecessors = new ArrayList<Box>();

        Map<Box, Integer> indicesMapping = new HashMap<Box, Integer>();
        List<Box> savedBoxes = new ArrayList<Box>(boxes.size());
        savedBoxes.addAll(boxes);

        for (int i = 0; i < N; i++) {
            indicesMapping.put(boxes.get(i), i);
        }

        Collections.sort(boxes, new BoxComparator());

        for (int i = 0; i < N; i++) {
            boxStackHeights[i] = boxes.get(i).getHeight();
            boxPredecessorIdx[i] = -1;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if ((boxes.get(i).getLen() < boxes.get(j).getLen()) &&
                        (boxes.get(i).getBreadth() < boxes.get(j).getBreadth()) &&
                        (boxes.get(i).getHeight() < boxes.get(j).getHeight())) {
                    boxStackHeights[i] = boxStackHeights[j] + boxes.get(i).getHeight();
                    boxPredecessorIdx[i] = j;
                    boxPredecessors.add(boxes.get(j));
                }
            }
        }

        int globalMax = 0;
        int maxBoxStackEndIndex = 0;
        for (int i = 0; i < N; i++) {
            if (boxStackHeights[i] > globalMax) {
                globalMax = boxStackHeights[i];
                maxBoxStackEndIndex = i;
            }
        }

        int index = maxBoxStackEndIndex;
        List<Integer> result = new ArrayList<Integer>();

        while (index >= 0) {
            result.add(indicesMapping.get(boxes.get(index)));
            index = boxPredecessorIdx[index];
        }

        Collections.reverse(result);

        System.out.println("BoxStacking order is " + Arrays.toString(result.toArray()));

        return globalMax;
    }

    public static void main(String[] args) {
        List<Box> boxesList1 = Arrays.asList(new Box(8, 1, 6), new Box(5, 3, 2), new Box(4, 2, 1));
        System.out.println("max height = " + getMaxHeight(boxesList1));

        System.out.println();

        List<Box> boxesList2 = Arrays.asList(new Box(8, 1, 3), new Box(5, 3, 4), new Box(4, 2, 1));
        System.out.println("max height = " + getMaxHeight(boxesList2));

        System.out.println();

        List<Box> boxesList3 = Arrays.asList(new Box(8, 1, 1), new Box(5, 3, 1), new Box(4, 6, 2), new Box(1, 1, 1));
        System.out.println("max height = " + getMaxHeight(boxesList3));

        System.out.println();

        List<Box> boxesList4 = Arrays.asList(new Box(4, 2, 1), new Box(5, 3, 2), new Box(8, 1, 6));
        System.out.println("max height = " + getMaxHeight(boxesList4));

        System.out.println();

        List<Box> boxesListA = Arrays.asList(new Box(1, 1, 1), new Box(2, 2, 2), new Box(3, 3, 3));
        System.out.println("max height = " + getMaxHeight(boxesListA));

        System.out.println();

        List<Box> boxesListB = Arrays.asList(new Box(3, 3, 3), new Box(2, 2, 2), new Box(1, 1, 1));
        System.out.println("max height = " + getMaxHeight(boxesListB));
    }
}
