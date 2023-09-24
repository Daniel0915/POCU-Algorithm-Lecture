package academy.pocu.comp3500.lab3;

import java.util.ArrayList;

public final class MissionControl {

    private MissionControl() {
    }

    public static int findMaxAltitudeTime(final int[] altitudes) {
        if (altitudes.length == 1) {
            return 0;
        }

        int start = 0;
        int end = altitudes.length - 1;
        int mid;

        while (start <= end) {
            if (start == 0 && end == 0) {
                return 0;
            }

            if (end - start == 1) {
                if (altitudes[start] > altitudes[end]) {
                    return start;
                }
                return end;
            }

            if (start == end) {
                return end;
            }

            mid = (end + start) / 2;
            if (altitudes[mid] > altitudes[mid - 1] && altitudes[mid] > altitudes[mid + 1]) {
                return mid;
            }

            // mid 기준 오른쪽
            if (altitudes[mid] > altitudes[mid - 1] && altitudes[mid] < altitudes[mid + 1]) {
                start = mid + 1;
                // mid 기준 왼쪽
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }

    public static ArrayList<Integer> findAltitudeTimes(final int[] altitudes, final int targetAltitude) {
        ArrayList<Integer> result = new ArrayList<>();
        int altitudesLength = altitudes.length;


        if (altitudesLength == 0) {
            return result;
        }

        int lastIndex = altitudesLength - 1;

        if (altitudesLength == 1) {
            if (altitudes[0] == targetAltitude) {
                result.add(0);
                return result;
            }
            return result;
        }

        int mid = findMaxAltitudeTime(altitudes);

        if (mid == lastIndex) {
            if (targetAltitude == altitudes[lastIndex]) {
                result.add(lastIndex);
                return result;
            }
        }

        if (mid == 0) {
            if (targetAltitude == altitudes[0]) {
                result.add(0);
                return result;
            }
        }

        if (altitudes[mid] == targetAltitude) {
            result.add(mid);
            return result;
        }

        int increaseStartIndex = 0;
        int increaseEndIndex = mid - 1;

        int left = binarySearch(altitudes, increaseStartIndex, increaseEndIndex, targetAltitude);



        int decreaseStartIndex = mid + 1;
        int decreaseEndIndex = altitudes.length - 1;

        int right = reverseBinarySearch(altitudes, decreaseStartIndex, decreaseEndIndex, targetAltitude);

        if (left != -1) {
            result.add(left);
        }

        if (right != -1) {
            result.add(right);
        }

        return result;
    }

    private static int binarySearch(final int[] altitudes, int start, int end, final int targetAltitude) {
        if (start == 0 && end == 0) {
            if (altitudes[0] == targetAltitude) {
                return 0;
            }

            if (altitudes[1] == targetAltitude) {
                return 1;
            }
        }


        if (start > end) {
            return -1;
        }

        int mid = (start + end) / 2;

        if (altitudes[mid] == targetAltitude) {
            return mid;
        }

        if (targetAltitude < altitudes[mid]) {
            return binarySearch(altitudes, start, mid - 1, targetAltitude);
        }

        return binarySearch(altitudes, mid + 1, end, targetAltitude);
    }

    private static int reverseBinarySearch(final int[] altitudes, int start, int end, final int targetAltitude) {
        int lastIndex = altitudes.length - 1;

        if (start == lastIndex && end == lastIndex) {
            if (altitudes[lastIndex] == targetAltitude) {
                return lastIndex;
            }

            if (altitudes[lastIndex - 1] == targetAltitude) {
                return lastIndex - 1;
            }
        }



        if (start > end) {
            return -1;
        }

        int mid = (start + end) / 2;

        if (altitudes[mid] == targetAltitude) {
            return mid;
        }

        if (targetAltitude > altitudes[mid]) {
            return reverseBinarySearch(altitudes, start, mid - 1, targetAltitude);
        }

        return reverseBinarySearch(altitudes, mid + 1, end, targetAltitude);
    }
}