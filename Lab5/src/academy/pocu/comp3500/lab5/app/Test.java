package academy.pocu.comp3500.lab5.app;

import static academy.pocu.comp3500.lab5.Bts.append;
import static academy.pocu.comp3500.lab5.Bts.getNodeOrNull;
import static academy.pocu.comp3500.lab5.Bts.insertRecursive;

import academy.pocu.comp3500.lab5.Bts;
import academy.pocu.comp3500.lab5.Node;

public class Test {

    public static void main(String[] args) {
        Node node = new Node(6);
//        append(node, 4);
//        append(node, 10);
//        append(node, 2);
//        append(node, 5);
//        append(node, 7);
//        append(node, 11);

//        System.out.println("getNodeOrNull(node, 4) = " + getNodeOrNull(node, 6).toString());

        insertRecursive(node, 4);
        insertRecursive(node, 10);
        insertRecursive(node, 2);
        insertRecursive(node, 5);


    }

}
