package toss.bfs.practice;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

// https://school.programmers.co.kr/learn/courses/30/lessons/1844
public class BfsGame {
    // 동 서 남 북
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};

    public int solution(int[][] maps) {
        boolean[][] visited = new boolean[maps.length][maps[0].length];
        return bfs(maps, visited);
    }

    private int bfs(int[][] maps, boolean[][] visited) {
        int x = 0;
        int y = 0;
        visited[x][y] = true;
        PriorityQueue<Point> queue = new PriorityQueue<>();
        queue.add(new Point(x, y, 1));

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            int cX = current.x;
            int cY = current.y;

            if (cX == maps.length - 1 && cY == maps[0].length - 1) {
                return current.cnt;
            }

            for (int i = 0; i < 4; i++) {
                int nX = cX + dx[i];
                int nY = cY + dy[i];

                if (nX < 0 || nX > maps.length - 1 || nY < 0 || nY > maps[0].length - 1) {
                    continue;
                }

                if (!visited[nX][nY] && maps[nX][nY] == 1) {
                    visited[nX][nY] = true;
                    queue.add(new Point(nX, nY, current.cnt + 1));
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
