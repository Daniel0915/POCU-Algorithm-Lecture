package bruteForce;

import java.util.ArrayList;

// https://isshosng.tistory.com/162
public class GridTriDfsGraph {
    static ArrayList<Integer>[] graph;
    static int min;

    public int solution(int n, int[][] wires) {
        graph = new ArrayList[n + 1];
        min = Integer.MAX_VALUE;

        // 그래프 ArrayList 초기화, 노드 개수 만큼 ArrayList 생성
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 양방향 간선 구조이므로 2 번 add 를 해준다.
        for (int i = 0; i < wires.length; i++) {
            int v1 = wires[i][0];
            int v2 = wires[i][1];
            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        for (int i = 0; i < wires.length; i++) {
            int v1 = wires[i][0];
            int v2 = wires[i][1];

            boolean[] visited = new boolean[n + 1];

            // 해당 간선을 그래프에서 제거
            graph[v1].remove(Integer.valueOf(v2));
            graph[v2].remove(Integer.valueOf(v1));

            // 임의의 시작점에서 dfs 탐색
            int cnt = dfs(1, visited);

            int diff = Math.abs(cnt - (n - cnt));
            min = Math.min(min, diff);

            // 그패르에 다시 간선 추가
            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        return min;
    }

    private int dfs(int v, boolean[] visited) {
        visited[v] = true;
        int cnt = 1;

        for (int next : graph[v]) {
            if (!visited[next]) {
                cnt += dfs(next, visited);
            }
        }

        return cnt;
    }
}
