package academy.pocu.comp3500.lab8;

import academy.pocu.comp3500.lab8.maze.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public final class MazeSolver {
    public static List<Point> findPath(final char[][] maze, final Point start) {
        Point cur = start;
        Stack<Point> crossPoint = new Stack<>();
        Stack<List<Point>> logs = new Stack<>();
        List<Point> tmpLog = new ArrayList<>();

        while (true) {

            if (isExit(maze, cur)) {
                tmpLog.add(cur);
                logs.push(tmpLog);
                return sumLogs(logs);
            }

            maze[cur.getY()][cur.getX()] = 'x'; // 이미 지났던 자리는 x 로 막기

            Stack<Point> movable = getMovablePoint(maze, cur);

            if (movable.size() == 0) {
                // 다 막혀서 옴, 다 막히진 않음
                if (crossPoint.size() == 0) {
                    return new ArrayList<>();
                }

                logs.add(tmpLog);
                logs.pop();
                cur = crossPoint.pop();
                tmpLog = new ArrayList<>();
                continue;
            }

            if (movable.size() >= 2) {
                logs.push(tmpLog);
                tmpLog = new ArrayList<>();
                crossPoint.push(cur);
            }

            tmpLog.add(cur);
            cur = movable.pop();
        }
    }

    public static Stack<Point> getMovablePoint(char[][] maze, Point loc) {
        int x = loc.getX();
        int y = loc.getY();
        Stack<Point> movable = new Stack<>();

        if (x - 1 >= 0 && maze[y][x - 1] != 'x') { // 북
            movable.push(new Point(x -1, y));
        }

        if (y - 1 >= 0 && maze[y -1][x] != 'x') { // 서
            movable.push(new Point(x, y - 1));
        }

        if (x + 1 < maze[0].length && maze[y][x + 1] != 'x') { // 남
            movable.push(new Point(x + 1, y));
        }

        if (y + 1 < maze.length && maze[y + 1][x] != 'x') { // 동
            movable.push(new Point(x, y + 1));
        }

        return movable;
    }

    public static Stack<Character> getMovablePointTest(char[][] maze, Point loc) {
        int x = loc.getX();
        int y = loc.getY();
        Stack<Character> movable = new Stack<>();

        if (x - 1 >= 0 && maze[x - 1][y] != 'x') { // 북
            movable.push(maze[x - 1][y]);
        }

        if (y - 1 >= 0 && maze[x][y - 1] != 'x') { // 서
            movable.push(maze[x][y - 1]);
        }

        if (x + 1 < maze[0].length && maze[x + 1][y] != 'x') { // 남
            movable.push(maze[x + 1][y]);
        }

        if (y + 1 < maze.length && maze[x][y + 1] != 'x') { // 동
            movable.push(maze[x][y + 1]);
        }

        return movable;
    }

    private static List<Point> sumLogs(Stack<List<Point>> logs) {
        List<Point> result = new ArrayList<>();
        Stack<List<Point>> reverse = new Stack<>();


        while (!logs.isEmpty()) {
            reverse.add(logs.pop());
        }

        while (!reverse.isEmpty()) {
            result.addAll(reverse.pop());
        }

        return result;
    }

    private static boolean isExit(char[][] maze, Point loc) {
        return maze[loc.getY()][loc.getX()] == 'E';
    }

    public static Stack<Point> getMovablePointTest2(char[][] maze, Point loc) {
        int x = loc.getX();
        int y = loc.getY();
        Stack<Point> movable = new Stack<>();

        if (x - 1 >= 0 && maze[x - 1][y] != 'x') { // 북
            movable.push(new Point(x -1, y));
        }

        if (y - 1 >= 0 && maze[x][y - 1] != 'x') { // 서
            movable.push(new Point(x, y - 1));
        }

        if (x + 1 < maze[0].length && maze[x + 1][y] != 'x') { // 남
            movable.push(new Point(x + 1, y));
        }

        if (y + 1 < maze.length && maze[x][y + 1] != 'x') { // 동

            movable.push(new Point(x, y + 1));
        }

        return movable;
    }
}