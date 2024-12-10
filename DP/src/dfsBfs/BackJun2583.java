package dfsBfs;

import java.util.*;

// 출처 : https://www.acmicpc.net/problem/2583
public class BackJun2583 {
    private List<Integer> result = new ArrayList<>();
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    public List<Integer> solution(int m, int n,
                                  int x1L, int y1L, int x1R, int y1R,
                                  int x2L, int y2L, int x2R, int y2R,
                                  int x3L, int y3L, int x3R, int y3R) {
        int[][] map = new int[m][n];
        boolean[][] visited = new boolean[m][n];

        for (int y = y1L; y < y1R; y++) {
            for (int x = x1L; x < x1R; x++) {
                map[Math.abs(y - (m - 1))][x] = -1;
            }
        }

        for (int y = y2L; y < y2R; y++) {
            for (int x = x2L; x < x2R; x++) {
                map[Math.abs(y - (m - 1))][x] = -1;
            }
        }

        for (int y = y3L; y < y3R; y++) {
            for (int x = x3L; x < x3R; x++) {
                map[Math.abs(y - (m - 1))][x] = -1;
            }
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 0 && !visited[i][j]) {
                    int[] startPoint = new int[]{i, j};
                    result.add(bfs(map, visited, startPoint));
                }
            }
        }


        print2DArray(map);

        System.out.println("result = " + result);

        return new ArrayList<>();
    }

    private int bfs(int[][] maps, boolean[][] visited, int[] startPoint) {
        // 시작점
        int x = startPoint[0];
        int y = startPoint[1];
        visited[x][y] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        int count = 1;

        while (!queue.isEmpty()) {
            int[] current = queue.remove();
            int cX = current[0];
            int cY = current[1];

            for (int i = 0; i < 4; i++) {
                int nX = cX + dx[i];
                int nY = cY + dy[i];

                if (nX < 0 || nX > maps.length - 1 || nY < 0 || nY > maps[0].length - 1) {
                    continue;
                }

                if (!visited[nX][nY] && maps[nX][nY] == 0) {
                    visited[nX][nY] = true;
                    queue.add(new int[]{nX, nY});
                    count++;
                }
            }
        }
        return count;
    }

    private void print2DArray(int[][] array) {
        for (int[] row : array) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println(); // 줄바꿈을 추가하여 가독성 향상
    }
}
