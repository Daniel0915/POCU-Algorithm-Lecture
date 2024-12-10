public class DFSExample {

    // 동, 서, 남, 북 방향 이동을 위한 배열 (우, 좌, 하, 상)
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) {
        DFSExample dfsExample = new DFSExample();
        int[][] maps = {
                {1, 2, 1},
                {8, 2, 0},
                {1, 7, 2}
        };

        int[] entrancePoint = {0, 0}; // 시작점
        boolean result = dfsExample.solution(maps, entrancePoint); // 7에 도달할 수 있는지 여부
        System.out.println("7에 도달 가능 여부: " + result);
    }

    public boolean solution(int[][] maps, int[] entrancePoint) {
        // 방문 체크 배열 생성 (방문한 좌표를 표시)
        boolean[][] visited = new boolean[maps.length][maps[0].length];

        // 시작점에서 DFS 실행
        return dfs(maps, visited, entrancePoint[0], entrancePoint[1], false);
    }

    // DFS 메서드: 현재 좌표에서 목표(숫자 7)에 도달할 수 있는지 확인
    private boolean dfs(int[][] maps, boolean[][] visited, int x, int y, boolean isEvenNumber) {
        // 범위를 벗어나거나 이미 방문한 곳이면 false 반환
        if (x < 0 || x >= maps.length || y < 0 || y >= maps[0].length || visited[x][y]) {
            return false;
        }

        // 목표 지점 (숫자 7)을 찾으면 true 반환
        if (maps[x][y] == 7) {
            return true;
        }

        // 현재 위치 방문 처리
        visited[x][y] = true;

        // 상하좌우로 DFS 탐색
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i]; // 다음 x 좌표
            int ny = y + dy[i]; // 다음 y 좌표

            // 다음 위치로 DFS를 진행하며, 목표에 도달하면 true 반환
            if (dfs(maps, visited, nx, ny, !isEvenNumber)) {
                return true;
            }
        }

        // 7을 찾지 못한 경우 false 반환
        return false;
    }
}
