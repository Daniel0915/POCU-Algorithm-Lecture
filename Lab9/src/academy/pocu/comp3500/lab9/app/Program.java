package academy.pocu.comp3500.lab9.app;

import academy.pocu.comp3500.lab9.CodingMan;
import academy.pocu.comp3500.lab9.data.ProfitCalculator;
import academy.pocu.comp3500.lab9.data.Task;
import academy.pocu.comp3500.lab9.data.VideoClip;
import academy.pocu.comp3500.lab9.PyramidBuilder;

public class Program {

    public static void main(String[] args) {
        // ProfitCalculator
        Task[] tasks = new Task[]{
                new Task(20, 30),
        };
        int[] skillLevels = new int[]{20};

        int profit = ProfitCalculator.findMaxProfit(tasks, skillLevels);

        assert (profit == 30);

        tasks = new Task[]{
                new Task(20, 30),
        };
        skillLevels = new int[]{10};

        profit = ProfitCalculator.findMaxProfit(tasks, skillLevels);

        assert (profit == 0);

        tasks = new Task[]{
                new Task(20, 50),
                new Task(20, 40)
        };
        skillLevels = new int[]{25};

        profit = ProfitCalculator.findMaxProfit(tasks, skillLevels);

        assert (profit == 50);

        tasks = new Task[]{
                new Task(20, 40),
                new Task(30, 40),
                new Task(50, 25),
                new Task(60, 45)
        };
        skillLevels = new int[]{10, 20, 35, 70, 45};

        profit = ProfitCalculator.findMaxProfit(tasks, skillLevels);

        assert (profit == 165);

    }

    public static void test9() {
        academy.pocu.comp3500.lab9.data.VideoClip[] clips1 = new academy.pocu.comp3500.lab9.data.VideoClip[]{
                new academy.pocu.comp3500.lab9.data.VideoClip(0, 7),
                new academy.pocu.comp3500.lab9.data.VideoClip(7, 15)
        };
        int count1 = academy.pocu.comp3500.lab9.CodingMan.findMinClipsCount(clips1, 35);
        assert (count1 == -1);
    }

    public static void test8() {
        academy.pocu.comp3500.lab9.data.VideoClip[] clips1 = new academy.pocu.comp3500.lab9.data.VideoClip[]{
                new academy.pocu.comp3500.lab9.data.VideoClip(0, 7),
                new academy.pocu.comp3500.lab9.data.VideoClip(8, 15),
                new academy.pocu.comp3500.lab9.data.VideoClip(15, 20),
                new academy.pocu.comp3500.lab9.data.VideoClip(20, 25),
                new academy.pocu.comp3500.lab9.data.VideoClip(25, 35)
        };
        int count1 = academy.pocu.comp3500.lab9.CodingMan.findMinClipsCount(clips1, 35);
        assert (count1 == -1);
    }

    public static void test7() {
        academy.pocu.comp3500.lab9.data.VideoClip[] clips1 = new VideoClip[]{};
        int count1 = CodingMan.findMinClipsCount(clips1, 35);
        assert (count1 == -1);
    }

}
