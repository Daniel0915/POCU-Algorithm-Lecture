import java.util.*;

public class Dijkstra7_3 {
    public static void main(String[] args) {
        String start = "start";
        String fin = "fin";
        // graph
        Map<String, Map<String, Integer>> graph = new HashMap<>();

        graph.put(start, new HashMap<>() {{
                    put("a", 2);
                    put("b", 2);
                }}
        );

        graph.put("a", new HashMap<>() {{
                    put("c", 2);
                    put(fin, 2);
                }}
        );

        graph.put("b", new HashMap<>() {{
                    put("a", 2);
                    put("c", 1);
                }}
        );


        graph.put("c", new HashMap<>() {{
                    put(fin, 2);
                }}
        );

        graph.put(fin, new HashMap<>());

        // costs
        Map<String, Integer> costs = new HashMap<>(){{
            put("a", 2);
            put("b", 2);
            put("c", Integer.MAX_VALUE);
            put(fin, Integer.MAX_VALUE);
        }};

        // parent
        Map<String, String> parent = new HashMap<>(){{
            put("a", start);
            put("b", start);
            put("c", null);
            put(fin, null);
        }};

        // processed
        Set<String> processed = new HashSet<>();

        String node = find_node_lowest(costs, processed);

        while (node != null) {
            Integer cost = costs.get(node);
            Map<String, Integer> neighbors = graph.get(node);

            for (String n : neighbors.keySet()) {
                Integer oldCost = costs.get(n);
                Integer newCost = cost + neighbors.get(n);

                if (oldCost > newCost) {
                    costs.put(n, newCost);
                    parent.put(n, node);
                }
            }

            processed.add(node);
            node = find_node_lowest(costs, processed);
        }

        System.out.println("costs.get(fin) = " + costs.get(fin));

        List<String> paths = getPaths(parent);
        System.out.println("paths = " + paths);
    }

    private static String find_node_lowest(Map<String, Integer> costs, Set<String> processed) {
        Integer value = Integer.MAX_VALUE;
        String node = null;

        for (String key : costs.keySet()) {
            if (processed.contains(key)) {
                continue;
            }

            if (value > costs.get(key)) {
                value = costs.get(key);
                node = key;
            }
        }

        return node;
    }

    private static List<String> getPaths(Map<String, String> parent) {
        List<String> paths = new ArrayList<>();
        paths.add("fin");
        String node = parent.get("fin");

        while (node != null) {
            paths.add(node);
            node = parent.get(node);
        }
        Collections.reverse(paths);
        return paths;
    }
}
