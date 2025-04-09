public class Node {
    public Integer  data;
    public Node left;
    public Node right;

    public Node(Integer data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public Node(Integer data) {
        this(data, null, null);
    }

    public void add(Integer data) {
        if (this.data == null) {
            this.data = data;
            return;
        }

        append(data, this);
    }

    private Node append(Integer data, Node node) {
        if (node == null) {
            return new Node(data, null, null);
        }

        if (node.data > data) {
            node.left = append(data, node.left);
        } else {
            node.right = append(data, node.right);
        }
        return node;
    }

    // 트리 구조 시각화 출력
    public void printTree() {
        printTree(this, 0);
    }

    private void printTree(Node node, int depth) {
        if (node == null) return;

        printTree(node.right, depth + 1);  // 오른쪽 먼저

        // 현재 노드 출력 (들여쓰기 포함)
        System.out.println("    ".repeat(depth) + node.data);

        printTree(node.left, depth + 1);  // 왼쪽 나중
    }

    public static Node getNodeOrNull(Node node, int data) {
        if (node == null) {
            return null;
        }

        if (node.data == data) {
            return node;
        }

        // 왼쪽
        if (data < node.data) {
            return getNodeOrNull(node.left, data);
        } else {
            return getNodeOrNull(node.right, data);
        }
    }
}
