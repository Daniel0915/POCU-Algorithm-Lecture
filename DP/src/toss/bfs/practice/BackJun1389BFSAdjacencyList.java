package toss.bfs.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 출처 : https://www.acmicpc.net/problem/1389
// 인접리스트 버전
public class BackJun1389BFSAdjacencyList {
    static int n, m;
    static List<Integer>[] list;
    static int[] memo, move;
    static boolean[] check;
    static Map<Integer, List<Integer>> graph = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        memo = new int[n + 1];
        list = new ArrayList[n + 1];

        for (int i = 0; i < n + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }


        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
        }

        int min = Integer.MAX_VALUE;
        int idx = 0;

        for (int i = 1; i < n + 1; i++) {
            int pathValue = 0;
            for (int j = 1; j < n + 1; j++) {
                if (i == j) {
                    continue;
                }

                int pathLength = bfs(i, j).size();
                pathValue += pathLength == 0 ? 0 : pathLength - 1;
            }

            if (pathValue == 0) {
                continue;
            }
            if (min > pathValue) {
                min = pathValue;
                idx = i;
            }
        }

        System.out.println(idx);
    }

    public static List<Integer> bfs(Integer start, Integer end) {
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(Arrays.asList(start));
        Set<Integer> searched = new HashSet<>();



        while (!queue.isEmpty()) {
            List<Integer> path = queue.poll();
            Integer node = path.get(path.size() - 1);

            if (end.equals(node)) {
                return path;
            }

            for (Integer n : graph.getOrDefault(node, Collections.emptyList())) {
                List<Integer> newPath = new ArrayList<>(path);
                newPath.add(n);
                queue.add(newPath);
            }
            searched.add(n);
        }
        return Collections.emptyList();
    }
}
