package academy.pocu.comp3500.lab10.app;

import academy.pocu.comp3500.lab10.Project;
import academy.pocu.comp3500.lab10.ProjectPractice;


import academy.pocu.comp3500.lab10.project.Task;
import java.util.HashMap;
import java.util.List;

public class Program {

    public static void main(String[] args) {
        {
            Task[] tasks = createTasks();

            List<String> schedule = ProjectPractice.findSchedule2(tasks, false);

            assert (schedule.size() == 6);
            assert (schedule.get(0).equals("A"));
            assert (schedule.get(1).equals("B"));
            assert (schedule.get(2).equals("C"));
            assert (schedule.get(3).equals("E"));
            assert (schedule.get(4).equals("F"));
            assert (schedule.get(5).equals("I"));
        }



//        {
//            Task a = new Task("3", 15);
//
//            Task b = new Task("2", 12);
//            // b.addPredecessor(a, c);
//
//            Task c = new Task("0", 11);
//            // c.addPredecessor(d, f);
//
//            Task d = new Task("1", 11);
//            // d.addPredecessor(e);
//
//            Task e = new Task("6", 11);
//
//            Task f = new Task("5", 11);
//            // f.addPredecessor(g);
//
//            Task g = new Task("4", 11);
//            // g.addPredecessor(c);
//
//            b.addPredecessor(a, c);
//            c.addPredecessor(d, f);
//            d.addPredecessor(e);
//            f.addPredecessor(g);
//            g.addPredecessor(c);
//
//            Task[] tasks = new Task[]{a, b, c, d, e, f, g};
//
////            Task a = new Task("A", 15);
////            Task b = new Task("B", 12);
////            Task c = new Task("C", 11);
////
////            c.addPredecessor(b);
////            b.addPredecessor(a);
////
////            Task[] tasks = new Task[]{b, c, a};
//
//
//            List<String> schedule = ProjectPractice.findSchedule2(tasks, false);
//
//        }
    }

    private static Task[] createTasks() {
        Task a = new Task("A", 12);
        Task b = new Task("B", 7);
        Task c = new Task("C", 10);
        Task d = new Task("D", 9);
        Task e = new Task("E", 8);
        Task f = new Task("F", 11);
        Task g = new Task("G", 14);
        Task h = new Task("H", 13);
        Task i = new Task("I", 6);

        i.addPredecessor(f);
        f.addPredecessor(e);
        e.addPredecessor(b, c);
        d.addPredecessor(c, h);
        c.addPredecessor(b);
        b.addPredecessor(a);
        h.addPredecessor(g);
        g.addPredecessor(d);

        return new Task[]{
                a, b, c, d, e, f, g, h, i
        };
    }
}