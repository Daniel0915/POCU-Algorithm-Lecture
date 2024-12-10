package dfsBfs;

import java.util.*;

public class HelloCodingBfsPractice_1 {
    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();

        // start -> A
        // end -> G
        graph.put("A", List.of("B", "D"));
        graph.put("B", List.of("C", "G"));
        graph.put("C", new ArrayList<>());
        graph.put("D", List.of("C", "F"));
        graph.put("G", new ArrayList<>());

        System.out.println(bfs("A", "G", graph));
    }

    public static List<String> bfs(String start, String goal, Map<String, List<String>> graph) {
        Queue<List<String>> queue = new LinkedList<>();  // 탐색 경로를 저장하는 큐
        Set<String> searched = new HashSet<>();          // 방문한 노드를 추적하는 집합

        queue.add(Arrays.asList(start));

        while (!queue.isEmpty()) {
            List<String> path = queue.poll();
            String person = path.get(path.size() - 1);

            if (person.equals(goal)) {
                return path;
            }

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
