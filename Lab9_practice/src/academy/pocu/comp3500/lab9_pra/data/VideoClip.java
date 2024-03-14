package academy.pocu.comp3500.lab9_pra.data;

public final class VideoClip {
    private final int startTime;
    private final int endTime;

    public VideoClip(int start, int end) {
        startTime = start;
        endTime = end;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }
}
