package toss;

import java.util.*;

public class Test_3 {
    public static void main(String[] args) {
//        List<String> inputList = new ArrayList<>(Arrays.asList(
//                "토스커피사일로&베이커리",
//                "토스커피사일로 베이커리",
//                "토스커피사일로 베이커",
//                "토스커피사일로 베이",
//                "토스커피사일로",
//                "비바리퍼블리카 식당",
//                "비바리퍼블리카식",
//                "비바리퍼블리"
//        ));

        List<String> inputList = new ArrayList<>(Arrays.asList(
                "토스사일로카페",
                "토스사일로카폐"
        ));

        List<String> inputListEmpty = new ArrayList<>();
        for (String input : inputList) {
            input = input.replaceAll("\\s", "");
            input = input.replaceAll("[&().,-]", "");

            inputListEmpty.add(input);
        }

        Trie trie = new Trie();

        for (String inputEmpty : inputListEmpty) {
            trie.insert(inputEmpty);
        }

        for (String inputEmpty : inputListEmpty) {
            System.out.println(inputEmpty);
            System.out.println(trie.isLastSearch(inputEmpty));
            System.out.println("=======================");
        }
    }

    static class Node {
        // 자식 노드
        Map<Character, Node> chiledNode = new HashMap<>();
        // 단어의 끝인지 아닌지 체크
        boolean endOfword;
    }

    static class Trie {

        // Trie자료구조를 생성할 떄 rootNode는 기본적으로 생성
        Node rootNode = new Node();

        // Trie에 문자열 저장
        void insert(String str) {
            // Trie자료구조는 항상 rootNode부터 시작
            Node node = this.rootNode;

            // 문자열의 각 단어마다 가져와서 자식노드 중에 있는지 체크
            // 없으면 자식노드 새로 생성
            for(int i=0; i<str.length(); i++) {
                node = node.chiledNode.computeIfAbsent(str.charAt(i), key -> new Node());
            }
            // 저장 할 문자열의 마지막 단어에 매핑되는 노드에 단어의 끝임을 명시
            node.endOfword = true;
        }

        // Trie에서 문자열 검색
        boolean search(String str) {
            // Trie자료구조는 항상 rootNode부터 시작
            Node node = this.rootNode;

            // 문자열의 각 단어마다 노드가 존재하는지 체크
            for(int i=0; i<str.length(); i++) {
                // 문자열의 각 단어에 매핑된 노드가 존재하면 가져오고 아니면 null
                node = node.chiledNode.getOrDefault(str.charAt(i), null);
                if(node == null) {
                    // node가 null이면 현재 Trie에 해당 문자열은 없음
                    return false;
                }
            }
            // 문자열의 마지막 단어까지 매핑된 노드가 존재한다고해서 무조건 문자열어 존재하는게 아님
            // busy를 Trie에 저장했으면, bus의 마지막 s단어에 매핑 된 노드는 존재하지만 Trie에 저장된건 아님
            // 그러므로 현재 노드가 단어의 끝인지 아닌지 체크하는 변수로 리턴
            return node.endOfword;
        }

        boolean isLastSearch(String str) {
            // Trie자료구조는 항상 rootNode부터 시작
            Node node = this.rootNode;

            // 문자열의 각 단어마다 노드가 존재하는지 체크
            for(int i=0; i<str.length(); i++) {
                // 문자열의 각 단어에 매핑된 노드가 존재하면 가져오고 아니면 null
                node = node.chiledNode.getOrDefault(str.charAt(i), null);
                if (node.chiledNode.isEmpty()) {
                    return true;
                }
            }
            return false;
        }
    }
}
