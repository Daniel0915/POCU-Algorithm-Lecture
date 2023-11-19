package academy.pocu.comp3500.lab9.data;


public class ProfitCalculator {
    public static int findMaxProfit(final Task[] tasks, final int[] skillLevels) {
        if (skillLevels.length == 0 || tasks.length == 0) {
            return 0;
        }

        int maxProfit = 0;

        // 1. 수익 기준 내림차순 정렬
        quickSort(tasks);
        // 2-1. skillLevel >= task 의 난이도 이면 수익 추가. 아니면 다음 task
        for (int i = 0; i < skillLevels.length; ++i) {
            for (int j = 0; j < tasks.length; ++j) {
                if (skillLevels[i] >= tasks[j].getDifficulty()) {
                    maxProfit += tasks[j].getProfit();
                    break;
                }
            }
        }

        return maxProfit;
    }

    private static void quickSort(Task[] tasks) {
        quickSortRecursive(tasks, 0, tasks.length - 1);
    }

    private static void quickSortRecursive(Task[] tasks, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotPos = partition(tasks, left, right);

        quickSortRecursive(tasks, left, pivotPos - 1);
        quickSortRecursive(tasks, pivotPos + 1, right);
    }

    private static int partition(Task[] tasks, int left, int right) {
        int pivot = tasks[right].getProfit();
        int i = (left - 1);

        for (int j = left; j < right; ++j) {
            if (tasks[j].getProfit() > pivot) {
                ++i;
                swap(tasks, i, j);
            }
        }

        int pivotPos = i + 1;

        swap(tasks, pivotPos, right);

        return pivotPos;
    }

    private static void swap(Task[] tasks, int pos1, int pos2) {
        Task temp = tasks[pos1];
        tasks[pos1] = tasks[pos2];
        tasks[pos2] = temp;
    }


}
