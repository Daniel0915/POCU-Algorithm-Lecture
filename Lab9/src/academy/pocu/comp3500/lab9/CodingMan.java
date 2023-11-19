package academy.pocu.comp3500.lab9;

import academy.pocu.comp3500.lab9.data.Task;
import academy.pocu.comp3500.lab9.data.VideoClip;

public class CodingMan {
    public static int findMinClipsCount(final VideoClip[] clips, int time) {
        if (clips.length == 0) {
            return -1;
        }

        int clipsCnt = 0;

        // 1. 시작 시간이 이른것으로 정렬(오름차순)
        quickSort(clips);

        // 2. 첫번째 요소의 startTime 이 0 이 아니라면 -1 반환

        for (int i = 0; i < clips.length; ++i) {
            if (i != clips.length - 1) {
                if (clips[i].getStartTime() > clips[i + 1].getEndTime()) {
                    return -1;
                }
            }

            if (i == clips.length - 1 && clips.length > 1) {
                if (clips[i].getStartTime() > clips[i - 1].getEndTime()) {
                    return -1;
                }
            }

            if (i == 0) {
                if (clips[i].getStartTime() != 0) {
                    return -1;
                }
                // 3. 정렬된 clips 의 요소들을 time >= endTime 이면 count +1
            }

            // 4. 그런데 그 전 요소의 startTime, endTime 이 현재 시간 안에 포함된다면 count 제외 (단, 첫번째 요소는 해당 조건 제외)
            if (i != 0) {
                if (isInclude(clips[i - 1], clips[i])) {
                    continue;
                }
            }

            if (clips[i].getStartTime() <= time && clips[i].getEndTime() >= time) {
                ++clipsCnt;
                return clipsCnt;
            }

            if (clips[i].getEndTime() < time) {
                ++clipsCnt;
            }

        }

        return clipsCnt;
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

        boolean isPreClipInclude = false;
        boolean isNowClipInclude = false;

        if (nowStartTime <= preStartTime && preEndTime <= nowEndTime) {
            isPreClipInclude = true;
        }

        if (preStartTime <= nowStartTime && nowEndTime <= preEndTime) {
            isNowClipInclude = true;
        }

        return isPreClipInclude || isNowClipInclude;
    }


}