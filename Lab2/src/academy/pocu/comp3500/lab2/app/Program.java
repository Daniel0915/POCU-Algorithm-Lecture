package academy.pocu.comp3500.lab2.app;

import academy.pocu.comp3500.lab2.LinkedList;
import academy.pocu.comp3500.lab2.Stack;
import academy.pocu.comp3500.lab2.Queue;
import academy.pocu.comp3500.lab2.datastructure.Node;

public class Program {

    public static void main(String[] args) {
        {
            Queue queue = new Queue();

            queue.enqueue(20); // queue: 20
            queue.enqueue(21); // queue: 20, 21
            queue.enqueue(22); // queue: 20, 21, 22
            queue.enqueue(23); // queue: 20, 21, 23
            queue.enqueue(24); // queue: 20, 21, 23, 24

            assert queue.getBack() == 5;
            assert queue.getFront() == 0;

            assert queue.dequeue() == 20;
            assert queue.peek() == 21;
            assert queue.getFront() == 1;
            assert queue.getBack() == 5;
            assert queue.getSize() == 4;

            assert queue.dequeue() == 21;
            assert queue.peek() == 22;
            assert queue.getFront() == 2;
            assert queue.getBack() == 5;
            assert queue.getSize() == 3;

            assert queue.dequeue() == 22;
            assert queue.peek() == 23;
            assert queue.getFront() == 3;
            assert queue.getBack() == 5;
            assert queue.getSize() == 2;

            assert queue.dequeue() == 23;
            assert queue.peek() == 24;
            assert queue.getFront() == 4;
            assert queue.getBack() == 5;
            assert queue.getSize() == 1;

            assert queue.dequeue() == 24;
            assert queue.getFront() == 5;
            assert queue.getBack() == 5;
            assert queue.getSize() == 0;

            assert queue.getSize() == 0;
            queue.enqueue(25);
            assert queue.peek() == 25;
            assert queue.getSize() == 1;


        }





        {
            testRemoveAt();
            testInsertAt();
            test4();
//            testInvalidInterleave();
            testInterleaveEdge();
            testInterleaveEdge2();
        }

        {
            Node root = LinkedList.append(null, 10);

            root = LinkedList.append(root, 11);
            root = LinkedList.append(root, 12);

            assert (root.getData() == 10);

            Node next = root.getNextOrNull();

            assert (next.getData() == 11);

            next = next.getNextOrNull();

            assert (next.getData() == 12);
        }

        {
            Node root = LinkedList.append(null, 10);

            root = LinkedList.prepend(root, 11);

            assert (root.getData() == 11);

            root = LinkedList.prepend(root, 12);

            assert (root.getData() == 12);

            Node next = root.getNextOrNull();

            assert (next.getData() == 11);

            next = next.getNextOrNull();

            assert (next.getData() == 10);
        }

        {
            Node root = LinkedList.append(null, 10);

            root = LinkedList.insertAt(root, 0, 11);

            assert (root.getData() == 11);

            root = LinkedList.insertAt(root, 1, 12);

            assert (root.getData() == 11);

            Node next = root.getNextOrNull();

            assert (next.getData() == 12);

            next = next.getNextOrNull();

            assert (next.getData() == 10);
        }

        {
            Node root = LinkedList.append(null, 10);

            root = LinkedList.append(root, 11);
            root = LinkedList.append(root, 12);
            root = LinkedList.append(root, 13);

            root = LinkedList.removeAt(root, 0);

            assert (root.getData() == 11);

            root = LinkedList.removeAt(root, 1);

            assert (root.getData() == 11);

            Node next = root.getNextOrNull();

            assert (next.getData() == 13);
        }

        {
            Node root = LinkedList.append(null, 10);

            root = LinkedList.append(root, 11);

            int index = LinkedList.getIndexOf(root, 10);

            assert (index == 0);

            index = LinkedList.getIndexOf(root, 11);

            assert (index == 1);
        }

        {
            Node root = LinkedList.append(null, 10);

            root = LinkedList.append(root, 11);

            Node node = LinkedList.getOrNull(root, 0);

            assert (node.getData() == 10);

            node = LinkedList.getOrNull(root, 1);

            assert (node.getData() == 11);
        }

        {
            Node root1 = LinkedList.append(null, 10);

            root1 = LinkedList.append(root1, 11);
            root1 = LinkedList.append(root1, 12);

            Node root2 = LinkedList.append(null, 13);

            root2 = LinkedList.append(root2, 14);
            root2 = LinkedList.append(root2, 15);

            Node newRoot = LinkedList.interleaveOrNull(root1, root2); // newRoot: 10, list: 10 -> 13 -> 11 -> 14 -> 12 -> 15

            assert (newRoot.getData() == 10);

            Node next = newRoot.getNextOrNull();

            assert (next.getData() == 13);

            next = next.getNextOrNull();

            assert (next.getData() == 11);

            next = next.getNextOrNull();

            assert (next.getData() == 14);

            next = next.getNextOrNull();

            assert (next.getData() == 12);

            next = next.getNextOrNull();

            assert (next.getData() == 15);
        }

        {
            Node root = LinkedList.append(null, 10);

            root = LinkedList.append(root, 11);
            root = LinkedList.append(root, 12);
            root = LinkedList.append(root, 13);
            root = LinkedList.append(root, 14);

            root = LinkedList.reverse(root); // root: 14, list: 14 -> 13 -> 12 -> 11 -> 10

            assert (root.getData() == 14);

            Node next = root.getNextOrNull();

            assert (next.getData() == 13);

            next = next.getNextOrNull();

            assert (next.getData() == 12);

            next = next.getNextOrNull();

            assert (next.getData() == 11);

            next = next.getNextOrNull();

            assert (next.getData() == 10);
        }

        {
            Stack stack = new Stack();

            stack.push(20);
            stack.push(21); // stack: 21
            //        20

            int data = stack.pop();

            assert (data == 21);

            data = stack.pop();

            assert (data == 20);
        }

        {
            Stack stack = new Stack();

            stack.push(20); // stack: 20

            assert (stack.peek() == 20);

            stack.push(21); // stack: 21
            //        20

            assert (stack.peek() == 21);
        }

        {
            Stack stack  = new Stack();

            stack.push(20);
            stack.push(21);

            assert (stack.getSize() == 2);
        }

        {
            Queue queue = new Queue();

            queue.enqueue(20);

            assert (queue.peek() == 20);

            queue.enqueue(21);

            assert (queue.peek() == 20);
        }

        {
            Queue queue = new Queue();

            queue.enqueue(20);
            queue.enqueue(21);

            int data = queue.dequeue();

            assert (data == 20);

            data = queue.dequeue();

            assert (data == 21);
        }

        {
            Queue queue = new Queue();

            queue.enqueue(20);
            queue.enqueue(21);

            assert (queue.getSize() == 2);
        }
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
