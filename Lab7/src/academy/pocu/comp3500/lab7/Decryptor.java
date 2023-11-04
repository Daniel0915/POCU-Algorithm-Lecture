package academy.pocu.comp3500.lab7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Decryptor {
    private HashMap<String, String> codeWords = new HashMap<>();
    // codeWords : 후보 부호어, 대문소문자를 구분하지 않음.
    public Decryptor(final String[] codeWords) {
        for (int index = 0; index < codeWords.length; index++) {
            String lowerCase = codeWords[index].toLowerCase();
            String key = lowerCase;

            char[] arr = lowerCase.toCharArray();
            quickSort(arr);
            String value = new String(arr);
            this.codeWords.put(key, value);
        }
    }

    public String[] findCandidates(final String word) {
        char[] arr = word.toLowerCase().toCharArray();

        quickSort(arr);

        String quickSortWord = new String(arr);

        ArrayList<String> wordsArr = new ArrayList<>();

        for (Map.Entry<String, String> entry : this.codeWords.entrySet()) {
            if (Objects.equals(quickSortWord, entry.getValue())) {
                wordsArr.add(entry.getKey());
            }
        }

        return wordsArr.toArray(new String[wordsArr.size()]);
    }

    private static void quickSort(char[] arr) {
        quickSortRecursive(arr, 0, arr.length - 1);
    }

    private static void quickSortRecursive(char[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotPos = partition(arr, left, right);

        quickSortRecursive(arr, left, pivotPos - 1);
        quickSortRecursive(arr, pivotPos + 1, right);
    }

    private static int partition(char[] arr, int left, int right) {
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

    private static void swap(char[] arr, int pos1, int pos2) {
        char temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }
}