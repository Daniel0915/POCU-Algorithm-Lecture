package dfsBfs;

import java.util.*;

// 출처 : https://www.acmicpc.net/problem/2667
public class BackJun2667App {
    public static void main(String[] args) {
        int[][] maze = new int[][]{
                        {1, 1, 1, 0, 1, 0, 0},
                        {1, 1, 1, 0, 1, 0, 1},
                        {1, 1, 1, 0, 1, 0, 1},
                        {1, 0, 0, 0, 1, 1, 1},
                        {0, 1, 0, 0, 0, 0, 0},
                        {0, 1, 1, 1, 1, 1, 0},
                        {0, 1, 1, 1, 0, 0, 1}
        };
        BackJun2667 backJun2667 = new BackJun2667();


        backJun2667.solution(maze).stream().forEach(System.out::println);
    }
}
