package dfsBfs;

import java.util.*;

public class BfsPuzzle_2 {
    List<List<Point>> t = new ArrayList<>();
    List<List<Point>> g = new ArrayList<>();

    // 동, 서, 남, 북
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        // 규칙에 맞게 최대한 많은 ㅍ즐 조각을 채워 넣을 경우, 총 몇 칸을 채울 수 있는지 return
        int len = game_board.length;

        // game_board 0, 1 바꿔주기
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (game_board[i][j] == 1) {
                    game_board[i][j] = 0;
                } else {
                    game_board[i][j] = 1;
                }
            }
        }

        // 각 게임 보드와 테이블의 방문 기록(메모제이션)
        boolean[][] visited_t = new boolean[len][len];
        boolean[][] visited_g = new boolean[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                // table 에서 블록 추출
                if (table[i][j] == 1 && !visited_t[i][j]) {
                    bfs(i, j, table, visited_t, t);
                }

                // game_board 에서 빈공간 추출
                if (game_board[i][j] == 1 && !visited_g[i][j]) {
                    bfs(i , j, game_board, visited_g, g);
                }
            }
        }

//        System.out.println("================= table ==================");
//        printGraph(t);
//        System.out.println("================= game_board ==================");
//        printGraph(g);

        // table 의 블록과 board 빈 공간의 블록을 회전하면서 비교해주기
        answer = compareBlock(t, g, answer);
        return answer;
    }

    private int compareBlock(List<List<Point>> table, List<List<Point>> board, int answer) {
        int table_size = table.size();
        int board_size = board.size();

        boolean[] visited = new boolean[board_size];

        for (int i = 0; i < table_size; i++) {
            for (int j = 0; j < board_size; j++) {

                // 방문했거나, 테이블(블록의 모음들)을 게임 보드에 블록을 넣지 못하는 경우 -> 넘기기
                if (visited[j] || table.get(i).size() != board.get(j).size()) {
                    continue;
                }

                if (isRotate(table.get(i), board.get(j))) {
                    visited[j] = true; // 블록으로 채워짐
                    answer += board.get(j).size();
                    break;
                }
            }
        }

        return answer;
    }

    private boolean isRotate(List<Point> table, List<Point> board) {
        // 오름차순 정렬
        Collections.sort(board);

        // 90도씩 회전시켜보기. 0, 90, 180, 270
        for (int i = 0; i < 4; i++) {
            // 오름차순 정렬. table 은 회전할때마다 다시 정렬해줌.
            Collections.sort(table);

            int curr_x = table.get(0).x;
            int curr_y = table.get(0).y;

            // 회전하면서 좌표가 바뀌기 때문에, 다시 (0, 0) 기준으로 세팅
            for (int j = 0; j < table.size(); j++) {
                table.get(j).x -= curr_x;
                table.get(j).y -= curr_y;
            }

            boolean check = true;

            // 좌표 비교
            for (int j = 0; j < board.size(); j++) {
                if (board.get(j).x != table.get(j).x || board.get(j).y != table.get(j).y) {
                    check = false;
                    break;
                }
            }

            if (check) {
                return true;
            } else {
                // 90 도 회전시키기 x, y -> y, -x
                for (int j = 0; j < table.size(); j++) {
                    int temp = table.get(j).x;
                    table.get(j).x = table.get(j).y;
                    table.get(j).y = -temp;
                }
            }
        }
        return false;
    }

    private void bfs(int x, int y, int[][] board, boolean[][] visited, List<List<Point>> list) {
        visited[x][y] = true;

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));

        List<Point> sub_list = new ArrayList<>();

        // (0, 0) 기준으로 넣어줌
        sub_list.add(new Point(0, 0));

        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || nx > board.length - 1 || ny < 0 || ny > board[0].length - 1) {
                    continue;
                }

                if (!visited[nx][ny] && board[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    q.add(new Point(nx, ny));
                    // (0, 0) 기준으로 넣기 떄문에
                    sub_list.add(new Point(nx - x, ny - y));
                }
            }
        }

        list.add(sub_list);
    }

    static class Point implements Comparable<Point> {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }


        @Override
        public int compareTo(Point o) {
            int res = Integer.compare(this.x , o.x);
            if (res != 0) {
                return  res;
            }

            return Integer.compare(this.y, o.y);
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    public static void printGraph(List<List<Point>> graph) {
        for (int i = 0; i < graph.size(); i++) {
            System.out.print("Vertex " + i + ": ");
            for (Point p : graph.get(i)) {
                System.out.print(p + " ");
            }
            System.out.println();
        }
    }
}
