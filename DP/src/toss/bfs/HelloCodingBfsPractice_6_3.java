package toss.bfs;

import java.util.*;

// 못품(차후에 시간 있을 때, 풀기)
public class HelloCodingBfsPractice_6_3 {
    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();

        // start ->
        // end -> G
        graph.put("A", List.of("B", "D"));
        graph.put("B", List.of("C", "G"));
        graph.put("C", new ArrayList<>());
        graph.put("D", List.of("C", "F"));
        graph.put("G", new ArrayList<>());

        bfs("A", "G", graph);
    }

    public static List<String> bfs(String start, String goal, Map<String, List<String>> graph) {
        Queue<List<String>> queue = new LinkedList<>();  // 탐색 경로를 저장하는 큐
        Set<String> searched = new HashSet<>();          // 방문한 노드를 추적하는 집합

        queue.add(Arrays.asList(start));

        while (!queue.isEmpty()) {
            queue.stream().forEach(s -> System.out.println(s));
            System.out.println("========================");
            List<String> path = queue.poll();

            // path 의 마지막 정점을 가져온다.
            String person = path.get(path.size() - 1);

//            if (graph.get(person).isEmpty()) {
//                return path;
//            }

            if (!searched.contains(person)) {
                for (String neighbor : graph.getOrDefault(person, Collections.emptyList())) {
                    List<String> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
                searched.add(person);
            }
        }
        return Collections.emptyList();
    }
}
