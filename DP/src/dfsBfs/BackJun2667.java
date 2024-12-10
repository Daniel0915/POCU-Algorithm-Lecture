package dfsBfs;

import java.util.*;

// 출처 : https://www.acmicpc.net/problem/2667
public class BackJun2667 {
    int[] dX = {1, -1, 0, 0};
    int[] dY = {0, 0, 1, -1};
    List<Integer> result = new ArrayList<>();

    public List<Integer> solution(int[][] maze) {
        int[][] visited = new int[maze.length][maze[0].length];

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (visited[i][j] == 0 && maze[i][j] == 1) {
                    bfs(maze, visited, i, j);
                }
            }
        }

        if (!result.isEmpty()) {
            Collections.sort(result);
            result.add(0, result.size());
        }

        return result;
    }

    private void bfs(int[][] maze, int[][] visited, int startX, int startY) {
        visited[startX][startY] = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});

        int cnt = 1;

        while (!queue.isEmpty()) {
            int[] current = queue.remove();
            int cX = current[0];
            int cY = current[1];

            for (int i = 0; i < 4; i++) {
                int nX = cX + dX[i];
                int nY = cY + dY[i];

                if (nX < 0 || nX > maze.length - 1 || nY  < 0 || nY > maze[0].length - 1) {
                    continue;
                }

                if (visited[nX][nY] == 0 && maze[nX][nY] == 1) {
                    visited[nX][nY] = 1;
                    cnt++;
                    queue.add(new int[]{nX, nY});
                }
            }
        }

        result.add(cnt);
    }

    private void print2DArray(int[][] array) {
        for (int[] row : array) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println(); // 줄바꿈을 추가하여 가독성 향상
    }
}
