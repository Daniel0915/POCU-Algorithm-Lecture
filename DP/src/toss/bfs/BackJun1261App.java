package toss.bfs;

import toss.bfs.practice.BackJun1261;

public class BackJun1261App {
    public static void main(String[] args) {
        BackJun1261 backJun1261 = new BackJun1261();
//        BackJun1261 backJun1261 = new BackJun1261();

        int[][] maps = new int[][]{
                {0, 0, 1},
                {1, 1, 0},
                {1, 1, 0}
        };
        int m = 3;
        int n = 3;
        System.out.println(backJun1261.solution(maps, m, n));

//        if(visited[nX][nY] == 0 && maps[nX][nY] == 1){
//            visited[nX][nY] = visited[cX][cY] + 1;
//            queue.add(new int[]{nX, nY});
//        }


    }
}
