package basic;

import java.util.ArrayList;
import java.util.List;

public class FactorCombinations {
    public class Solution {
        public List<List<Integer>> getFactors(int n) {
            List<List<Integer>> res = new ArrayList<List<Integer>>();
            dfs(res, new ArrayList<Integer>(), 2, n);
            return res;
        }

        public void dfs(List<List<Integer>> res, List<Integer> cur, int start, int n) {
            int upper = (int) Math.sqrt(n);
            for (int i = start; i <= upper; i++) {
                int factor = -1;
                if (n % i == 0) {
                    factor = n / i;
                }
                if (factor != -1 && factor >= i) {
                    cur.add(i);
                    cur.add(factor);
                    res.add(new ArrayList<Integer>(cur));
                    cur.remove(cur.size() - 1);
                    dfs(res, cur, i, factor);
                    cur.remove(cur.size() - 1);
                }
            }
        }
    }
}
