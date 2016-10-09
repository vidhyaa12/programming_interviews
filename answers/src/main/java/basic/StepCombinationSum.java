package basic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * x + y + z = 1
 * x, y, z are in the range [0, 1] and are multiples of step size
 * step size = 0.5
 * Solve this for the n - dimensional case

 */
public class StepCombinationSum {
    private float stepSize;
    private int subsetSize;
    private float targetSum;

    public  StepCombinationSum(float stepSize, int subsetSize, float targetSum) {
        this.stepSize = stepSize;
        this.subsetSize = subsetSize;
        this.targetSum = targetSum;
    }

    public List<List<Float>> combinationSum() {
        List<List<Float>> result = new ArrayList<List<Float>>();
        dfs(result, 0, targetSum, new ArrayList<Float>());

        for (List<Float> combination : result) {
            System.out.println(Arrays.toString(combination.toArray()));
        }

        return result;
    }

    public void dfs(List<List<Float>> result, float start, double remainingSum, List<Float> currentSubset) {
        // Check for precision with step sizes that are between 0 and 1
        if (currentSubset.size() == subsetSize) {
            if (roundNumber(remainingSum) == 0.0) {
                List<Float> temp = new ArrayList<Float>();
                temp.addAll(currentSubset);
                result.add(temp);
            } else {
                System.out.println("RemainingSum at leafNode = " + remainingSum + " for solution : " + Arrays.toString(currentSubset.toArray()));
            }
        } else {
            for (float i = start; i <= remainingSum; i += stepSize) {
                currentSubset.add(i);
                dfs(result, start, roundNumber(remainingSum - i), currentSubset);
                currentSubset.remove(currentSubset.size() - 1);
            }
        }
    }

    private Double roundNumber(Double toBeTruncated) {
        return new BigDecimal(toBeTruncated)
                .setScale(1, BigDecimal.ROUND_HALF_UP)
                .doubleValue();
    }

    public static void main(String[] args) {
        StepCombinationSum stepCombinationSum = new StepCombinationSum(1.0f, 3, 7f);
        stepCombinationSum.combinationSum();
    }
}
