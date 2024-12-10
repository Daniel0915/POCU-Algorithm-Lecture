package toss.floyd;

import java.util.Arrays;

// 출처 : https://www.acmicpc.net/problem/11403
public class BackJun11403Floyd {
    public static void main(String[] args) {
        int size = 7;
        int[][] inputMap = {
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 0}
        };

        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (inputMap[i][k] == 1 && inputMap[k][j] == 1) {
                        inputMap[i][j] = 1;
                    }
                }
            }
        }
        print2DArray(inputMap);
    }

    private static void print2DArray(int[][] array) {
        for (int[] row : array) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println(); // 줄바꿈을 추가하여 가독성 향상
    }
}
