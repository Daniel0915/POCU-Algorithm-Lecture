package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class Queue {
    private Node rootNode;
    private int front = 0;
    private int back = 0;

    public void enqueue(final int data) {
        if (rootNode == null) {
            rootNode = new Node(data);
            back++;
            return;
        }
        rootNode = LinkedList.append(rootNode, data);
        back++;
    }

    public int peek() {
        return rootNode.getData();
    }

    public int dequeue() {
        if (front == back) {
            System.out.println("queue 가 비어 있습니다.");
        }
        int deleteValue = rootNode.getData();
        rootNode = rootNode.getNextOrNull();
        front++;
        return deleteValue;
    }

    public int getSize() {
        return back - front;
    }

    public int getFront() {
        return front;
    }

    public int getBack() {
        return back;
    }
}