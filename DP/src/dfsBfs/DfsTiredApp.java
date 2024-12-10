package dfsBfs;

import java.util.Arrays;

// 출처 : https://school.programmers.co.kr/learn/courses/30/lessons/87946
public class DfsTiredApp {
    public static void main(String[] args) {
        DfsTired dfsTired = new DfsTired();
        int[][] dungeons = new int[][]{{80,20},{50,40},{30,10}};
        System.out.println(dfsTired.solution(80, dungeons));
    }
}
