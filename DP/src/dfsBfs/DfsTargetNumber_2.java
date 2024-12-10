package dfsBfs;

public class DfsTargetNumber_2 {
    int count = 0;
    public int solution(int[] numbers, int target) {

        return count;
    }

    public void dfs(int[] numbers, int depth, int result, int target) {
        if (numbers.length == depth) {
            if (result == target) {
                count++;
            }
            return;
        }

        int plus = result + numbers[depth];
        int minus = result - numbers[depth];

        dfs(numbers, depth + 1, plus, target);
        dfs(numbers, depth + 1, minus, target);
    }
}
