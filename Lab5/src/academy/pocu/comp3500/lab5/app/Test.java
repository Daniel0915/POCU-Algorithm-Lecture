package academy.pocu.comp3500.lab5.app;

import static academy.pocu.comp3500.lab5.BtsBinaryTree.deleteNode;
import static academy.pocu.comp3500.lab5.BtsBinaryTree.deleteNode2;
import static academy.pocu.comp3500.lab5.BtsBinaryTree.deleteNode3;
import static academy.pocu.comp3500.lab5.BtsBinaryTree.findParentNode;
import static academy.pocu.comp3500.lab5.BtsBinaryTree.findParentNodeOrNullRecursive;
import static academy.pocu.comp3500.lab5.BtsBinaryTree.insertRecursive;
import static academy.pocu.comp3500.lab5.BtsBinaryTree.traverseInOrder;
import static academy.pocu.comp3500.lab5.BtsBinaryTree.traverseInOrderRecursive;

import academy.pocu.comp3500.lab5.Node;

public class Test {

    public static void main(String[] args) {
        Node node = new Node(6);
        insertRecursive(node, 4);
        insertRecursive(node, 10);
        insertRecursive(node, 12);
        insertRecursive(node, 9);


        Node node2 = deleteNode2(node, 10);


    }

}
