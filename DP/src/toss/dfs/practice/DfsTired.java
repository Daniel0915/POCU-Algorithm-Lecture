package toss.dfs.practice;

// 출처 : https://school.programmers.co.kr/learn/courses/30/lessons/87946
public class DfsTired {
    boolean[] visited;
    int count = 0;

    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        dfs(0, k , dungeons);
        return count;
    }

    private void dfs(int depth, int k, int[][] dungeons) {
        for (int i = 0; i < dungeons.length; i++) {
            if (visited[i] || dungeons[i][0] > k) {
                continue;
            }

            visited[i] = true;
            dfs(depth + 1, k - dungeons[i][1], dungeons);
            visited[i] = false;
        }

        count = Math.max(count, depth);
    }
}
