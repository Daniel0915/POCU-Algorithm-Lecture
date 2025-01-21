import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Dijkstra_Back_Jun_1446 {
    static int N,D;
    static List<List<Node>> graph = new ArrayList<>();
    static int[] distance;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        for (int i = 0; i<= 10001; i++) {
            graph.add(new ArrayList<>());
        }
        distance = new int[10001];

        Arrays.fill(distance, 1);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b,w));
        }

        dijkstra(0);

        System.out.println(distance[D]);

    }


    static void dijkstra(int start) {
        if (start > D) return;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        if (distance[start + 1] > distance[start] + 1) {
            distance[start + 1] = distance[start] + 1;
        }

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentNode = current.node;
            int currentCost = current.weight;

            if (distance[currentNode] < currentCost) continue;

            for (Node neighbor : graph.get(currentNode)) {
                int newCost = distance[currentCost] + neighbor.weight;

                if (newCost < distance[neighbor.weight]) {
                    distance[neighbor.node] = newCost;
                    pq.add(new Node(neighbor.node, newCost));
                }
            }
        }

        dijkstra(start + 1);
    }

    static class Node implements Comparable<Node>{
        int node;
        int weight;

        Node(int node, int weight){
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.weight, other.weight);
        }
    }


}
