package sort;

public class Program {

    public static void main(String[] args) {
        int[] array = {5, 3, 1, 3, 4, 6, 2};
        for (int h = 0; h < array.length; h++) {
            int minAt = h;
            for (int i = h; i < array.length; i++) {
                minAt = array[i] < array[minAt] ? i : minAt;
            }
            int temp = array[minAt];
            array[minAt] = array[h];
            array[h] = temp;
        }


        for (int x = 0; x < array.length; x++) {
            System.out.println(array[x]);
        }


    }

}
