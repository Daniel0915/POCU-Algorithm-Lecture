package academy.pocu.comp3500.lab9_pra;

public class PyramidBuilder {
    public static int findMaxHeight(final int[] widths, int statue) {
        if (widths.length <= 1) {
            return 0;
        }

        quickSort(widths);

        int topBlockCnt = 2;
        int floor = 0;


        // 최상 높이의 블럭들의 합이 동상 너비를 초과하는지 검사
        while (true) {
            if (topBlockCnt > widths.length) {
                return floor;
            }


            int topBlockWidthsSum = 0;
            for (int index = 0; index < topBlockCnt; index++) {
                topBlockWidthsSum += widths[index];
            }

            if (topBlockWidthsSum > statue) {
                floor++;
                break;
            }

            topBlockCnt++;
        }


        // i 번째 블럭 수 > i+1 번째 블럭 수 높을 경우의 조건에 맞게 하되
        // 현재 남은 블럭 > 다음에 쌓아야할 블럭 수 기준보다 많으면 1층 추가
        // 그렇지 않으면 층수를 추가하지 않음.
        int remain = widths.length - topBlockCnt;

        while (true) {
            topBlockCnt++;
            if (remain < topBlockCnt) {
                break;
            }
            remain -= topBlockCnt;
            floor++;
        }
        return floor;
    }

    // 오름차순
    public static void quickSort(int[] arr) {
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
}
