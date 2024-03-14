package academy.pocu.comp3500.lab8.app;

import static academy.pocu.comp3500.lab8.MazeAdjacencyMatrix.createAdjacencyMatrix;

import academy.pocu.comp3500.lab8.MazeSolver;
import academy.pocu.comp3500.lab8.maze.Point;
import java.util.List;
import java.util.Stack;

public class Program {

    public static void main(String[] args) {
        char[][] maze8x6 = new char[][]{
                {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
                {'x', 'x', ' ', 'x', ' ', 'E', ' ', 'x'},
                {'x', 'x', ' ', 'x', ' ', 'x', 'x', 'x'},
                {'x', ' ', ' ', ' ', ' ', 'x', ' ', 'x'},
                {'x', 'x', ' ', 'x', ' ', ' ', ' ', 'x'},
                {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}
        };

        List<Point> result = MazeSolver.findPath(maze8x6, new Point(2, 2));

        assert (result.size() == 7);
        assert (result.get(0).getX() == 2 && result.get(0).getY() == 2);
        assert (result.get(6).getX() == 5 && result.get(6).getY() == 1);


    }
}
