package academy.pocu.comp3500.lab2.app;

import academy.pocu.comp3500.lab2.LinkedList;
import academy.pocu.comp3500.lab2.Stack;
import academy.pocu.comp3500.lab2.Queue;
import academy.pocu.comp3500.lab2.datastructure.Node;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Program {

    public static void main(String[] args) {

        // Create a list
        List<Domain> l = new ArrayList<Domain>();
        l.add(new Domain(10, "www.geeksforgeeks.org"));
        l.add(new Domain(20, "practice.geeksforgeeks.org"));
        l.add(new Domain(30, "code.geeksforgeeks.org"));
        l.add(new Domain(40, "www.geeksforgeeks.org"));

        Domain target = new Domain(50, "practice.geeksforgeeks.org");

        int index = Collections.binarySearch(l, target, Comparator.comparing(Domain::getId));
        Optional<Domain> findDomain = Optional.ofNullable(index >= 0 ? l.get(index) : null);



    }



}
