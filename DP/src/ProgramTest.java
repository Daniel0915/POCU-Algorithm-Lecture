public class ProgramTest {
    public static void main(String[] args) {
//        DfsTargetNumber dfsTargetNumber = new DfsTargetNumber();
//
//        int[] a = new int[]{1, 1, 1, 1, 1};
//
//        dfsTargetNumber.solution(a, 3);

//        DfsNetwork dfsNetwork = new DfsNetwork();
//
//        int n1 = 3;
//        int[][] computers1 = { {1, 1, 0}, {1, 1, 0}, {0, 0, 1} };
//        System.out.println("Number of networks: " + dfsNetwork.solution(n1, computers1)); // Output: 2
//
//        int n2 = 3;
//        int[][] computers2 = { {1, 1, 0}, {1, 1, 1}, {0, 1, 1} };
//        System.out.println("Number of networks: " + dfsNetwork.solution(n2, computers2)); // Output: 1

        BfsGame bfsGame = new BfsGame();
        int[][] maps = { {1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1} };
        int solution = bfsGame.solution(maps);

        System.out.println("solution = " + solution);
    }
}
