public class BST {
    public static void main(String[] args) {
        Node root = new Node(null);

        int[] values = {37, 23, 41, 15, 29, 50, 12, 18, 33, 45};
        for (int value : values) {
            root.add(value);
        }

//        System.out.println("== BST 구조 ==");
//        root.printTree();

        System.out.println(root.getNodeOrNull(root, 12).data);


    }

}
