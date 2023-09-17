package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class LinkedList {
    private static int lastNodeIndex;

    public static Node append(final Node rootOrNull, final int data) {
        if (rootOrNull == null) {
            Node head = new Node(data);
            // 초기화
            lastNodeIndex = 0;
            return head;
        }

        Node newNode = new Node(data);

        Node node = rootOrNull;

        // 노드의 next 가 null 아니면 있다면 다음 노드로 이동
        // 가장 마지막 노드는 next 가 null 을 가짐으로, 가장 마지막 노드를 찾으면 while 문 out
        while (rootOrNull.getNextOrNull() != null) {
            node = rootOrNull.getNextOrNull();
        }

        // 가장 마지막 노드에 새로 생성된 노드의 주소를 setter
        node.setNext(newNode);
        lastNodeIndex++;

        return rootOrNull;
    }

    public static Node prepend(final Node rootOrNull, final int data) {
        Node newNode = new Node(data);

        newNode.setNext(rootOrNull);
        lastNodeIndex++;

        return newNode;
    }

    public static Node insertAt(final Node rootOrNull, final int index, final int data) {
        if (index < 0) {
            return rootOrNull;
        }

        // index가 유효한 범위 밖이라면 아무 일도 일어나지 않습니다.
        if (lastNodeIndex < index - 1) {
            return rootOrNull;
        }

        // 첫번째 index 에 넣을 때
        if (index == 0) {
            Node newNode = new Node(data);
            newNode.setNext(rootOrNull);
            lastNodeIndex++;
            return newNode;
        }

        // insert 해야하는 노드 앞
        Node preNode = rootOrNull;
        for (int i = 0; i < index - 1; i++) {
            preNode = preNode.getNextOrNull();
        }

        Node newNode = new Node(data);
        newNode.setNext(preNode.getNextOrNull());
        preNode.setNext(newNode);
        lastNodeIndex++;

        return rootOrNull;

    }

    public static Node removeAt(final Node rootOrNull, final int index) {
        if (rootOrNull == null) {
            return null;
        }

        // 삭제 index 가 현재 Node의 index 범위에 벗어나면, null 반환
        if (lastNodeIndex < index || index < 0) {
            return rootOrNull;
        }


        // 첫번째 노드 삭제
        if (index == 0) {
            Node rootNode = rootOrNull.getNextOrNull();
            lastNodeIndex--;
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
            lastNodeIndex--;
            return rootOrNull;
        }

        // 중간 노드 삭제
        // 삭제 대상 전 노드
        Node preNode = rootOrNull;
        for (int i = 0; i < index - 1; i++) {
            preNode = preNode.getNextOrNull();
        }

        preNode.setNext(preNode.getNextOrNull().getNextOrNull());
        lastNodeIndex--;

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
        int nowLastNodeIndex = lastNodeIndex;

        Node reverseNode = append(null, getOrNull(rootOrNull, nowLastNodeIndex).getData());

        for (int i = nowLastNodeIndex - 1; i >= 0; i--) {
            reverseNode = append(reverseNode, getOrNull(rootOrNull, i).getData());
        }

        return reverseNode;
    }

    public static Node interleaveOrNull(final Node root0OrNull, final Node root1OrNull) {
        Node root00LastNode = root0OrNull;
        int root00LastIndex = 0;
        while (root00LastNode.getNextOrNull() != null) {
            root00LastNode = root00LastNode.getNextOrNull();
            root00LastIndex++;
        }


        Node root10LastNode = root1OrNull;
        int root10LastIndex = 0;
        while (root10LastNode.getNextOrNull() != null) {
            root10LastNode = root10LastNode.getNextOrNull();
            root10LastIndex++;
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
}