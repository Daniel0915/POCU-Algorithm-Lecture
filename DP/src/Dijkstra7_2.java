import java.util.*;

public class Dijkstra7_2 {
    public static void main(String[] args) {
        String start = "start";
        String fin = "fin";

        Map<String, Map<String, Integer>> graph = new HashMap<>();
        graph.put(start, new HashMap<>(){{
            put("a", 10);
        }});

        graph.put("a", new HashMap<>(){{
            put("b", 20);
        }});

        graph.put("b", new HashMap<>(){{
            put("c", 1);
            put(fin, 30);
        }});

        graph.put("c", new HashMap<>(){{
            put("a", 1);
        }});

        graph.put(fin, new HashMap<>());


        Map<String, Integer> costs = new HashMap<>(){{
            put("a", 10);
            put("b", Integer.MAX_VALUE);
            put("c", Integer.MAX_VALUE);
            put(fin, Integer.MAX_VALUE);
        }};

        Map<String, String> parents = new HashMap<>(){{
            put("a", start);
            put("b", null);
            put("c", null);
            put(fin, null);
        }};

        Set<String> processed = new HashSet<>();

        String node = find_node_lowest_cost(costs, processed);

        while (node != null) {
            Integer cost = costs.get(node);
            Map<String, Integer> neighbors = graph.get(node);

            for (String n : neighbors.keySet()) {
                // neighbor cost
                Integer neighborCost = costs.get(n);
                // B -> A
                Integer newCost = cost + neighbors.get(n);

                if (neighborCost > newCost) {
                    costs.put(n, newCost);
                    parents.put(n, node);
                }
            }
            processed.add(node);
            node = find_node_lowest_cost(costs, processed);
        }



        System.out.println("cost = " + costs.get(fin));
        System.out.println("path = " + getPath(parents));
    }

    public static String find_node_lowest_cost(Map<String, Integer> costs, Set<String> processed) {
        String lowestCostNode = null;
        int lowestCost = Integer.MAX_VALUE;

        for (Map.Entry<String, Integer> entry : costs.entrySet()) {
            String node = entry.getKey();
            int cost = entry.getValue();

            if (processed.contains(node)) {
                continue;
            }

            if (lowestCost >= cost) {
                lowestCostNode = node;
                lowestCost = cost;
            }
        }
        return lowestCostNode;
    }

    public static List<String> getPath(Map<String, String> parents) {
        String fin = "fin";
        List<String> paths = new ArrayList<>();

        String childNode = parents.get(fin);
        paths.add(fin);
        while (childNode != null) {
            paths.add(childNode);
            childNode = parents.get(childNode);
        }

        Collections.reverse(paths);
        return paths;
    }
}
