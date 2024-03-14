package academy.pocu.comp3500.lab9_pra.data;


public class ProfitCalculator {
    public static int findMaxProfit(final Task[] tasks, final int[] skillLevels) {

        // 각 직원은 일감 하나만을 완료할 수 있지만 그 직원의 실력 수준이 최소 일감의 난이도만큼은 되어야 그 일을 할 수 있습니다.

        // 한 일감은 여러 직원에게 할당될 수 있고, 이럴 경우 그 일은 여러 번 완료되게 됩니다.
        return 0;
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
