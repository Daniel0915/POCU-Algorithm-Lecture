package academy.pocu.comp3500.lab2.app;

import academy.pocu.comp3500.lab2.LinkedList;
import academy.pocu.comp3500.lab2.Stack;
import academy.pocu.comp3500.lab2.Queue;
import academy.pocu.comp3500.lab2.datastructure.Node;

public class Program {

    public static void main(String[] args) {
        Node root = LinkedList.append(null, 0);
        LinkedList.append(root, 1);
        LinkedList.append(root, 2);
        LinkedList.append(root, 3);


//        root = LinkedList.insertAt(root, 2, 3); // root: 0, list: 0 -> 1 -> 3 -> 4
        root = LinkedList.insertAt(root, 1, 4); // root: 11, list: 0 -> 1 -> 2 -> 3 -> 4

    }

    public static void testRemoveAt() {
        Node root = LinkedList.append(null, 10);
        root = LinkedList.append(root, 20);
        root = LinkedList.append(root, 30);
        root = LinkedList.append(root, 40);

        root = LinkedList.removeAt(root, -1);

        Node node = root;
        assert (node.getData() == 10);
        node = node.getNextOrNull();
        assert (node.getData() == 20);
        node = node.getNextOrNull();
        assert (node.getData() == 30);
        node = node.getNextOrNull();
        assert (node.getData() == 40);
        node = node.getNextOrNull();
        assert (node == null);

        root = LinkedList.removeAt(root, 4);

        node = root;
        assert (node.getData() == 10);
        node = node.getNextOrNull();
        assert (node.getData() == 20);
        node = node.getNextOrNull();
        assert (node.getData() == 30);
        node = node.getNextOrNull();
        assert (node.getData() == 40);
        node = node.getNextOrNull();
        assert (node == null);

        root = LinkedList.removeAt(root, 3);

        node = root;
        assert (node.getData() == 10);
        node = node.getNextOrNull();
        assert (node.getData() == 20);
        node = node.getNextOrNull();
        assert (node.getData() == 30);
        node = node.getNextOrNull();
        assert (node == null);

        root = LinkedList.removeAt(root, 0);

        node = root;
        assert (node.getData() == 20);
        node = node.getNextOrNull();
        assert (node.getData() == 30);
        node = node.getNextOrNull();
        assert (node == null);

        node = LinkedList.removeAt(null, 0);
        assert (node == null);

        node = LinkedList.removeAt(null, -1);
        assert (node == null);

        node = LinkedList.removeAt(null, 100);
        assert (node == null);
    }

    public static void testInsertAt() {
        Node root = LinkedList.append(null, 20);

        root = LinkedList.insertAt(root, -1, -100);

        root = LinkedList.insertAt(root, 0, 10);

        root = LinkedList.insertAt(root, 2 ,40);

        root = LinkedList.insertAt(root, 2 ,30);

        root = LinkedList.insertAt(root, 5, 10000);

        Node node = root;

        assert (node.getData() == 10);

        node = node.getNextOrNull();

        assert (node.getData() == 20);

        node = node.getNextOrNull();

        assert (node.getData() == 30);

        node = node.getNextOrNull();

        assert (node.getData() == 40);

        node = node.getNextOrNull();

        assert (node == null);

        node = LinkedList.insertAt(null, 0, 10);

        assert (node.getData() == 10);

        node = LinkedList.insertAt(null, -1 , 10);

        assert (node == null);

        node = LinkedList.insertAt(null, 100, 10);

        assert (node == null);
    }

    public static void testInterleaveEdge2() {
        Node head1 = LinkedList.append(null, 3);
        Node linked = LinkedList.interleaveOrNull(null, head1);
        assert linked.getData() == 3;

        Node linked2 = LinkedList.interleaveOrNull(head1, null);
        assert linked2.getData() == 3;
    }

    public static void testInterleaveEdge() {
        Node head1 = LinkedList.append(null, 3);
        Node head2 = LinkedList.append(null, 1);
        LinkedList.append(head2, 2);

        Node linked = LinkedList.interleaveOrNull(head1, head2);

        int[] results = new int[] {3,1,2};
        Node node = linked;
        for (int i : results) {
            assert node.getData() == i;
            node = node.getNextOrNull();
        }
    }

    public static void testInvalidInterleave() {
        Node head2 = LinkedList.append(null, 1);
        LinkedList.append(head2, 2);
        LinkedList.append(head2, 3);
        LinkedList.append(head2, 4);
        LinkedList.append(head2, 5);
        LinkedList.append(head2, 6);
        LinkedList.append(head2, 7);
        LinkedList.append(head2, 8);
        LinkedList.append(head2, 9);

        Node linked = LinkedList.interleaveOrNull(null, head2);
        Node node = linked;
        int[] results = new int[]{1,2,3,4,5,6,7,8,9};
        for (int i : results) {
            assert i == node.getData();
            node = node.getNextOrNull();
        }

        Node linked2 = LinkedList.interleaveOrNull(head2, null);
        assert linked2 == head2;
    }

    public static void test4() {
        Node root = LinkedList.append(null, 10);
        root = LinkedList.append(root, 11);

        root = LinkedList.removeAt(root, 2);
        assert root.getNextOrNull().getNextOrNull() == null;
    }



}
