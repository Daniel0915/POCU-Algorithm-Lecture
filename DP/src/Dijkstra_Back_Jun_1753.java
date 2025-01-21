import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Dijkstra_Back_Jun_1753 {
    public static Map<String, Map<String, Integer>> graph   = new HashMap<>();
    public static Map<String, Integer>              costs   = new HashMap<>();
    public static Map<String, String>              parents  = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = parseInt(st.nextToken());
        int E = parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        String startV = st.nextToken();

        for (int i = 1; i < V + 1; i++) {
            String vector = String.valueOf(i);
            graph.put(vector, new HashMap<>());

            if (Objects.equals(vector, startV)) {
                continue;
            }
            costs.put(vector, Integer.MAX_VALUE);
            parents.put(vector, null);
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            String u = st.nextToken();
            String v = st.nextToken();
            int    w = parseInt(st.nextToken());

            if (graph.get(u) == null) {
                graph.put(u, new HashMap<>(){{
                    put(v, w);
                }});
            } else {
                graph.get(u).put(v, w);
            }
        }

        Map<String, Integer> startVNeighbor = graph.get(startV);
        for (Map.Entry<String, Integer> entry : startVNeighbor.entrySet()) {
            costs.put(entry.getKey(), entry.getValue());
            parents.put(entry.getKey(), startV);
        }

        Set<String> processed = new HashSet<>();

        String node = find_lowest_vector(costs, processed);

        while (node != null) {
            Map<String, Integer> neighbors = graph.get(node);
            for (String n : neighbors.keySet()) {
                Integer a = costs.get(n);

                Integer a_ = costs.get(node);
                Integer neighborCost = neighbors.get(n);

                Integer a_new_cost = a_ + neighborCost;

                if (a > a_new_cost) {
                    costs.put(n, a_new_cost);
                    parents.put(n, node);
                }
            }
            processed.add(node);
            node = find_lowest_vector(costs, processed);
        }
        costs.put(startV, 0);


        Map<String ,String> result = new HashMap<>();

        for (Map.Entry<String, Integer> entry : costs.entrySet()) {
            Integer value = entry.getValue();
            if (value == Integer.MAX_VALUE) {
                result.put(entry.getKey(), "INF");
            } else {
                result.put(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String key : result.keySet()) {
            sb.append(result.get(key) + "\n");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static String find_lowest_vector(Map<String, Integer> costs, Set<String> processed) {
        Integer cost = Integer.MAX_VALUE;
        String node = null;

        for (String key : costs.keySet()) {
            if (processed.contains(key)) {
                continue;
            }

            if (cost > costs.get(key)) {
                cost = costs.get(key);
                node = key;
            }
        }

        return node;
    }
}
