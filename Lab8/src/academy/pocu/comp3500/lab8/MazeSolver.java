package academy.pocu.comp3500.lab8;

import academy.pocu.comp3500.lab8.maze.Point;

import java.util.*;

public final class MazeSolver {
    // 북, 남, 동, 서
    static int[] dX = {0, 0, 1, -1};
    static int[] dY = {1, -1, 0, 0};

    public static List<Point> findPath(final char[][] maze, final Point start) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        int[][] intVisited = new int[maze.length][maze[0].length];

        List<Point> points = new ArrayList<>();
        bfs(maze, start, visited, intVisited, points);
        print2DArray(intVisited);

        return points;
    }

    private static void bfs(char[][] maze, Point start, boolean[][] visited, int[][] intVisited, List<Point> points) {
        int x = start.getY();
        int y = start.getX();
        visited[x][y] = true;
        intVisited[x][y] = 1;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));

        int[] exit = null;
        int depth = 0;

        while (!queue.isEmpty()) {
            Point current = queue.remove();
            int cX = current.getX();
            int cY = current.getY();

            if (maze[cX][cY] == 'E') {
                exit = new int[]{cX, cY};
                depth = intVisited[cX][cY];
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nX = cX + dX[i];
                int nY = cY + dY[i];

                if (nX < 0 || nX > maze.length - 1 || nY < 0 || nY > maze[0].length - 1) {
                    continue;
                }

                if (!visited[nX][nY] && (maze[nX][nY] == ' ' || maze[nX][nY] == 'E')) {
                    visited[nX][nY] = true;
                    intVisited[nX][nY] = intVisited[cX][cY] + 1;
                    queue.add(new Point(nX, nY));
                }
            }
        }

        if (exit == null) {
            return;
        }

    }



    private static void print2DArray(int[][] array) {
        for (int[] row : array) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println(); // 줄바꿈을 추가하여 가독성 향상
    }
}