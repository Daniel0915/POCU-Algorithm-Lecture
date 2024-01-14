package academy.pocu.comp3500.lab3;

public class SearchRotate {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        System.out.println(indexOfRotatedArray(array, 5));
    }

    public static int indexOfRotatedArray(int[] array, int num) {
        return indexOfRotatedArrayRecursive(array, 0, array.length - 1, num);
    }

    public static int indexOfRotatedArrayRecursive(int[] array, int start, int end, int num) {
        if (start > end) {
            return -1;
        }
        
        int mid = (start + end) / 2;
        
        if (array[mid] == num) {
            return mid;
        }

        /********************* 튀는 부분이 mid 기준 오른쪽에 있을 경우[mid 기준 왼쪽은 정렬됨] - [start] *********************/
        // start 부터 mid 까지에 있는 숫자들이 정렬된 경우 + 탐색 대상이 start 부터 mid 까지의 요소 범위에 있을 경우
        if (array[start] <= array[mid]) {
            if (num >= array[start] && num <= array[mid]) {
                return indexOfRotatedArrayRecursive(array, start, mid - 1, num);
            }

            return indexOfRotatedArrayRecursive(array, mid + 1, end, num);
        }
        /********************* 튀는 부분이 mid 기준 오른쪽에 있을 경우[mid 기준 왼쪽은 정렬됨] - [end] *********************/



        /******************* 튀는 부분이 mid 기준 왼쪽에 있을 경우[mid 기준 오른쪽은 정렬됨] - [start] *********************/
        // mid 부터 end 까지에 있는 숫자들이 정렬된 경우 + 탐색 대상이 mid 부터 end 까지의 요소 범위에 있을 경우
        if (num >= array[mid] && num <= array[end]) {
            return indexOfRotatedArrayRecursive(array, mid + 1, end, num);
        }

        // mid 부터 end 까지에 있는 숫자들이 정렬된 경우 + 탐색 대상이 mid 부터 end 까지의 요소 범위에 없을 경우
        return indexOfRotatedArrayRecursive(array, start, mid - 1, num);
        /******************* 튀는 부분이 mid 기준 왼쪽에 있을 경우[mid 기준 오른쪽은 정렬됨] - [end] *********************/
    }

}
