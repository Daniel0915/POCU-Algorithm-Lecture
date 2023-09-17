public class StackImpl {
    private int size;
    private Integer[] stack;
    private int topIndex;

    public StackImpl(int size) {
        this.size = size;
        this.topIndex = 0;
        stack = new Integer[size];
    }

    public void push(int value) {
        // topIndex 가 stack 크기보다 커지면
        if (topIndex > size - 1) {
            System.out.println("stack Overflow");
            System.out.println("value : " + value);
        } else if (topIndex == size - 1) {
            stack[topIndex] = value;
        } else {
            stack[topIndex] = value;
            topIndex++;
        }
    }

    public Integer pop() {
        if (topIndex < 0) {
            System.out.println("값이 없습니다.");
            return null;
        } else {
            int popValue = stack[topIndex];
            topIndex--;
            return popValue;
        }
    }

    public boolean isValue(Integer value) {
        Integer[] copyStack = new Integer[size];
        for (Integer index = stack.length - 1; index >= 0; index--) {
            Integer popValue = pop();
            if (popValue == value) {
                topIndex++;
                push(value);
                return true;
            } else {
                copyStack[index] = popValue;
            }
        }
        stack = copyStack;
        topIndex = copyStack.length;
        return false;
    }

    public Integer get(int index) {
        return stack[index];
    }
}
