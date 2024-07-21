import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test1 {
    public static void main(String[] args) {
        System.out.println(solution(2, 11));

    }

    public static int solution(int N , int number) {
        if (N == number) {
            return 1;
        }

        List<Set<Integer>> countList = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            countList.add(new HashSet<>());
        }

        countList.get(1).add(N);

        for (int i = 2; i < 9; i++) {
            Set<Integer> countSet = countList.get(i);

            // 4(i)
            // 1(j) + 3(i - j) = 4
            // 2(j) + 2(i - j) = 4
            // 3(j) + 1(i - j) = 4
            for (int j = 1; j <= i; j++) {
                Set<Integer> preSet = countList.get(j);
                Set<Integer> postSet = countList.get(i - j);

                for (int preNum : preSet) {
                    for (int postNum : postSet) {
                        countSet.add(preNum + postNum);
                        countSet.add(preNum - postNum);
                        countSet.add(preNum * postNum);

                        if (preNum != 0 && postNum != 0) {
                            countSet.add(preNum / postNum);
                        }
                    }
                }
            }
            countSet.add(Integer.parseInt(String.valueOf(N).repeat(i)));

            for (int target : countSet) {
                if (number == target) {
                    return i;
                }
            }
        }

        return -1;
    }
}
