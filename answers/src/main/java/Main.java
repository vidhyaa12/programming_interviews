import java.util.*;
import java.util.Scanner;

public class Main {

    /*
     * DEPEND TELNET TCPIP NETCARD
     * DEPEND TCPIP NETCARD
     * DEPEND NETCARD TCPIP
     */
    private static final String DEPEND = "DEPEND";
    private static final String REMOVE = "REMOVE";
    private static final String INSTALL = "INSTALL";
    private static final String LIST = "LIST";

    // This function creates an adjacency list for the dependency graph and checks if the input is valid.
    // Input is valid if there are no cyclic dependencies.
    static boolean validateGraph(Map<String, List<String>> depGraph) {
        Map<String, Integer> visitedState = new HashMap<>();
        Set<String> nodeSet = new HashSet<String>();
        nodeSet.addAll(depGraph.keySet());
        for (List<String> depList : depGraph.values()) {
            if (depList != null) {
                for (String dep : depList) {
                    nodeSet.add(dep);
                }
            }
        }

        for (String node : nodeSet) {
            visitedState.put(node, 0);
        }

        for (String node : nodeSet) {
            if (!validateAndRunDependencies(node, depGraph, visitedState)) {
                return false;
            }
        }
        return true;
    }

    // DFS with cycle check
    static boolean validateAndRunDependencies(String node,
                                              Map<String, List<String>> depGraph,
                                              Map<String, Integer> visitedState) {
        if (visitedState.get(node) == -1) {
            return false;
        } else if (visitedState.get(node) == 1) {
            return true;
        }

        visitedState.put(node, -1);
        List<String> deps = depGraph.get(node);
        if (deps != null) {
            for (String dep : deps) {
                if (!validateAndRunDependencies(dep, depGraph, visitedState)) {
                    return false;
                }
            }
        }
        visitedState.put(node, 1);

        return true;
    }

    public static void main(String[] args) {

        Map<String, List<String>> depGraph = new HashMap<String, List<String>>();
        Set<String> installedDeps = new HashSet<String>();
        Map<String, List<String>> revDepGraph = null;

        Scanner sc = new Scanner(System.in);
        String line;

        while (sc.hasNextLine()) {
            line = sc.nextLine();
            System.out.println(line);

            // parse & validate command and execute if valid

            // check if depend command is valid and execute
            if (line.startsWith(DEPEND)) {
                String[] tokens = line.split(" ");
                String node = tokens[1];
                if (!depGraph.containsKey(node)) {
                    depGraph.put(node, new ArrayList<String>());
                }
                List<String> adjList = depGraph.get(node);

                for (int i = 2; i < tokens.length; i++) {
                    String dep = tokens[i];
                    adjList.add(dep);
                    if (!validateGraph(depGraph)) {
                        System.out.println(dep + " depends on " + node + " . ignoring command");
                        adjList.remove(adjList.size() - 1);
                    }
                }

            // DFS based install logic to install current item and its prerequisites
            // (all the items the current item depends on should be installed before installing current item)
            } else if (line.startsWith(INSTALL)) {
                System.out.println(line);
                String[] tokens = line.split(" ");
                String node = tokens[1];
                dfsInstall(node, depGraph, installedDeps);

            // DFS based remove logic to remove current item.
            // If there are no dependent items for the current item that are installed,
            // then the item and any uninstalled dependendents can be removed.
            // Otherwise, the current item is still needed and cannot be removed
            } else if (line.startsWith(REMOVE)) {
                if (revDepGraph == null) {
                    revDepGraph = getRevDepGraph(depGraph);
                }

                String[] tokens = line.split(" ");
                String node = tokens[1];

                if (!installedDeps.contains(node)) {
                    System.out.println(node + " is not installed");
                }

                dfsDelete(node, revDepGraph, depGraph, installedDeps);

            // LIST command would print all the items that are curently installed.
            } else if (line.startsWith(LIST)) {
                for (String installedDep : installedDeps) {
                    System.out.println(installedDep);
                }
            }
        }
    }

    public static void dfsDelete(String node, Map<String, List<String>> revDepGraph, Map<String, List<String>> depGraph, Set<String> installedDeps) {
        // A has prerequisites B and C
        // When A is removed, B and C can be removed only if they are not the prerequisites for any installed node

        List<String> prerequisitesOfNode = depGraph.get(node);

        if (prerequisitesOfNode.isEmpty()) {
            System.out.println("Removing " + node);
            installedDeps.remove(node);
            return;
        }

        for (String prerequisiteNode : prerequisitesOfNode) {
            List<String> dependentNodes = revDepGraph.get(prerequisiteNode);

            boolean isPrerequisiteNodeNeeded = false;
            for (String dependentNode : dependentNodes) {
                if (installedDeps.contains(dependentNode)) {
                    isPrerequisiteNodeNeeded = true;
                    break;
                }
            }

            if (!isPrerequisiteNodeNeeded) {
                dfsDelete(prerequisiteNode, revDepGraph, depGraph, installedDeps);
            }
        }
    }

    public static Map<String, List<String>> getRevDepGraph(Map<String, List<String>> depGraph) {
        Map<String, List<String>> revDepGraph = new HashMap<>();
        for (String node : depGraph.keySet()) {
            List<String> deps = depGraph.get(node);

            for (String dep : deps) {
                if (!revDepGraph.containsKey(dep)) {
                    revDepGraph.put(dep, new ArrayList<>());
                }
                revDepGraph.get(dep).add(node);
            }
        }
        return revDepGraph;
    }

    public static void dfsInstall(String node, Map<String, List<String>> depGraph, Set<String> installedDeps) {
        if (installedDeps.contains(node)) {
            return;
        }

        List<String> deps = depGraph.get(node);
        if (deps != null) {
            for (String dep : deps) {
                dfsInstall(dep, depGraph, installedDeps);

            }
        }

        installedDeps.add(node);
        System.out.println("Installing " + node);
    }
}