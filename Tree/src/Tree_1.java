import java.util.Objects;

public class Tree_1 {
    public static void main(String[] args) {
//        System.out.println(isSame("racecar"));
//        System.out.println(GCD(98, 56));

        Hanoi(3, 1, 2, 3);


    }


    public static void recursive(int n) {
        if (n <= 0) {
            return;
        }

        recursive(n - 1);
        System.out.println(n);
    }

    public static void recursive_2(int n) {
        if (n <= 0) {
            return;
        }

        System.out.println(n);
        recursive_2(n - 1);
    }

    public static int recursive_3(int n) {
        if (n <= 1) {
            return n;
        }

        return recursive_3(n - 1) + n;
    }

    /*
     * 팰린드롬 검사
     * 문자열이 주어졌을 때, 해당 문자열이 앞에서부터 읽어도 뒤에서부터 읽어도 같은지 확인하는 함수를 재귀로 구현하세요.
     */
    public static boolean isSame(String str) {
        return Objects.equals(str, recursive_4(str, 0));
    }

    public static String recursive_4(String str, int index) {
        if ( Objects.equals(index, str.length() - 1) ) {
            return String.valueOf(str.charAt(index));
        }

        return recursive_4(str, index + 1) + str.charAt(index);
    }

    public static int GCD(int a, int b) {
        if (a == b) {
            return a;
        }

        if (a > b) {
            return recursive_5(a, b);
        }
        return recursive_5(b, a);
    }

    public static int recursive_5(int a, int b) {
        if (a % b == 0) {
            return b;
        }

        return recursive_5(b, a % b);
    }

    public static String recursive_6(int a) {
        if (a < 2) {
            return String.valueOf( a % 2);
        }

        return recursive_6(a / 2) + a % 2;
    }

    public static void recursive_7(char a, char b, char c, int n) {
        if (n <= 1) {
            return;
        }

        recursive_7(b, c, a, n - 1);
        System.out.println(String.format("%s -> %s", a, b));
        System.out.println(String.format("%s -> %s", a, c));
        System.out.println(String.format("%s -> %s", b, c));
        recursive_7(c, a, b, n - 2);
    }

    /**
     *
     * @param N
     * @param start : A
     * @param mid : B
     * @param to : C
     */
    public static void Hanoi(int N, int start, int mid, int to) {

        // 이동할 원반의 수가 1개라면?
        if (N == 1) {
            System.out.println(start + " " + to + "\n");
            return;
        }

        // A -> C로 옮긴다고 가정할 떄,
        // STEP 1 : N-1개를 A에서 B로 이동 (= start 지점의 N-1개의 원판을 mid 지점으로 옮긴다.)
        Hanoi(N - 1, start, to, mid);

        // STEP 2 : 1개를 A에서 C로 이동 (= start 지점의 N번째 원판을 to지점으로 옮긴다.)
        System.out.println(start + " " + to + "\n");

        // STEP 3 : N-1개를 B에서 C로 이동 (= mid 지점의 N-1개의 원판을 to 지점으로 옮긴다.)
        Hanoi(N - 1, mid, start, to);
    }
}
