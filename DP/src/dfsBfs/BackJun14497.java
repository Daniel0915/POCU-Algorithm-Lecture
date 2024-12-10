package dfsBfs;

import java.util.Objects;
import java.util.PriorityQueue;

public class BackJun14497 {
    private int[] dx = {0, 0, 1, -1};
    private int[] dy = {1, -1, 0, 0};

    public int solution(String[][] maps, int n, int m) {
        boolean[][] visited = new boolean[n][m];

        int startX = 0;
        int startY = 0;

        outerLoop:
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length; j++) {
                if (Objects.equals(maps[i][j], "*")) {
                    startX = i;
                    startY = j;
                    break outerLoop;
                }
            }
        }

        return bfs(startX, startY, maps, visited);
    }

    private int bfs(int startX, int startY, String[][] maps, boolean[][] visited) {
        int x = startX;
        int y = startY;
        visited[x][y] = true;
        PriorityQueue<Point> queue = new PriorityQueue<>();
        queue.add(new Point(x, y, 0));

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            int cX = current.x;
            int cY = current.y;

            if (Objects.equals(maps[cX][cY], "#")) {
                return current.cnt;
            }

            for (int i = 0; i < 4; i++) {
                int nX = cX + dx[i];
                int nY = cY + dy[i];

                if (nX < 0 || nX > maps.length - 1 || nY < 0 || nY > maps[0].length - 1) {
                    continue;
                }

                if (!visited[nX][nY]) {
                    visited[nX][nY] = true;
                    if (Objects.equals(maps[nX][nY], "0")) {
                        queue.add(new Point(nX, nY, current.cnt));
                    } else {
                        queue.add(new Point(nX, nY, current.cnt + 1));
                    }
                }
            }
        }
        return -1;
    }

    static class Point implements Comparable<Point> {
        int x;
        int y;
        int cnt;

        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Point o) {
            return this.cnt - o.cnt;
        }
    }
}
