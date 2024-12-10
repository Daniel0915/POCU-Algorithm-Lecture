package toss;

import java.util.LinkedList;
import java.util.Queue;

public class BFSAlternatingDirection {

    // 동, 서, 남, 북
    int[] dx = {0, 0, 1, -1}; // x 좌표 변화 (좌, 우, 하, 상)
    int[] dy = {1, -1, 0, 0}; // y 좌표 변화 (우, 좌, 하, 상)

    public static void main(String[] args) {
        BFSAlternatingDirection bfsTest = new BFSAlternatingDirection();
        int[][] maps = new int[][]{
                {1, 2, 1},
                {8, 2, 0},
                {1, 7, 2}
        };

        int[] entrancePoint = new int[]{0, 0};
        System.out.println(bfsTest.solution(maps, entrancePoint));  // 7에 도달하면 true 반환
    }

    public boolean solution(int[][] maps, int[] entrancePoint) {
        int[][] visited = new int[maps.length][maps[0].length];  // 방문 여부를 체크하는 배열
        return bfs(maps, visited, entrancePoint);  // BFS 실행
    }

    private boolean bfs(int[][] maps, int[][] visited, int[] entrancePoint) {
        visited[entrancePoint[0]][entrancePoint[1]] = 1;  // 시작점 방문 표시
        Queue<int[]> queue = new LinkedList<>();
        queue.add(entrancePoint);

        int directionToggle = 0;  // 0이면 좌우 이동, 1이면 상하 이동

        while (!queue.isEmpty()) {
            int[] current = queue.remove();  // 현재 위치를 큐에서 꺼냄
            int cX = current[0];
            int cY = current[1];

            // 목표 지점인 숫자 7을 찾으면 true 반환
            if (maps[cX][cY] == 7) {
                return true;
            }

            // directionToggle에 따라 이동 방향을 결정 (짝수면 좌우, 홀수면 상하)
            if (directionToggle % 2 == 0) {
                // 좌우로 이동 (i=0,1 -> 우, 좌)
                move(queue, maps, visited, cX, cY, 0, 2);
            } else {
                // 상하로 이동 (i=2,3 -> 하, 상)
                move(queue, maps, visited, cX, cY, 2, 4);
            }

            directionToggle++;  // 매번 방향을 바꾸기 위해 토글
        }
        return false;  // 7에 도달하지 못하면 false 반환
    }

    // 실제로 이동하는 메서드: 범위에 따라 특정 방향으로 이동
    private void move(Queue<int[]> queue, int[][] maps, int[][] visited, int cX, int cY, int start, int end) {
        for (int i = start; i < end; i++) {
            int nX = cX + dx[i];
            int nY = cY + dy[i];

            // 막혀 있는 영역 검사 (지도 범위를 벗어나면 무시)
            if (nX < 0 || nX >= maps.length || nY < 0 || nY >= maps[0].length) {
                continue;
            }

            // 방문하지 않은 영역이고 이동할 수 있으면 큐에 추가
            if (visited[nX][nY] == 0) {
                visited[nX][nY] = visited[cX][cY] + 1;  // 방문 표시 (거리 정보 업데이트)
                queue.add(new int[]{nX, nY});  // 다음 이동할 좌표를 큐에 추가
            }
        }
    }
}

