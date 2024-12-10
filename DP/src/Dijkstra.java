import java.util.*;

public class Dijkstra {
    public static void main(String[] args) {
        Map<String, Map<String, Integer>> graph = new HashMap<>();
        graph.put("start", new HashMap<>());
        graph.get("start").put("a", 6);
        graph.get("start").put("b", 2);


        graph.put("a", new HashMap<>());
        graph.get("a").put("fin", 1);

        graph.put("b", new HashMap<>());
        graph.get("b").put("a", 3);
        graph.get("b").put("fin", 5);

        graph.put("fin", new HashMap<>());

        Map<String, Integer> costs = new HashMap<>();
        costs.put("a", 6);
        costs.put("b", 2);
        costs.put("fin", Integer.MAX_VALUE);


        Map<String, String> parents = new HashMap<>();
        parents.put("a", "start");
        parents.put("b", "start");
        parents.put("fin", null);

        Set<String> processed = new HashSet<>();

        String node = find_lower_cost_node(costs, processed);

        while (node != null) {
            // B
            Integer cost = costs.get(node);
            Map<String, Integer> neighbors = graph.get(node);

            for (String n : neighbors.keySet()) {
                // neighbor cost
                Integer neighborCost = costs.get(n);
                // (B -> A)
                Integer new_cost = cost + neighbors.get(n);

                if (neighborCost > new_cost) {
                    costs.put(n, new_cost);
                    parents.put(n, node);
                }
            }
            processed.add(node);
            node = find_lower_cost_node(costs, processed);
        }
        System.out.println("cost = " + costs.get("fin"));
        System.out.println("routes = " + getRoutes(parents));

    }

    public static String find_lower_cost_node(Map<String, Integer> costs, Set<String> processed) {
        String lowestCodeNode = null;
        int lowestCost = Integer.MAX_VALUE;

        for (Map.Entry<String, Integer> entry : costs.entrySet()) {
            String node = entry.getKey();
            int    cost = entry.getValue();

            if (!processed.contains(node) && cost < lowestCost) {
                lowestCodeNode = node;
                lowestCost     = cost;
            }
        }
        return lowestCodeNode;
    }

    public static List<String> getRoutes(Map<String, String> parents) {
        List<String> routes = new ArrayList<>();
        String fin = "fin";

        String childNode = parents.get(fin);
        routes.add(fin);
        while (childNode != null) {
            routes.add(childNode);
            childNode = parents.get(childNode);
        }

        Collections.reverse(routes);

        return routes;
    }
}
