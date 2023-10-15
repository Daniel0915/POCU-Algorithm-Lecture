package academy.pocu.comp3500.assignment2;

import academy.pocu.comp3500.assignment2.datastructure.ArrayList;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Objects;

public final class Logger {
    private static ArrayList<LogContent> logs = new ArrayList<>();
    private static String INDENT_STRING = "  ";
    private static Indent indent;

    public static void log(final String text) {
        int indentCount = indent != null ? indent.getIndentCount() : 0;
        String indent = INDENT_STRING.repeat(indentCount);
        logs.add(new LogContent(indent + text, indentCount));
    }

    public static void printTo(final BufferedWriter writer) {
        try {
            for (LogContent log : logs) {
                writer.write(log.getContent());
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printTo(final BufferedWriter writer, final String filter) {
        try {

            // 마지막 라인(배열) 요소 중 filter 대상이 있는지 확인하여 있으면 log 기록
            if (isFilter(logs.get(logs.getSize() - 1), filter)) {
                for (int i = 0; i < logs.getSize(); i++) {
                    if (i == 0) {
                        writer.write(logs.get(i).getContent());
                        writer.newLine();
                        writer.flush();
                        continue;
                    }

                    if (isFilter(logs.get(i), filter)) {
                        writer.write(logs.get(i).getContent());
                        writer.newLine();
                        writer.flush();
                    }
                }
            }

            // 없으면 log 기록 안함
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void clear() {
        logs.clear();
    }

    public static Indent indent() {
        if (indent != null) {
            indent.addIndentCount();
            return indent;
        }
        indent = new Indent();
        indent.addIndentCount();
        indent.setLogs(logs);
        return indent;
    }

    public static void unindent() {
        indent.minusIndentCount();
    }

    private static int[] getArray(String content, boolean isLR) {
        if (content == null || Objects.equals(content, "")) {
            return null;
        }
        String[] numsString = content.split(" ");
        int[] nums = new int[numsString.length];

        for (int i = 0; i < numsString.length; i++) {
            nums[i] = Integer.parseInt(numsString[i]);
        }

        if (isLR) {
            quickSort(nums);
        }

        return nums;
    }

    private static boolean isFilter(LogContent logContent, String filter) {
        String content = logContent.getContent();
        int indentCount = logContent.getIndentCount() * 2;
        try {
            // R: 90 50 70 80
            content = content.substring(indentCount);
        } catch (Exception e) {
            return false;
        }

        char side = content.charAt(0);

        // ex : 'L: '
        int fixTextLength = 3;
        try {
            content = content.substring(fixTextLength);
        } catch (Exception e) {
            return false;
        }

        if (Objects.equals("", content) || Objects.equals(null, content)) {
            return false;
        }

        boolean isLR = false;
        if (side != 'X') {
            isLR = true;
        }

        int[] nums = getArray(content, isLR);

        if (binarySearchRecursive(nums, Integer.parseInt(filter), 0, nums.length - 1) == -1) {
            return false;
        }
        return true;
    }

    private static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            }

            if (arr[mid] > target) {
                return binarySearchRecursive(arr, target, left, mid - 1);
            } else {
                return binarySearchRecursive(arr, target, mid + 1, right);
            }
        }

        return -1;
    }

    private static void quickSort(int[] arr) {
        quickSortRecursive(arr, 0, arr.length -1);
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