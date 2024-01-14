package academy.pocu.comp3500.lab8;

public class MazeAdjacencyMatrix {

    public static int[][] createAdjacencyMatrix(char[][] maze) {
        int rows = maze.length;
        int cols = maze[0].length;
        int size = rows * cols;

        int[][] adjacencyMatrix = new int[size][size];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maze[i][j] == ' ') {
                    int nodeIndex = i * cols + j;

                    // Check the adjacent open spaces
                    if (i - 1 >= 0 && maze[i - 1][j] == ' ') {
                        int adjacentIndex = (i - 1) * cols + j;
                        adjacencyMatrix[nodeIndex][adjacentIndex] = 1;
                    }
                    if (i + 1 < rows && maze[i + 1][j] == ' ') {
                        int adjacentIndex = (i + 1) * cols + j;
                        adjacencyMatrix[nodeIndex][adjacentIndex] = 1;
                    }
                    if (j - 1 >= 0 && maze[i][j - 1] == ' ') {
                        int adjacentIndex = i * cols + (j - 1);
                        adjacencyMatrix[nodeIndex][adjacentIndex] = 1;
                    }
                    if (j + 1 < cols && maze[i][j + 1] == ' ') {
                        int adjacentIndex = i * cols + (j + 1);
                        adjacencyMatrix[nodeIndex][adjacentIndex] = 1;
                    }
                }
            }
        }

        return adjacencyMatrix;
    }

}
