package academy.pocu.comp3500.lab8;

import academy.pocu.comp3500.lab8.maze.Point;

import java.util.*;

public final class MazeSolver2 {
    // 북, 남, 동, 서
//    static int[] dX = {0, 0, 1, -1};
//    static int[] dY = {1, -1, 0, 0};
    static int[] dX = {0, 1, -1, 0};
    static int[] dY = {1, 0, 0, -1};

    public static List<Point> findPath(final char[][] maze, final Point start) {
        List<Point> path = new ArrayList<>();

        path.add(new Point(start.getX(), start.getY()));
        if (dfs(maze, start.getY(), start.getX(), path)) {
            return path;
        }
        return new ArrayList<>();
    }

    private static boolean dfs(char[][] maze, int x, int y, List<Point> path) {
        if (maze[x][y] == 'E') {
            return true;
        }

        maze[x][y] = 'v';

        for (int i = 0; i < 4; i++) {
            int newX = x + dX[i];
            int newY = y + dY[i];

            if (isValid(maze, newX, newY)) {
                path.add(new Point(newY, newX));
                if (dfs(maze, newX, newY, path)) {
                    return true;
                }
                path.remove(path.size() - 1);
            }
        }
        System.out.println("x :" + x + ", " + "y : " + y);

        return false;
    }

    private static boolean isValid(char[][] maze, int x, int y) {
        int rows = maze.length;
        int cols = maze[0].length;
        return x >= 0 && x < rows && y >= 0 && y < cols && maze[x][y] != 'x' && maze[x][y] != 'v';
    }


    private static void print2DArray(int[][] array) {
        for (int[] row : array) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println(); // 줄바꿈을 추가하여 가독성 향상
    }
}