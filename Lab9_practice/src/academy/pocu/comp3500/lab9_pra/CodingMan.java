package academy.pocu.comp3500.lab9_pra;

import academy.pocu.comp3500.lab9_pra.data.VideoClip;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class CodingMan {
    public static int findMinClipsCount(final VideoClip[] clips, int time) {
        quickSort(clips);

        if (clips[0].getStartTime() > 0) {
            return -1;
        }

        Stack<VideoClip> stack = new Stack<>();

        stack.push(clips[0]);

        for (int index = 0; index < clips.length; index++) {
            // 현재 스택에 있는 대상의 범위가 time 범위를 초과하는 경우, 해당 범위를 카운트하지 않기 위함.
            if (stack.peek().getEndTime() >= time) {
                break;
            }

            if (clips[index].getStartTime() > stack.peek().getEndTime()) {
                return -1;
            }

            if (clips[index].getEndTime() <= stack.peek().getEndTime()) {
                continue;
            }

            if (clips[index].getEndTime() > stack.peek().getEndTime()) {
                if (stack.peek().getStartTime() == clips[index].getStartTime()) {
                    stack.pop();
                    stack.push(clips[index]);
                } else {
                    stack.push(clips[index]);
                }
            }
        }


        return stack.size();
    }

    public static int findMinClipsCount2(final VideoClip[] clips, int time) {
        if (clips.length == 0) {
            return -1;
        }

        quickSort(clips);

        Stack<VideoClip> stack = new Stack<>();
        int mustIncludeThisTime = 0;

        if (clips[0].getStartTime() != mustIncludeThisTime) {
            return -1;
        }

        stack.push(clips[0]);

        for (VideoClip clip : clips) {
            // [이해함]
            // stack 의 최근에 쌓인 대상의 끝 시작 >= time
            // 내가 목표하는(time) 시간 범위에서 넘은 것은 필요 없기 때문에
            if (stack.peek().getEndTime() >= time) {
                break;
            }

            // 현재 시작 시간 > 가장 최근의 쌓인 시간대의 끝 시간
            if (clip.getStartTime() > stack.peek().getEndTime()) {
                continue;
            }

            // mustIncludeThisTime(꼭 포함해야하는 시간??) >= 현재 시작 시간
            if (mustIncludeThisTime >= clip.getStartTime()) {
                // 가장 최근의 쌓인 시간대의 끝 시간 < 현재 끝 시작
                if (stack.peek().getEndTime() < clip.getEndTime()) {
                    stack.pop();
                    stack.push(clip);
                }
            } else {
                // 가장 최근의 쌓인 시간대의 끝 시간
                mustIncludeThisTime = stack.peek().getEndTime();
                stack.push(clip);
            }
        }

        if (stack.peek().getEndTime() < time) {
            return -1;
        }

        // 굳이 스택을 썼어야 했니.. 노노노...

        return stack.size();
    }

    private static void quickSort(VideoClip[] clips) {
        quickSortRecursive(clips, 0, clips.length - 1);
    }

    private static void quickSortRecursive(VideoClip[] clips, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotPos = partition(clips, left, right);

        quickSortRecursive(clips, left, pivotPos - 1);
        quickSortRecursive(clips, pivotPos + 1, right);
    }

    private static int partition(VideoClip[] clips, int left, int right) {
        int pivot = clips[right].getStartTime();
        int i = (left - 1);

        for (int j = left; j < right; ++j) {
            if (clips[j].getStartTime() < pivot) {
                ++i;
                swap(clips, i, j);
            }
        }

        int pivotPos = i + 1;

        swap(clips, pivotPos, right);

        return pivotPos;
    }

    private static void swap(VideoClip[] clips, int pos1, int pos2) {
        VideoClip temp = clips[pos1];
        clips[pos1] = clips[pos2];
        clips[pos2] = temp;
    }

    private static boolean isInclude(VideoClip preClip, VideoClip nowClip) {
        int preStartTime = preClip.getStartTime();
        int preEndTime = preClip.getEndTime();

        int nowStartTime = nowClip.getStartTime();
        int nowEndTime = nowClip.getEndTime();

        if (nowStartTime <= preStartTime && preEndTime <= nowEndTime) {
            return true;
        }
        return false;
    }

    private static boolean isContinue(VideoClip preClip, VideoClip nowClip) {
        int preEndTime = preClip.getEndTime();
        int nowStartTime = nowClip.getStartTime();

        if (preEndTime < nowStartTime) {
            return false;
        }

        return true;
    }

    private static boolean isTimeInclude(VideoClip videoClip, int time) {
        int startTime = videoClip.getStartTime();
        int endTime = videoClip.getEndTime();

        if (time >= startTime && time <= endTime) {
            return true;
        }
        return false;
    }


}