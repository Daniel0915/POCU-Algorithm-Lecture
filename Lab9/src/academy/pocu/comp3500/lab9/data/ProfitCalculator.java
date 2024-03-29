package academy.pocu.comp3500.lab9.data;


public class ProfitCalculator {
    public static int findMaxProfit(final Task[] tasks, final int[] skillLevels) {
        quickSort(tasks);

        int maxProfit = 0;

        for (int skill : skillLevels) {
            for (Task task : tasks) {
                if (skill >= task.getDifficulty()) {
                    maxProfit += task.getProfit();
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
