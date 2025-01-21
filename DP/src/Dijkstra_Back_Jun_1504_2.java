import java.io.*;
import java.util.*;

public class Dijkstra_Back_Jun_1504_2 {
    static final int INF = 200000000;
    static int N, E;
    static List<List<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선 입력
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Node(end, weight));
        }


        // 반드시 거쳐야 하는 정점
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        // 경로 계산
        int route1 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N);
        int route2 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N);

        int result = Math.min(route1, route2);
        bw.write((result >= INF ? -1 : result) + "\n");
        bw.flush();
        br.close();
        bw.close();
    }

    // 다익스트라 알고리즘
    static int dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentNode = current.vertex;
            int currentCost = current.cost;

            if (dist[currentNode] < currentCost) continue;

            for (Node neighbor : graph.get(currentNode)) {
                int newCost = dist[currentNode] + neighbor.cost;
                if (newCost < dist[neighbor.vertex]) {
                    dist[neighbor.vertex] = newCost;
                    pq.add(new Node(neighbor.vertex, newCost));
                }
            }
        }

        return dist[end];
    }

    static class Node implements Comparable<Node> {
        int vertex, cost;

        Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
        }
    }
}
