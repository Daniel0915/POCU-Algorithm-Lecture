package academy.pocu.comp3500.lab5;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BtsBinaryTree {

    public static Node getNodeOrNull(final Node node, int data) {
        if (node == null) {
            return null;
        }

        if (node.data == data) {
            return node;
        }

        if (data < node.data) {
            return getNodeOrNull(node.left, data);
        }

        return getNodeOrNull(node.right, data);
    }

    public static Node insertRecursive(final Node node, int data) {
        if (node == null) {
            return new Node(data);
        }

        if (data < node.data) {
            node.left = insertRecursive(node.left, data);
        } else {
            node.right = insertRecursive(node.right, data);
        }

        return node;
    }


    public static int traversePreOrder(final Node root, int data) {
        if (root == null) {
            return -1;
        }

        if (root.data == data) {
            return -1;
        }

        Stack<Node> nodes = new Stack<>();

        nodes.push(root);

        while (!nodes.empty()) {
            Node node = nodes.pop();

            if (node.right != null) {
                if (node.right.data == data) {
                    return node.data;
                }
                nodes.push(node.right);
            }

            if (node.left != null) {
                if (node.left.data == data) {
                    return node.data;
                }
                nodes.push(node.left);
            }
        }

        return -1;
    }

    public static List<Integer> traverseInOrder(final Node root, int data) {
        if (root == null) {
            return null;
        }

        Stack<Node> nodes = new Stack<>();

        Node current = root;

        boolean isTargetData = false;

        List<Integer> dataList = new ArrayList<>();

        while (!nodes.empty() || current != null) {
            if (current != null) {
                nodes.push(current);
                current = current.left;
            } else {
                current = nodes.pop();

                dataList.add(current.data);
                if (isTargetData) {
                    break;
                }
                if (current.data == data) {
                    isTargetData = true;
                }

                current = current.right;
            }
        }

        return dataList;
    }

    public static void test(final Node node) {
        if (node == null) {
            return;
        }

        test(node.right);
        System.out.println(node.data);
        test(node.left);
    }

    public static void traverseInOrderRecursive(Node node) {
        if (node == null) {
            return;
        }

        traverseInOrderRecursive(node.left);
        System.out.println(node.data);
        traverseInOrderRecursive(node.right);
    }

    public static void traverseInOrder(Node node) {
        if (node == null) {
            return;
        }

        Node current = node;

        Stack<Node> nodes = new Stack<>();

        while (!nodes.empty() || current != null) {
            if (current != null) {
                nodes.push(current);
                current = current.left;
            } else {
                current = nodes.pop();
                System.out.println(current.data);
                current = current.right;
            }
        }
    }

    public static void traverseInOrderDesc(Node node) {
        if (node == null) {
            return;
        }

        Node current = node;

        Stack<Node> nodes = new Stack<>();

        while (!nodes.empty() || current != null) {
            if (current != null) {
                nodes.push(current);
                current = current.right;
            } else {
                current = nodes.pop();
                System.out.println(current.data);
                current = current.left;
            }
        }
    }

    public static Node findInOrderPredecessorOrNull(final Node node) {
        if (node.left == null) {
            return null;
        }

        Node n = node.left;

        while (n.right != null) {
            n = n.right;
        }

        return n;
    }

    public static Node findInOrderPredecessorOrNullRecursive(final Node node) {
        if (node.left == null) {
            return null;
        }

        return findInOrderPredecessorOrNullRecursiveTail(node.left);
    }

    public static Node findInOrderPredecessorOrNullRecursiveTail(final Node node) {
        if (node.right == null) {
            return node;
        }

        return findInOrderPredecessorOrNullRecursiveTail(node.right);
    }

    public static Node findInOrderSuccessorOrNull(final Node node) {
        if (node.right == null) {
            return null;
        }

        Node n = node.right;

        while (n.left != null) {
            n = n.left;
        }

        return n;
    }

    public static Node findInOrderSuccessorOrNullRecursive(final Node node) {
        if (node.right == null) {
            return null;
        }

        return findInOrderSuccessorOrNullRecursiveTail(node.right);
    }

    public static Node findInOrderSuccessorOrNullRecursiveTail(final Node node) {
        if (node.left == null) {
            return node;
        }

        return findInOrderSuccessorOrNullRecursiveTail(node.left);
    }

    public static Node findParentNodeOrNullRecursive(final Node root, int childData) {
        if (root == null) {
            return null;
        }

        // childData is root date case
        if (root.data == childData) {
            return null;
        }

        if (root.left != null) {
            if (root.left.data == childData) {
                return root;
            }
        }

        if (root.right != null) {
            if (root.right.data == childData) {
                return root;
            }
        }

        if (childData < root.data) {
            return findParentNodeOrNullRecursive(root.left, childData);
        }
        return findParentNodeOrNullRecursive(root.right, childData);
    }

    public static Node deleteNode(Node root, final int key) {
        // 현재 노드의 부모를 저장할 포인터
        Node parent = null;

        // 루트 노드로 시작
        Node curr = root;

        // 아래 while은 key 의 노드와 부모 노드를 동시에 찾을 수 있음.
        // bst 에서 키를 검색하고 부모 포인터를 설정합니다.
        while (curr != null && curr.data != key) {
            // 부모를 현재 노드로 업데이트
            parent = curr;

            // 주어진 키가 현재 노드보다 작으면 왼쪽 하위 트리로 이동합니다.
            // 그렇지 않으면 오른쪽 하위 트리로 이동
            if (key < curr.data) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        // Case 1: 삭제할 노드에 자식이 없는 경우, 즉 리프 노드
        if (curr.left == null && curr.right == null) {
            // 삭제할 노드가 루트 노드가 아닌 경우 해당 노드를 설정합니다.
            // 부모 왼쪽/오른쪽 자식을 null로
            if (curr != root) {
                if (parent.left == curr) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
            // 트리에 루트 노드만 있는 경우 null로 설정합니다.
            else {
                root = null;
            }
        }
        // 사례 2: 삭제할 노드에 두 개의 자식이 있는 경우
        else if (curr.left != null && curr.right != null) {
            // 중위 후속 노드 를 찾습니다.
            Node successor = findInOrderSuccessorOrNull(curr);

            // 후속 값 저장
            int val = successor.data;

            // 후계자를 재귀적으로적으로 삭제합니다. 참고로 후계자
            // 최대 하나의 자식(오른쪽 자식)을 가집니다.
            deleteNode(root, successor.data);

            // 후속 노드의 값을 현재 노드에 복사
            curr.data = val;
        }
        // Case 3: 삭제할 노드에 자식이 하나만 있는 경
        else {
            // 자식 노드 선택
            Node child = (curr.left != null) ? curr.left : curr.right;

            // 삭제하고자 하는 노드가 루트노드가 아니면 부모 노드로 설정
            // 자식에게
            if (curr != root) {
                if (curr == parent.left) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            }
            // 삭제할 노드가 루트 노드이면 루트를 자식 노드로 설정
            else {
                root = child;
            }
        }
        return root;
    }

    public static Node deleteNode2(Node root, int data) {
        if (root == null) {
            return root;
        }

        // Recursive calls for ancestors of
        // node to be deleted
        if (root.data > data) {
            root.left = deleteNode2(root.left, data);
            return root;
        } else if (root.data < data) {
            root.right = deleteNode2(root.right, data);
            return root;
        }

        // We reach here when root is the node
        // to be deleted.

        // If one of the children is empty
        if (root.left == null) {
            Node temp = root.right;
            return temp;
        } else if (root.right == null) {
            Node temp = root.left;
            return temp;
        }

        // If both children exist
        else {
            Node succParent = root;

            // Find successor
            Node succ = root.right;
            while (succ.left != null) {
                succParent = succ;
                succ = succ.left;
            }

            // Delete successor.  Since successor
            // is always left child of its parent
            // we can safely make successor's right
            // right child as left of its parent.
            // If there is no succ, then assign
            // succ.right to succParent.right
            if (succParent != root)
                succParent.left = succ.right;
            else {
                succParent.right = succ.right;
            }

            // Copy Successor Data to root
            root.data = succ.data;

            // Delete Successor and return root
            return root;
        }


    }

    public static Node findParentNode(Node root, final int key) {
        // 현재 노드의 부모를 저장할 포인터
        Node parent = null;

        // 루트 노드로 시작
        Node curr = root;

        // 아래 while은 key 의 노드와 부모 노드를 동시에 찾을 수 있음.
        // bst 에서 키를 검색하고 부모 포인터를 설정합니다.
        while (curr != null && curr.data != key) {
            // 부모를 현재 노드로 업데이트
            parent = curr;

            // 주어진 키가 현재 노드보다 작으면 왼쪽 하위 트리로 이동합니다.
            // 그렇지 않으면 오른쪽 하위 트리로 이동
            if (key < curr.data) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return parent;
    }

    public static Node deleteNode3(Node root, final int key) {
        Node parent = findParentNode(root, key);

        if (parent == null) {
            if (parent.left != null) {
                parent = parent.left;
                return parent;
            }

            if (parent.right != null) {
                parent = parent.right;
                return parent;
            }
            return null;
        }

        Node children = getNodeOrNull(parent, key);

        if (children == null) {
            return root;
        }


        // 삭제 대상이 leaf 일 경우
        if (children.left == null && children.right == null) {
            if (parent.left.data == children.data) {
                parent.left = null;
                return parent;
            }

            if (parent.right.data == children.data) {
                parent.right = null;
                return parent;
            }
        }

        Node succ = findInOrderSuccessorOrNull(children);

        // 삭재 대상의 succ 노드가 leaf 일 경우
        if (succ.left == null && succ.right == null) {
            children.data = succ.data;
        }


        return getNodeOrNull(parent, key);
    }
}
