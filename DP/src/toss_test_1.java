import toss.bfs.BackJun1261;

import java.util.LinkedList;
import java.util.Queue;

public class toss_test_1 {
    // 동 서 남 북
    int[] dx = {0, 0, 1, -1};
    int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) {
        toss_test_1 tossTest1 = new toss_test_1();
        int[][] maps = new int[][]{
                {1,2,1},
                {8,2,0},
                {1,7,2}
        };

        int[] entrancePoint = new int[]{0,0};
        System.out.println(tossTest1.solution(maps, entrancePoint));
    }

    public boolean solution(int[][] maps, int[] entrancePoint) {
        int[][] visited = new int[maps.length][maps[0].length];

        return bfs(maps,visited,entrancePoint);
    }

    private boolean bfs(int[][] maps, int[][] visited, int[] entrancePoint) {
        visited[entrancePoint[0]][entrancePoint[1]] = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(entrancePoint);

        int a = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.remove();
            int cX = current[0];
            int cY = current[1];

            if (maps[cX][cY] == 7) {
                return true;
            }

            if ((a % 2) == 0) {
                for (int i = 0; i < 2; i++) {
                    int nX = cX + dx[i];
                    int nY = cY + dy[i];

                    // 막혀 있는 영역
                    if (nX < 0 || nX > maps.length - 1 || nY < 0 || nY > maps[0].length - 1)
                        continue;

                    // 다음 이동할 영역이
                    // 1. 방문하지 않은 영역
                    // 2. 이동할 수 있음
                    if(visited[nX][nY] == 0) {
                        visited[nX][nY] = visited[cX][cY] + 1;
                        queue.add(new int[]{nX, nY});
                    }
                }
            } else {
                for (int i = 2; i < 4; i++) {
                    int nX = cX + dx[i];
                    int nY = cY + dy[i];

                    // 막혀 있는 영역
                    if (nX < 0 || nX > maps.length - 1 || nY < 0 || nY > maps[0].length - 1)
                        continue;

                    // 다음 이동할 영역이
                    // 1. 방문하지 않은 영역
                    // 2. 이동할 수 있음
                    if(visited[nX][nY] == 0) {
                        visited[nX][nY] = visited[cX][cY] + 1;
                        queue.add(new int[]{nX, nY});
                    }
                }
            }
            a++;
        }
        return false;
    }


}
