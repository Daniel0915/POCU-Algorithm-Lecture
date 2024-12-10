package toss;

import java.util.*;

public class Test_2 {
    public static void main(String[] args) {
        int[][] relationships = new int[][]{
                {1, 2},
                {2, 3},
                {2, 6},
                {3, 4},
                {4, 5}
        };

        int target = 1;
        int limit = 2;


        // 초기화
        Set<Integer> elements = new HashSet<>();
        for (int i = 0; i < relationships.length; i++) {
            elements.add(relationships[i][0]);
            elements.add(relationships[i][1]);
        }

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (Integer e: elements) {
            graph.put(e, new ArrayList<>());
        }

        for (int i = 0; i < relationships.length; i++) {
            graph.get(relationships[i][0]).add(relationships[i][1]);
            graph.get(relationships[i][1]).add(relationships[i][0]);
        }

        int path5Count = 0;
        int path10Count = 0;
        for (int e: elements) {
            if (e == target) {
                continue;
            }
            List<Integer> path = bfs(target, e, graph);
            int pathSize = path.size() - 1;
            if (pathSize == 1) {
                path5Count++;
            } else if (pathSize <= limit) {
                path10Count++;
            }
        }

        int result = (path5Count * 5) + (path10Count * 10) + 2;

        System.out.println(result);

    }

    public static List<Integer> bfs(int start, int goal, Map<Integer, List<Integer>> graph) {
        Queue<List<Integer>> queue = new LinkedList<>();
        Set<Integer> searched = new HashSet<>();

        queue.add(Arrays.asList(start));

        while (!queue.isEmpty()) {
            List<Integer> path = queue.poll();

            int node = path.get(path.size() - 1);

            if (node == goal) {
                return path;
            }

            if (!searched.contains(node)) {
                for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
                    List<Integer> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
                searched.add(node);
            }
        }

        return Collections.emptyList();
    }
}
