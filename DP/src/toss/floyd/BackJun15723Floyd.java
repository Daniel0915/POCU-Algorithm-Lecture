package toss.floyd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 출처 : https://www.acmicpc.net/problem/15723
public class BackJun15723Floyd {
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        boolean[][] connected = new boolean[26][26];

        while(N --> 0) {
            String[] str = br.readLine().split(" is ");
            char a = str[0].charAt(0);
            char b = str[1].charAt(0);

            connected[a - 'a'][b - 'a'] = true;
        }

        for(int k = 0 ; k < 26 ; k++) {
            for(int i = 0 ; i < 26 ; i++) {
                for(int j = 0 ; j < 26 ; j++) {
                    if(i == j || j == k || k == i)  continue;

                    if (connected[i][k] && connected[k][j]) {
                        connected[i][j] = true;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        while(M --> 0) {
            String[] str = br.readLine().split(" is ");
            char a = str[0].charAt(0);
            char b = str[1].charAt(0);

            sb.append(connected[a - 'a'][b - 'a'] ? "T" : "F");
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void print2DArray(int[][] array) {
        for (int[] row : array) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println(); // 줄바꿈을 추가하여 가독성 향상
    }

    private static void print2DArray(boolean[][] array) {
        for (boolean[] row : array) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println(); // 줄바꿈을 추가하여 가독성 향상
    }
}
