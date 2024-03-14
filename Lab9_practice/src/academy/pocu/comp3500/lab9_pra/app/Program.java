package academy.pocu.comp3500.lab9_pra.app;

import academy.pocu.comp3500.lab9_pra.CodingMan;
import academy.pocu.comp3500.lab9_pra.data.VideoClip;
import academy.pocu.comp3500.lab9_pra.PyramidBuilder;

public class Program {

    public static void main(String[] args) {
        // CodingMan
//        VideoClip[] clips = new VideoClip[]{
//                new VideoClip(0, 10),
//        };
//        int airTime = 10;
//
//        int count = CodingMan.findMinClipsCount(clips, airTime);
//
//        assert (count == 1);
//
//        clips = new VideoClip[]{
//                new VideoClip(30, 60),
//                new VideoClip(0, 20)
//        };
//        airTime = 60;
//
//        count = CodingMan.findMinClipsCount(clips, airTime);
//
//        assert (count == -1);

        VideoClip[]  clips = new VideoClip[]{
                new VideoClip(0, 10),
                new VideoClip(5, 9),
                new VideoClip(7, 20)
        };
        int airTime = 20;

        int count = CodingMan.findMinClipsCount(clips, airTime);

        assert (count == 2);


        clips = new VideoClip[]{
                new VideoClip(0, 5),
                new VideoClip(0, 20),
                new VideoClip(5, 30),
                new VideoClip(25, 35),
                new VideoClip(35, 70),
                new VideoClip(50, 75)
        };
        airTime = 60;

        count = CodingMan.findMinClipsCount(clips, airTime);

        assert (count == 4);


//        for (VideoClip clip : clips) {
//            // ========== 0 start ==========
//            if (15 >= 20) {
//                break;
//            }
//
//            if (0 > 15) {
//                continue;
//            }
//
//            if (0 >= 0) {
//                if (15 < 15) {
//
//                    // mustIncludeThisTime 여전히 0
//
//                }
//            }
//            // ========== 0 end ==========
//
//            // ========== 1 start ==========
//            if (15 >= 20) {
//                break;
//            }
//
//            if (10 > 15) {
//                continue;
//            }
//
//            if (0 >= 10) {
//
//            } else {
//                // mustIncludeThisTime = 15
//                // stack.push(clips[1])
//            }
//            // ========== 1 end ==========
//
//            // ========== 2 start ==========
//            if (20 >= 20) {
//                break;
//            }
//
//
//            // ========== 2 end ==========
//
//        }
//
//
//        if (20 < 20) {
//            // return -1;
//        }
//
//        // return 2;(stack.size)





    }
}
