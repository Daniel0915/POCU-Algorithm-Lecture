import java.util.*;

public class Program {

    public static void main(String[] args) {
        Recorder recorder = new Recorder(5, 5);
        Book book = new Book(2, 4);
        Cow cow = new Cow(6, 11);

        int row = 3;
        int column = 15;

        Map<Integer, Object> map = new HashMap<>();

        map.put(0, recorder);
        map.put(1, book);
        map.put(2, cow);

        int[][] array = new int[row][column];


        int maxValue = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                Object obj = map.get(i);
                if (obj instanceof Recorder) {
                    Recorder recorder1 = (Recorder) obj;
                    array[i][j] = calculate(recorder1.money, recorder1.space, array, i, j);
                    maxValue = Math.max(maxValue, array[i][j]);
                } else if (obj instanceof Book) {
                    Book book1 = (Book) obj;
                    array[i][j] = calculate(book1.money, book1.space, array, i, j);
                    maxValue = Math.max(maxValue, array[i][j]);
                } else {
                    Cow cow1 = (Cow) obj;
                    array[i][j] = calculate(cow1.money, cow1.space, array, i, j);
                    maxValue = Math.max(maxValue, array[i][j]);
                }
            }
        }


        for (int i = 0; i <array.length; i++) {
            System.out.println(i + " : " + Arrays.toString(array[i]));
        }

        System.out.println(maxValue); // 최적값


    }

    public static int calculate(int money, int space, int[][] array, int row, int column) {
        int bagSize = column + 1;
        if (row == 0) {
            if (space > bagSize) {
                return 0;
            }

            return money;
        }

        if (space > bagSize) {
            // 이전 값 가져오기
            return array[row - 1][column];
        }

        int remainSize = bagSize - space;

        if (remainSize == 0) {
            return Math.max(array[row - 1][column], money);
        }

        int remainIndex = remainSize - 1;

        return Math.max(array[row - 1][column], money + array[row - 1][remainIndex]);
    }
}
