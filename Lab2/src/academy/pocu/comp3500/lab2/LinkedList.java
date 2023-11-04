package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class LinkedList {

    public static Node append(final Node rootOrNull, final int data) {
        Node newNode = new Node(data);

        if (rootOrNull == null) {
            return newNode;
        }

        Node node = rootOrNull;

        // 노드의 next 가 null 아니면 있다면 다음 노드로 이동
        // 가장 마지막 노드는 next 가 null 을 가짐으로, 가장 마지막 노드를 찾으면 while 문 out
        while (node.getNextOrNull() != null) {
            node = node.getNextOrNull();
        }

        // 가장 마지막 노드에 새로 생성된 노드의 주소를 setter
        node.setNext(newNode);

        return rootOrNull;
    }

    public static Node prepend(final Node rootOrNull, final int data) {
        Node newNode = new Node(data);

        if (rootOrNull == null) {
            return newNode;
        }


        newNode.setNext(rootOrNull);

        return newNode;
    }

    public static Node insertAt(final Node rootOrNull, final int index, final int data) {

        // head Node 에 넣는 것으로 판단
        if (index == 0) {
            return LinkedList.prepend(rootOrNull, data);
        }

        if (index < 0 || rootOrNull == null) {
            return rootOrNull;
        }

        int nodeIndex = 0;
        Node preNode = rootOrNull;

        while (nodeIndex != index) {
            if (nodeIndex == (index - 1)) {

                if (preNode == null) {
                    throw new IllegalArgumentException("Invalid index");
                }

                Node newNode = new Node(data);
                // 이전 노드
                newNode.setNext(preNode.getNextOrNull());
                preNode.setNext(newNode);
                break;
            }
            preNode = preNode.getNextOrNull();
            nodeIndex++;
        }

        return rootOrNull;

    }

    public static Node removeAt(final Node rootOrNull, final int index) {
        if (rootOrNull == null) {
            return null;
        }

        int lastNodeIndex = getNodeLastIndex(rootOrNull);

        // 삭제 index 가 현재 Node의 index 범위에 벗어나면, null 반환
        if (lastNodeIndex < index || index < 0) {
            return rootOrNull;
        }


        // 첫번째 노드 삭제
        if (index == 0) {
            Node rootNode = rootOrNull.getNextOrNull();
            return rootNode;
        }

        // 마지막 노드 삭제
        if (index == lastNodeIndex) {
            // 마지막 노드 전 노드
            Node preNode = rootOrNull;
            for (int i = 0; i < lastNodeIndex - 1; i++) {
                preNode = preNode.getNextOrNull();
            }

            // 마지막 노드
            preNode.setNext(null);
            return rootOrNull;
        }

        // 중간 노드 삭제
        // 삭제 대상 전 노드
        Node preNode = rootOrNull;
        for (int i = 0; i < index - 1; i++) {
            preNode = preNode.getNextOrNull();
        }

        preNode.setNext(preNode.getNextOrNull().getNextOrNull());

        return rootOrNull;
    }

    public static int getIndexOf(final Node rootOrNull, final int data) {
        Node findNode = rootOrNull;
        int findNodeIndex = 0;

        // Node 가 1개 일 경우
        if (findNode.getNextOrNull() == null) {
            if (findNode.getData() == data) {
                return findNodeIndex;
            } else {
                return -1;
            }
        }

        while (findNode.getNextOrNull() != null) {
            if (findNode.getData() == data) {
                return findNodeIndex;
            }
            findNode = findNode.getNextOrNull();
            findNodeIndex++;
            // 마지막 노드
            if (findNode.getNextOrNull() == null) {
                Node lastNode = findNode;
                if (lastNode.getData() == data) {
                    return findNodeIndex;
                }
            }
        }
        return -1;
    }

    public static Node getOrNull(final Node rootOrNull, final int index) {
        // 유효하지 않은 입력값
        if (rootOrNull == null || index < 0) {
            return null;
        }


        Node findNode = rootOrNull;
        int findNodeIndex = 0;

        // Node 가 1개 일 경우
        if (findNode.getNextOrNull() == null && index == 0) {
            return rootOrNull;
        }

        // Node 가 2개 이상일 경우
        while (findNode.getNextOrNull() != null) {
            if (findNodeIndex == index) {
                return findNode;
            }
            findNode = findNode.getNextOrNull();
            findNodeIndex++;

            // 마지막 노드
            if (findNode.getNextOrNull() == null) {
                if (findNodeIndex == index) {
                    return findNode;
                }
            }
        }

        return null;
    }

    public static Node reverse(final Node rootOrNull) {
        int lastNodeIndex = getNodeLastIndex(rootOrNull);

        Node reverseNode = append(null, getOrNull(rootOrNull, lastNodeIndex).getData());

        for (int i = lastNodeIndex - 1; i >= 0; i--) {
            reverseNode = append(reverseNode, getOrNull(rootOrNull, i).getData());
        }

        return reverseNode;
    }

    public static Node interleaveOrNull(final Node root0OrNull, final Node root1OrNull) {
        Node root00LastNode = root0OrNull;
        int root00LastIndex = 0;
        if (root0OrNull != null) {
            while (root00LastNode.getNextOrNull() != null) {
                root00LastNode = root00LastNode.getNextOrNull();
                root00LastIndex++;
            }
        }


        Node root10LastNode = root1OrNull;
        int root10LastIndex = 0;
        if (root1OrNull != null) {
            while (root10LastNode.getNextOrNull() != null) {
                root10LastNode = root10LastNode.getNextOrNull();
                root10LastIndex++;
            }
        }

        int maxIndex = Math.max(root00LastIndex, root10LastIndex);

        Node interleaveNode = null;

        for (int i = 0; i < maxIndex + 1; i++) {
            if (i == 0) {
                if (getOrNull(root0OrNull, i) != null) {
                    interleaveNode = new Node(getOrNull(root0OrNull, i).getData());
                }

                if (getOrNull(root1OrNull, i) != null) {
                    interleaveNode = append(interleaveNode, getOrNull(root1OrNull, i).getData());
                }
            } else {
                if (getOrNull(root0OrNull, i) != null) {
                    interleaveNode = append(interleaveNode, getOrNull(root0OrNull, i).getData());
                }

                if (getOrNull(root1OrNull, i) != null) {
                    interleaveNode = append(interleaveNode, getOrNull(root1OrNull, i).getData());
                }
            }
        }

        return interleaveNode;
    }

    private static int getNodeLastIndex(Node node) {
        if (node == null) {
            return 0;
        }

        int lastIndex = 0;
        Node nextNode = node;
        while (nextNode.getNextOrNull() != null) {
            nextNode = nextNode.getNextOrNull();
            lastIndex++;
        }
        return lastIndex;
    }
}