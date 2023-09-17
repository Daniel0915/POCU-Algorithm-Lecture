package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class Stack {
    private Node rootNode;
    public void push(final int data) {

        if (rootNode == null) {
            rootNode = new Node(data);
            return;
        }
        rootNode = LinkedList.append(rootNode, data);
    }

    public int peek() {
        // Node 가 1개일 경우
        if (rootNode.getNextOrNull() == null) {
            return rootNode.getData();
        }

        Node topNode = rootNode;
        while (topNode.getNextOrNull() != null) {
            topNode = topNode.getNextOrNull();
        }

        return topNode.getData();
    }

    public int pop() {
        int topData;
        if (rootNode.getNextOrNull() == null) {
            topData = rootNode.getData();
            rootNode = null;
            return topData;
        }

        // top Node 전 노드
        Node preTopNode = rootNode;
        while (preTopNode.getNextOrNull() != null) {
            // 현재 노드가 마지막 노드 전일 때 break
            if (preTopNode.getNextOrNull().getNextOrNull() == null) {
                break;
            }

            preTopNode = preTopNode.getNextOrNull();
        }

        topData = preTopNode.getNextOrNull().getData();
        preTopNode.setNext(null);

        return topData;
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