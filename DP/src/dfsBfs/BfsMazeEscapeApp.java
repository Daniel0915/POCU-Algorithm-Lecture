package dfsBfs;

import java.util.Arrays;

// 출처 : https://school.programmers.co.kr/learn/courses/30/lessons/159993
public class BfsMazeEscapeApp {
    public static void main(String[] args) {
        BfsMazeEscape bfsMazeEscape = new BfsMazeEscape();

        String[] map = {"LOOXS","OOOOX","OOOOO","OOOOO","EOOOO"};

        System.out.println(bfsMazeEscape.solution(map));
    }
}
