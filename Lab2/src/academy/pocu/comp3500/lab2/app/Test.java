package academy.pocu.comp3500.lab2.app;

import java.util.LinkedList;
import java.util.Queue;

public class Test {

    public static void main(String[] args) {
        // LinkedList를 사용하여 큐를 생성
        Queue<Integer> queue = new LinkedList<>();

        // 큐에 요소 추가 (enqueue)
        queue.add(1);
        queue.add(2);
        queue.add(3);

        System.out.println("큐의 상태: " + queue);

        queue.poll();

        // 큐에서 요소 제거 (dequeue)
        int removedElement = queue.remove();
        System.out.println("제거된 요소: " + removedElement);

        System.out.println("큐의 상태 (요소 제거 후): " + queue);

    }

}
