package toss.bfs.practice;

import java.util.Arrays;
import java.util.PriorityQueue;

// 출처 : https://www.acmicpc.net/problem/1261
public class BackJun1261 {
    // 남, 북, 동, 서
    int[] dX = {1, -1, 0, 0};
    int[] dY = {0, 0, -1, 1};
    int desCnt;

    public int solution(int[][] maps, int m, int n) {
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length; j++) {
                if (maps[i][j] == 1) {
                    maps[i][j] = 0;
                } else {
                    maps[i][j] = 1;
                }
            }
        }

        bfs(maps, visited);

        return desCnt;
    }

    private void bfs(int[][] maps, boolean[][] visited) {
        int x = 0;
        int y = 0;
        visited[x][y] = true;
        PriorityQueue<Pos> queue = new PriorityQueue<>();
        queue.add(new Pos(x, y, 0));

        while (!queue.isEmpty()) {
            Pos current = queue.remove();
            int cX = current.x;
            int cY = current.y;

            // 마지막
            if (cX == maps.length - 1 && cY == maps[0].length -1) {
                desCnt = current.desCnt;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nX = cX + dX[i];
                int nY = cY + dY[i];

                if (nX < 0 || nX > maps.length - 1 || nY < 0 || nY > maps[0].length -1) {
                    continue;
                }

                if (!visited[nX][nY]) {
                    if (maps[nX][nY] == 1) {
                        visited[nX][nY] = true;
                        queue.add(new Pos(nX, nY, current.desCnt));
                    } else {
                        visited[nX][nY] = true;
                        queue.add(new Pos(nX, nY, current.desCnt + 1));
                    }
                }
            }
        }
    }

    static class Pos implements Comparable<Pos> {
        int x;
        int y;
        int desCnt;

        public Pos(int x, int y, int desCnt) {
            this.x = x;
            this.y = y;
            this.desCnt = desCnt;
        }

        @Override
        public int compareTo(Pos o) {
            return this.desCnt - o.desCnt;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "x=" + x +
                    ", y=" + y +
                    ", desCnt=" + desCnt +
                    '}';
        }
    }
}
