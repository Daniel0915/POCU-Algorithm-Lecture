package toss.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 출처 : https://www.acmicpc.net/problem/1389
public class BackJun1389BFS {
    static int n, m;
    static List<Integer>[] list;
    static int[] memo, move;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        memo = new int[n+1];
        list = new ArrayList[n+1];

        for (int i = 0; i < n + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                check = new boolean[n + 1];
                move = new int[n + 1];
                if (i != j) {
                    int res = bfs(i, j);
                    memo[i] += res;
                }
            }
        }

        int min = Integer.MAX_VALUE;
        int idx = 0;
        for (int i = 1; i < n + 1; i++) {
            if (min > memo[i]) {
                min = memo[i];
                idx = i;
            }
        }

        System.out.println(idx);
    }

    private static int bfs(int start, int end) {
        Queue<Integer> q = new LinkedList<>();
        check[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int p_node = q.poll();

            if (p_node == end) {
                return move[p_node];
            }

            for (int i = 0; i <list[p_node].size(); i++) {
                int next = list[p_node].get(i);

                if (!check[next]) {
                    check[next] = true;
                    move[next] = move[p_node] + 1;
                    q.add(next);
                }
            }
        }

        return 0;
    }
}
