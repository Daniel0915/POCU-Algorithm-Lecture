public class BST {
    public static void main(String[] args) {
        Node root = new Node(null);

        int[] values = {6, 4, 10, 2, 1, 3, 5};
        for (int value : values) {
            root.add(value);
        }

        System.out.println("== BST 구조 ==");
        root.printTree();


    }

}
