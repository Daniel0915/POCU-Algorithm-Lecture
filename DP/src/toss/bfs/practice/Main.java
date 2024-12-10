package toss.bfs.practice;

public class Main {
    public static void main(String[] args) {
        BackJun1261 backJun1261 = new BackJun1261();

        int[][] maps = new int[][]{
                {0, 1, 1},
                {1, 1, 1},
                {1, 1, 0}
        };
        int m = 3;
        int n = 3;

        System.out.println(backJun1261.solution(maps, m, n));
    }
}
