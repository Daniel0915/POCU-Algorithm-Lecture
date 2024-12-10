package bruteForce;

import java.util.*;

public class Test {
    public int[] solution(int[] answers) {
        int[] first = {1,2,3,4,5}; // 5개씩 반복
        int[] second = {2,1,2,3,2,4,2,5}; // 8개씩 반복
        int[] third = {3,3,1,1,2,2,4,4,5,5}; // 10개씩 반복
        int[] score = {0,0,0}; // 각 수포자들의 점수

        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == first[i] % first.length) {
                score[0]++;
            }

            if (answers[i] == score[i] % second.length) {
                score[1]++;
            }

            if (answers[i] == third[i] % second.length) {
                score[2]++;
            }
        }

        // 최대 점수 구하기
        int max = Math.max(score[0], Math.max(score[1], score[2]));


        List<Integer> answ = new ArrayList<>();
        for (int i = 0; i < score.length; i++) {
            if (max == score[i]) {
                answ.add(i++);
            }
        }

        int[] answer = new int[answ.size()];
        for (Integer i : answ) {
            answer[i] = answ.get(i);
        }


        return answer;
    }

    private static void quickSort(int[] arr) {
        quickSortRecursive(arr, 0, arr.length - 1);
    }

    private static void quickSortRecursive(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotPos = partition(arr, left, right);

        quickSortRecursive(arr, left, pivotPos - 1);
        quickSortRecursive(arr, pivotPos + 1 , right);
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = (left - 1);

        for (int j = left; j < right; ++j) {
            if (arr[j] < pivot) {
                ++i;
                swap(arr, i, j);
            }
        }

        int pivotPos = i + 1;

        swap(arr, pivotPos, right);

        return pivotPos;
    }

    private static void swap(int[] arr, int pos1, int pos2) {
        int temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }
}
