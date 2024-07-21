public class DfsTargetNumber {
    int count = 0;
    public int solution(int[] numbers, int target) {
        dfs(numbers, 0, target, 0);
        System.out.println(count);
        return count;
    }

    public void dfs(int[] numbers, int depth, int target, int result) {
        if (depth == numbers.length) {
            if (target == result) {
                count++;
            }
            return;
        }

        int plus = result + numbers[depth];
        int minus = result - numbers[depth];

        dfs(numbers, depth + 1, target, plus);
        dfs(numbers, depth + 1, target, minus);

    }

    public Node insertRecursive(final Node node, int value, boolean isLast) {
        if (node == null) {
            return new Node(value);
        }

        int nowNodeValue = node.value;

        // 왼쪽 노드
        if (value < nowNodeValue) {
            node.left  = insertRecursive(node.left, value, isLast);
        } else {
            node.right = insertRecursive(node.right, value, isLast);
        }

        return node;
    }
}
