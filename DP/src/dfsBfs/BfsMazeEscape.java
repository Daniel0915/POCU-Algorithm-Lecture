package dfsBfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

// 출처 : https://school.programmers.co.kr/learn/courses/30/lessons/159993
public class BfsMazeEscape {
    // 동 서 남 북
    int[] dX = {1, -1, 0, 0};
    int[] dY = {0, 0, -1, 1};
    int[] startPoint;

    public int solution(String[] maps) {
        String[][] mapsXY = new String[maps.length][maps[0].length()];

        int[][] visited = new int[maps.length][maps[0].length()];

        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length(); j++) {
                if (Objects.equals(maps[i].charAt(j), 'S')) {
                    startPoint = new int[]{i, j};
                }
                mapsXY[i][j] = Character.toString(maps[i].charAt(j));
            }
        }

        return bfs(mapsXY, visited);
    }

    private int bfs(String[][] maps, int[][] visited) {
        // 시작점
        int x = startPoint[0];
        int y = startPoint[1];
        visited[x][y] = 0;
        Queue<int[]> queueForLever = new LinkedList<>();
        queueForLever.add(new int[]{x, y});

        int[] leverPoint = new int[]{-1, -1};

        while (!queueForLever.isEmpty()) {
            int[] current = queueForLever.remove();
            int cX = current[0];
            int cY = current[1];

            for (int i = 0; i < 4; i++) {
                int nX = cX + dX[i];
                int nY = cY + dY[i];

                if (Objects.equals(maps[cX][cY], "L")) {
                    leverPoint = new int[]{cX, cY};
                }

                if (nX < 0 || nX > maps.length - 1 || nY < 0 || nY > maps[0].length - 1) {
                    continue;
                }

                if (visited[nX][nY] == 0 && !Objects.equals(maps[nX][nY], "X")) {
                    visited[nX][nY] = visited[cX][cY] + 1;
                    queueForLever.add(new int[]{nX, nY});
                }
            }
        }

        if (leverPoint[0] == -1 && leverPoint[1] == -1) {
            return -1;
        }


        int leverCnt = visited[leverPoint[0]][leverPoint[1]];


        // visited init
        visited = new int[maps.length][maps[0].length];

        // leverStartPoint
        int leverStartX = leverPoint[0];
        int leverStartY = leverPoint[1];
        visited[x][y] = 0;
        Queue<int[]> queueForEnd = new LinkedList<>();
        queueForEnd.add(new int[]{leverStartX, leverStartY});

        while (!queueForEnd.isEmpty()) {
            int[] current = queueForEnd.remove();
            int cX = current[0];
            int cY = current[1];

            for (int i = 0; i < 4; i++) {
                int nX = cX + dX[i];
                int nY = cY + dY[i];

                if (Objects.equals(maps[cX][cY], "E")) {
                    return visited[cX][cY] + leverCnt;
                }

                if (nX < 0 || nX > maps.length - 1 || nY < 0 || nY > maps[0].length - 1) {
                    continue;
                }

                if (visited[nX][nY] == 0 && !Objects.equals(maps[nX][nY], "X")) {
                    visited[nX][nY] = visited[cX][cY] + 1;
                    queueForEnd.add(new int[]{nX, nY});
                }
            }
        }

        return -1;
    }

    private void print2DArray(String[][] array) {
        for (String[] row : array) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println(); // 줄바꿈을 추가하여 가독성 향상
    }

    private void print2DArray(int[][] array) {
        for (int[] row : array) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println(); // 줄바꿈을 추가하여 가독성 향상
    }
}
