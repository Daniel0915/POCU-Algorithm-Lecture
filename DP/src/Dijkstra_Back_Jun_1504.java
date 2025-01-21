import java.io.*;
import java.util.*;

public class Dijkstra_Back_Jun_1504 {
    static int N, E;
    static int                            INF         = 200000000;
    static int                                  startN      = 1;
    static Map<Integer, Map<Integer, Integer>>  graph       = new HashMap<>();
    static Map<Integer, Integer>                costs       = new HashMap<>();
    static Set<Integer>                         processed   = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter  bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            graph.put(i, new HashMap<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(start).put(end, weight);
            graph.get(end).put(start, weight);
        }

        // 반드시 거쳐야 하는 정점.
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        // 1 -> v1 -> v2 -> N
        Integer startToV1   = dijkstra(startN, v1);
        Integer v1ToV2      = dijkstra(v1, v2);
        Integer v2ToN       = dijkstra(v2, N);

        // 1 -> v2 -> v1 -> N
        Integer startToV2   = dijkstra(startN, v2);
        Integer v1ToN = dijkstra(v1, N);

        // 1 -> v1 -> v2 -> N
        Integer total_1 = startToV1 + v1ToV2 +  v2ToN;
        // 1 -> v2 -> v1 -> N
        Integer total_2 = startToV2 + v1ToV2 + v1ToN;

        INF = INF > total_1 ? total_1 : INF;
        INF = INF > total_2 ? total_2 : INF;

        INF = INF == 200000000 ? -1 : INF;

        bw.write(INF + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static Integer dijkstra(Integer startNode, Integer endNode) {
        processed = new HashSet<>();
        initCosts(costs, startNode);
        processed.add(startNode);
        // cost min
        Integer node = getNodeCostMin(costs, processed);

        while (node != null) {
            Integer cost = costs.get(node);
            Map<Integer, Integer> neighbors = graph.get(node);



            for (Integer n : neighbors.keySet()) {
                Integer old_cost = costs.get(n);
                Integer new_cost = cost + neighbors.get(n);

                if (old_cost > new_cost) {
                    costs.put(n, new_cost);
                }
            }

            processed.add(node);
            node = getNodeCostMin(costs, processed);
        }

        return costs.get(endNode);
    }


    public static Integer getNodeCostMin(Map<Integer, Integer> costs, Set<Integer> processed) {
        Integer node = null;
        Integer cost = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Integer> entry : costs.entrySet()) {

            if (processed.contains(entry.getKey())) {
                continue;
            }

            if (cost > entry.getValue()) {
                cost = entry.getValue();
                node = entry.getKey();
            }
        }

        return node;
    }


    public static void initCosts(Map<Integer, Integer> costs, Integer startNode) {
        for (int i = 1; i <= N; i++) {
            if (i == startNode) {
                continue;
            }

            costs.put(i, Integer.MAX_VALUE);
        }

        for (Map.Entry<Integer, Map<Integer, Integer>> entry : graph.entrySet() ) {
            Integer node = entry.getKey();

            if (Objects.equals(node, startNode)) {
                continue;
            }

            Map<Integer, Integer> neighbors = entry.getValue();

            for (Map.Entry<Integer, Integer> entryNeighbor : neighbors.entrySet()) {
                if (Objects.equals(entryNeighbor.getKey(), startNode)) {
                    costs.put(node, entryNeighbor.getValue());
                }
            }
        }
    }
}
