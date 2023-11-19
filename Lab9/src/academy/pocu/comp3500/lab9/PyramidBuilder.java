package academy.pocu.comp3500.lab9;

public class PyramidBuilder {
    public static int findMaxHeight(final int[] widths, int statue) {
        if (widths.length <= 1) {
            return 0;
        }

        quickSort(widths);

        if (widths[widths.length - 1] + widths[widths.length - 2] <= statue) {
            return 0;
        }

        int elementCount = widths.length;

        boolean isDone = false;
        int level = 1;

        while (!isDone) {
            elementCount -= level + 1;

            if (elementCount > level + 1) {
                level++;
            }

            if (elementCount <= level + 1) {
                isDone = true;
            }
        }

        return level;
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
        quickSortRecursive(arr, pivotPos + 1, right);
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

    private static int calculateLevels(int elementCount) {
        boolean isDone = false;
        int level = 1;

        while (!isDone) {
            elementCount -= level + 1;

            if (elementCount > level + 1) {
                level++;
            }

            if (elementCount <= level + 1) {
                isDone = true;
            }
        }
        return level;
    }
}
