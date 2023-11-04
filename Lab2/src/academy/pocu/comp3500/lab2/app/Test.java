package academy.pocu.comp3500.lab2.app;

import academy.pocu.comp3500.lab2.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Test {

    public static void main(String[] args) {
        int[] array = {3, 4, 5, 6, 1, 2};
        System.out.println(findK(array));


    }

    public static int foo(int num) {
        return fooRecursive(num, num);
    }

    public static int fooRecursive(int num, int num2) {

        if (num < 5) {
            int sum = 0;
            for (int i = num2; i > 5; i--) {
                sum += i;
            }

            return 6 - sum;
        }

        return fooRecursive(num - 1, num2);
    }

    public static int foo2(int num) {
        if (num < 5) {
            return 10 - num;
        }
        return foo2(num - 1) - num;
    }

    public static int bar(String str) {
        int length = str.length();

        if (length <= 1) {
            return length;
        }

        String subStr = str.substring(1);

        return length + bar(subStr);
    }

    public static int bar2(String str) {
        return bar2(str, 0);
    }

    public static int bar2(String str, int sum) {
        int length = str.length();

        if (length <= 1) {
            return length + sum;
        }

        String subStr = str.substring(1);

        return bar2(subStr, length + sum);
    }

    public static int findK(int[] arr) {
        int s = 0;
        int e = arr.length - 1;

        if (arr[s] < arr[e]) {
            return arr[s + 1];
        }

        int mid;
        while (true) {
            mid = (s + e) / 2;

            if (e - s == 1) {
                return arr[(e + 1) % arr.length];
            }

            if (arr[s] > arr[mid]) {
                e = mid;
            } else if (arr[s] < arr[mid]) {
                s = mid;
            }
        }
    }





}
