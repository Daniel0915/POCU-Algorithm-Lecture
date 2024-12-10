package dfsBfs;

public class BackJun14497App {
    public static void main(String[] args) {
        BackJun14497 backJun14497 = new BackJun14497();

        String[][] maps = {
                {"1", "#", "1", "0", "1", "1", "1"},
                {"1", "1", "0", "1", "0", "0", "1"},
                {"0", "0", "1", "*", "1", "1", "1"},
                {"1", "1", "0", "1", "1", "1", "1"},
                {"0", "0", "1", "1", "0", "0", "1"}
        };
        int n = 5;
        int m = 7;
        System.out.println(backJun14497.solution(maps, n, m));

        String[][] maps1 = {
                {"#", "0", "0", "0", "0"},
                {"1", "1", "1", "1", "1"},
                {"0", "0", "0", "0", "*"}
        };


        System.out.println(backJun14497.solution(maps1, n, m));

        String[][] maps2 = {
                {"#", "0", "0"},
                {"0", "*", "0"},
                {"0", "0", "0"}
        };

        System.out.println(backJun14497.solution(maps2, n, m));

    }
}
