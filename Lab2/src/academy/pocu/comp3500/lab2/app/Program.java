package academy.pocu.comp3500.lab2.app;

import academy.pocu.comp3500.lab2.LinkedList;
import academy.pocu.comp3500.lab2.Stack;
import academy.pocu.comp3500.lab2.Queue;
import academy.pocu.comp3500.lab2.datastructure.Node;

public class Program {

    public static void main(String[] args) {
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

}
