package toss.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 출처 : https://www.acmicpc.net/problem/15723
public class BackJun15723Bfs {
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Map<Character, List<Character>> graph = new HashMap<>();

        Set<Character> elements = new HashSet<>();

        while (N --> 0) {
            String[] str = br.readLine().split(" is ");
            char a = str[0].charAt(0);
            char b = str[1].charAt(0);

            elements.add(a);
            elements.add(b);

            List<Character> list = graph.getOrDefault(a, Collections.emptyList());
            if (list.isEmpty()) {
                graph.put(a, new ArrayList<>(Arrays.asList(b)));
            } else {
                graph.get(a).add(b);
            }
        }

        for (Character a : elements) {
            if (graph.get(a) == null) {
                graph.put(a, new ArrayList<>());
            }
        }


        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        while (M --> 0) {
            String[] str = br.readLine().split(" is ");
            char a = str[0].charAt(0);
            char b = str[1].charAt(0);

            sb.append(bfs(a, b, graph) ? "T" : "F");
//            sb.append(connected[a - 'a'][b - 'a'] ? "T" : "F");
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static boolean bfs(char start, char end, Map<Character, List<Character>> graph) {
        if (start == end) {
            return false;
        }

        Queue<Character> queue = new LinkedList<>(graph.get(start));
        Set<Character> searched = new HashSet<>();

        while (!queue.isEmpty()) {
            char a = queue.poll();

            if (Objects.equals(a, end)) {
                return true;
            }

            if (!searched.contains(a)) {
                queue.addAll(graph.get(a));
                searched.add(a);
            }
        }

        return false;
    }

    private static void print2DArray(int[][] array) {
        for (int[] row : array) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println(); // 줄바꿈을 추가하여 가독성 향상
    }

    private static void print2DArray(boolean[][] array) {
        for (boolean[] row : array) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println(); // 줄바꿈을 추가하여 가독성 향상
    }
}
