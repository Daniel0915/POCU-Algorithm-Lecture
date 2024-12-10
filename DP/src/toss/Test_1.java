package toss;

import java.util.*;
import java.util.stream.Collectors;

public class Test_1 {
    public static void main(String[] args) {
        String s = "12412415";
        int N = 4;

        int max = Integer.MIN_VALUE;

        int[] intArray = new int[s.length()];

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < s.length(); j++) {
                int target = Integer.parseInt(String.valueOf(s.charAt(j)));
                if (i == target) {
                    intArray[j] = i;
                }
            }
        }

        for (int i = 0; i < intArray.length - N; i++) {
            List<String> temp = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                if (intArray[i + j] != 0) {
                    temp.add(String.valueOf(intArray[i + j]));
                }
            }

            if (temp.size() == N && temp.stream().collect(Collectors.toSet()).size() == temp.size()) {
                max = Math.max(Integer.parseInt(temp.stream().collect(Collectors.joining())), max);
            }
        }

        if (max == Integer.MIN_VALUE) {
            System.out.println("-1");
        } else {
            System.out.println(max);
        }


//        System.out.println(result);
    }
}
