package academy.pocu.comp3500.lab2;

public class Array {
    private int[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public Array() {
        this(DEFAULT_CAPACITY);
    }

    public int[] getArray() {
        return array;
    }

    public Array(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Invalid initial capacity");
        }
        array = new int[size];
        this.size = 0;
    }


    public void add(int value) {
        if (size == array.length) {
            int[] newArray = new int[size + 1];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }

        array[size] = value;
        size++;
    }

    public int getSize() {
        return size;
    }

    public void add(int value, int index) {
        if (index < 0  || index > size) {
            throw new IllegalArgumentException("Invalid index");
        }

        if (size == array.length) {
            int[] newArray = new int[size + 1];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }

        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }

        array[index] = value;
        size++;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Invalid index");
        }

        if (index == size - 1) {
            array[index] = 0;
            size--;
            System.out.println("마지막 요소 삭제");
            return;
        }

        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }

        array[size - 1] = 0;
        size--;
    }
}
