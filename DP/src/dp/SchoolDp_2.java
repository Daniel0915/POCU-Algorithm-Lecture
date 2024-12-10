package dp;

import java.util.LinkedList;
import java.util.Queue;

public class SchoolDp_2 {
    int[][] directions = {
            {1, 0}, // 동쪽
            {0, -1},  // 남쪽
    };

    // 참고 : https://velog.io/@ajufresh/%EB%93%B1%EA%B5%A3%EA%B8%B8
    public int solution(int m, int n, int[][] puddles) {
        int[][] street = new int[n][m];

        // 웅덩이 -1
        for (int[] puddle : puddles) {
            street[puddle[1] - 1][puddle[0] -1] = -1;
        }

        // 시작점은 1로 저장
        street[0][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                // 웅덩이면 0 으로 바꾸고 continue
                if (street[i][j] == -1) {
                    street[i][j] = 0;
                    continue;
                }

                // Target 좌표의 위쪽
                if (i != 0) {
                    street[i][j] += street[i - 1][j] % 1000000007;
                }

                // Target 좌표의 왼쪽
                if (j != 0) {
                    street[i][j] += street[i][j - 1] % 1000000007;
                }
            }
        }

        return street[n - 1][m - 1] % 1000000007;
    }
}
