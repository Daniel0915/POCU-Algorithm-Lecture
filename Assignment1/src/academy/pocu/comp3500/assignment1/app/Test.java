package academy.pocu.comp3500.assignment1.app;

import academy.pocu.comp3500.assignment1.pba.Player;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        generateSubsets(nums, 0, new int[nums.length], 0);
    }

    private static void generateSubsets(int[] nums, int currentIndex, int[] currentSubset, int currentSize) {
        if (currentIndex == nums.length) {
            // 현재 부분 집합 출력
            for (int i = 0; i < currentSize; i++) {
                System.out.print(currentSubset[i] + " ");
            }
            System.out.println();
            return;
        }

        // 현재 요소를 부분 집합에 포함하지 않는 경우
        generateSubsets(nums, currentIndex + 1, currentSubset, currentSize);

        // 현재 요소를 부분 집합에 포함하는 경우
        currentSubset[currentSize] = nums[currentIndex];
        generateSubsets(nums, currentIndex + 1, currentSubset, currentSize + 1);
    }
}
