package basic;

/**
 * Created by vidhyaa on 01/12/17.
 */
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class RouteChecker {
    class ActionIdx {
        String action;
        int idx;

        public ActionIdx(String action, int idx) {
            this.action = action;
            this.idx = idx;
        }
    }

    public static void main(String[] args) {
        testIsValid2();
    }

    public static void testIsValid() {
        RouteChecker routeChecker = new RouteChecker();

        // p1, d1
        System.out.println(routeChecker.isValid(new String[]{"p1", "d1"}));

        // p0, d0
        System.out.println(routeChecker.isValid(new String[]{"p0", "d0"}));

        // p1, p2, d2, d1
        System.out.println(routeChecker.isValid(new String[]{"p1", "p2", "d2", "d1"}));

        // p1, d2, p2, d1. false as d2 appears  before p2
        System.out.println(routeChecker.isValid(new String[]{"p1", "d2", "p2", "d1"}));

        // p1, p2, d2, false as d1 is missing
        System.out.println(routeChecker.isValid(new String[]{"p1", "p2", "d2"}));
    }

    // Version2 written by Jaison
    public static void testIsValid2() {
        RouteChecker routeChecker = new RouteChecker();

        // p1, d1
        System.out.println(routeChecker.isValid2(new String[]{"p1", "d1"}));

        // p0, d0
        System.out.println(routeChecker.isValid2(new String[]{"p0", "d0"}));

        // p1, p2, d2, d1
        System.out.println(routeChecker.isValid2(new String[]{"p1", "p2", "d2", "d1"}));

        // p1, d2, p2, d1. false as d2 appears  before p2
        System.out.println(routeChecker.isValid2(new String[]{"p1", "d2", "p2", "d1"}));

        // p1, p2, d2, false as d1 is missing
        System.out.println(routeChecker.isValid2(new String[]{"p1", "p2", "d2"}));
    }

    public boolean isValid(String[] route) {
        Map<String, ActionIdx> actionIdxMap = new HashMap<String, ActionIdx>();

        for (int i = 0; i < route.length; i++) {
            actionIdxMap.put(route[i], new ActionIdx(route[i], i));
        }

        // validate whether idx of pick up exists and is < idx of drop off
        for (String action : actionIdxMap.keySet()) {
            ActionIdx actionIdx = actionIdxMap.get(action);
            int idx = actionIdx.idx;

            String pairActionPrefix = "";

            if (actionIdx.action.startsWith("p")) {
                pairActionPrefix = "d";
            } else {
                pairActionPrefix = "p";
            }

            String pairAction = pairActionPrefix + action.substring(1);

            // pair action did not exist
            if (!actionIdxMap.containsKey(pairAction)) {
                return false;
            }

            ActionIdx pairActionIdx = actionIdxMap.get(pairAction);

            // pick up appears after drop off and this is invalid.
            if (action.startsWith("d") && pairActionIdx.idx > idx) {
                return false;
            }
        }
        return true;
    }

    public boolean isValid2(String[] route) {
        Map<Integer, Integer> itemIdxToPickupStep = new HashMap<Integer, Integer>();
        for (int curStep = 0; curStep < route.length; curStep++) {
            if (route[curStep].charAt(0) == 'd') {
                int itemIdx = Integer.parseInt(route[curStep].substring(1));
                int pickupStep = itemIdxToPickupStep.getOrDefault(itemIdx, Integer.MAX_VALUE);
                if (pickupStep > curStep) {
                    return false;
                }
                itemIdxToPickupStep.remove(itemIdx); // we droppped off so clear this
            } else if (route[curStep].charAt(0) == 'p') {
                int itemIdx = Integer.parseInt(route[curStep].substring(1));
                if (itemIdxToPickupStep.containsKey(itemIdx)) {
                    //throw Exception("Multiple pickups found for item : " + itemIdx);
                }
                itemIdxToPickupStep.put(itemIdx, curStep);
            } else {
                // throw Exception("Unknown operation);
            }
        }

        if (itemIdxToPickupStep.size() > 0) {
            //throw Exception("Some items not dropped off in the route commands");
        }
        return true;
    }
}


/*
Your previous Plain Text content is preserved below:

isValidRoute(route) -> true/false
route = [pickup1, dropoff1] -> true
      = [p1, p2, d2, d1] -> true
      = [p1, d2, p2, d1] -> false
      = [p1, p2, d2] -> false
assume no duplicates ([p1, p1, d1])
 */