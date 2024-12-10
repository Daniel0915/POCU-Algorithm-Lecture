package bruteForce;

public class Rec {
    static int rightMax = 0;
    static int totalMin = Integer.MAX_VALUE;

    public static void main(String[] args) {
        solution(null);
    }
    public static int solution(int[][] sizes) {
        int[][] nSize = new int[][]{
                {60, 50},
                {30, 70},
                {60, 30},
                {80, 40}
        };

        //가로의 최대 길이
        int max_row = 0;
        // 세로의 최대 길이
        int max_col = 0;

        for (int i = 0; i< sizes.length; i++) {
            if (sizes[i][0] < sizes[i][1]) {
                int tmp  = sizes[i][0];
                sizes[i][0] = sizes[i][1];
                sizes[i][1] = tmp;
            }
            // 최대값
            if (max_row < sizes[i][0]) {
                max_row = sizes[i][0];
            }

            // 최대값
            if (max_col < sizes[i][1]) {
                max_col = sizes[i][1];
            }

        }
        return max_col * max_row;
    }

}
