package academy.pocu.comp3500.lab9.app;

import academy.pocu.comp3500.assignment2.datastructure.ArrayList;
import academy.pocu.comp3500.lab9.CodingMan;
import academy.pocu.comp3500.lab9.PyramidBuilder;
import academy.pocu.comp3500.lab9.data.ProfitCalculator;
import academy.pocu.comp3500.lab9.data.Task;
import academy.pocu.comp3500.lab9.data.VideoClip;
import java.util.List;

public class Program {

    public static void main(String[] args) {
        test7();
        test8();
        test9();
    }

    public static void test9() {
        VideoClip[] clips1 = new VideoClip[]{
                new VideoClip(0, 7),
                new VideoClip(7, 15)
        };
        int count1 = CodingMan.findMinClipsCount(clips1, 35);
        assert (count1 == -1);
    }

    public static void test8() {
        VideoClip[] clips1 = new VideoClip[]{
                new VideoClip(0, 7),
                new VideoClip(8, 15),
                new VideoClip(15, 20),
                new VideoClip(20, 25),
                new VideoClip(25, 35)
        };
        int count1 = CodingMan.findMinClipsCount(clips1, 35);
        assert (count1 == -1);
    }

    public static void test7() {
        VideoClip[] clips1 = new VideoClip[]{};
        int count1 = CodingMan.findMinClipsCount(clips1, 35);
        assert (count1 == -1);
    }

}
