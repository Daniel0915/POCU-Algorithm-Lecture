package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class Queue {
    private Node rootNode;

    public void enqueue(final int data) {
        if (rootNode == null) {
            rootNode = new Node(data);
            return;
        }
        rootNode = LinkedList.append(rootNode, data);
    }

    public int peek() {
        return rootNode.getData();
    }

    public int dequeue() {
        int deleteValue = rootNode.getData();
        rootNode = rootNode.getNextOrNull();
        return deleteValue;
    }

    public int getSize() {
        int lastIndex = 0;
        Node lastNode = rootNode;
        while (lastNode.getNextOrNull() !=null) {
            lastNode = lastNode.getNextOrNull();
            lastIndex++;
        }
        return lastIndex + 1;
    }
}