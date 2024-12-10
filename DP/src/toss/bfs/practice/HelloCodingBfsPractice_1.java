package toss.bfs.practice;

import java.util.*;

public class HelloCodingBfsPractice_1 {
    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();

        // start -> CAB
        // end -> BAT
        graph.put("CAB", List.of("CAT", "CAR"));
        graph.put("CAT", List.of("MAT", "BAT"));
        graph.put("CAR", List.of("CAT", "BAR"));
        graph.put("MAT", List.of("BAT"));
        graph.put("BAT", new ArrayList<>());
        graph.put("BAR", List.of("BAT"));

        System.out.println(bfs("CAB", "BAT", graph));
    }

    public static List<String> bfs(String start, String end, Map<String, List<String>> graph) {
        Queue<List<String>> queue = new LinkedList<>();
        queue.add(Arrays.asList(start));

        Set<String> searched = new HashSet<>();

        while (!queue.isEmpty()) {
            List<String> path = queue.poll();
            String person = path.get(path.size() - 1);

            if (person.equals(end)) {
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
