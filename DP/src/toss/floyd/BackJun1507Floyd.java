package toss.floyd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 출처 : https://www.acmicpc.net/problem/1507
public class BackJun1507Floyd {
    static int N;
    static int[][] dist;
    static int[][] original;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dist = new int[N][N];
        original = new int[N][N];

        // 입력 받기
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
                original[i][j] = dist[i][j];  // 원본 배열 저장
            }
        }

        print2DArray(original);

        // 플로이드-와샬을 통해 불필요한 도로를 제거
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // i == j 인 경우는 거리가 0이므로 건너뛴다.
                    if (i == j || i == k || j == k) continue;

                    // 아래 조건은 "모든 쌍의 도시 사이의 최소 이동 시간이 주어졌을 때" 케이스로 추가됨.
                    // i에서 j로 가는 거리가 i에서 k를 거쳐 j로 가는 것보다 크면 모순이 발생
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        System.out.println(-1);
                        return;
                    }

                    // i에서 j로 가는 거리가 i에서 k를 거쳐 j로 가는 것과 동일하면 직접 연결 도로가 아니므로 제거
                    if (dist[i][j] == dist[i][k] + dist[k][j]) {
                        original[i][j] = 0;  // 중간 경유지가 있는 도로는 직접 연결 도로가 아님
                    }
                }
            }
        }

        print2DArray(original);

        // 도로 시간 합 계산
        int roadSum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                roadSum += original[i][j];
            }
        }

        System.out.println(roadSum);

    }

    private static void print2DArray(int[][] array) {
        for (int[] row : array) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println(); // 줄바꿈을 추가하여 가독성 향상
    }
}
