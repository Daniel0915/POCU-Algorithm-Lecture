package academy.pocu.comp3500.lab5;

public class Bts {
    public static Node getNodeOrNull(final Node node, int data) {
        if (node == null) {
            return null;
        }

        if (node.data == data) {
            return node;
        }

        if (data < node.data) {
            return getNodeOrNull(node.left, data);
        }

        return getNodeOrNull(node.right, data);
    }

    public static void append(final Node node, final int data) {
        if (node.left == null && node.right == null) {
            if (data < node.data) {
                node.setLeft(new Node(data));
                return;
            }

            node.setRight(new Node(data));
            return;
        }

        if (node.right == null) {
            node.right = new Node(data);
            return;
        }

        if (node.left == null) {
            node.left = new Node(data);
            return;
        }

        if (data < node.data) {
            append(node.left, data);
            return;
        }

        append(node.right, data);
        return;
    }

    public static Node insertRecursive(final Node node, int data) {
        if (node == null) {
            return new Node(data);
        }

        if (data < node.data) {
            node.left = insertRecursive(node.left, data);
        } else {
            node.right = insertRecursive(node.right, data);
        }

        return node;
    }

}
