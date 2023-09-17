package sort;

public class InsertSort {

    public static void main(String[] args) {
        insertSort();

    }

    public static void insertSort() {
        int[] array = {5, 3, 1, 3, 4, 6, 2};

        for (int i = 1; i < array.length; i++) {
            while (i != 0 && array[i-1] > array[i]) {
                int temp = array[i-1];
                array[i-1] = array[i];
                array[i] = temp;
                i--;
            }
        }

        for (int a = 0; a < array.length; a++) {
            System.out.println(array[a]);
        }




    }

}
