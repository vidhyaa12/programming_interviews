package basic;

/**
 * Created by vidhyaa on 20/10/18.
 */

// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM

import java.util.List;
import java.util.ArrayList;

// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
// CLASS BEGINS, THIS CLASS IS REQUIRED
public class CellCompete {
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public List<Integer> cellCompete(int[] states, int days) {
        // WRITE YOUR CODE HERE
        for (int i = 0; i < days; i++) {
            states = getStateForNextDay(states);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < states.length; i++) {
            result.add(states[i]);
        }
        return result;
    }
    // METHOD SIGNATURE ENDS

    public int[] getStateForNextDay(int[] states) {
        int[] resultStates = new int[states.length];
        if (states[1] == 0) {
            resultStates[0] = 0;
        } else {
            resultStates[0] = 1;
        }

        if (states[6] == 0) {
            resultStates[7] = 0;
        } else {
            resultStates[7] = 1;
        }

        for (int i = 1; i < 7; i++) {
            if (states[i - 1] == states[i + 1]) {
                resultStates[i] = 0;
            } else {
                resultStates[i] = 1;
            }
        }
        return resultStates;
    }
}