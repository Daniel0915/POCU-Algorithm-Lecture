package toss.dp;

import java.util.LinkedList;
import java.util.Queue;

public class SchoolDp {
    int[][] directions = {
            {1, 0}, // 동쪽
            {0, -1},  // 남쪽
    };

    // 참고 : https://velog.io/@ajufresh/%EB%93%B1%EA%B5%A3%EA%B8%B8
    public int solution(int m, int n, int[][] puddles) {
        int[][] street = new int[n][m];

        // 웅덩이 -1
        for (int[] puddle : puddles) {
            street[puddle[1] - 1][puddle[0] - 1] = -1;
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

        return street[n - 1][m -1] % 1000000007;
    }

    public int solution2(int m, int n, int[][] puddles) {
        int x = n - 1;
        int y = m - 1;

        int[][] maps = new int[x + 1][y + 1];

        // 배열의 모든 요소를 1로 변경
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length; j++) {
                maps[i][j] = 1;
            }
        }
        // 물 웅덩이 표시
        maps[puddles[0][1] - 1][puddles[0][0] -1] = 0;

        int[][] visited = new int[maps.length][maps[0].length];
        bfs(maps, visited);

        int answer = visited[maps.length - 1][maps[0].length - 1];

        if (answer == 0) {
            answer = -1;
        }

        return answer;
    }

    public void bfs(int[][] maps, int[][] visited) {
        int x = 0;
        int y = 0;
        visited[x][y] = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] current = queue.remove();
            int cX= current[0];
            int cY= current[1];

            // 동쪽, 남쪽
            for (int i = 0; i < directions.length; i++) {
                int nX = cX + directions[i][0];
                int nY = cY + directions[i][1];

                // 막힌 곳
                if (nX < 0 || nX > maps.length-1 || nY < 0 || nY > maps[0].length-1) {
                    continue;
                }

                // 물웅덩이이거나 이미 방문한 곳은 제외
                if (maps[nX][nY] == -1 || visited[nX][nY] != 0) {
                    continue;
                }

                visited[nX][nY] = visited[cX][cY] + 1;
                queue.add(new int[]{nX, nY});
            }
        }
    }


}
