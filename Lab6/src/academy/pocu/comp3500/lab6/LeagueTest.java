package academy.pocu.comp3500.lab6;

import academy.pocu.comp3500.lab6.leagueofpocu.Player;
import java.util.Objects;

public class LeagueTest {
    private Player[] players;

    public LeagueTest(Player[] players) {
        this.players = players;
    }

    public Player findMatchOrNull(final Player player) {
        quickSort(this.players, "rating");
        int minDiff = Integer.MAX_VALUE;
        int targetIndex = 0;

        for (int i = 0; i < players.length; i++) {
            int diff = Math.abs(players[i].getRating() - player.getRating());

            if (diff == 0) {
                continue;
            }

            if (diff == minDiff) {
                targetIndex = i;
                break;
            }

            if (diff < minDiff) {
                minDiff = diff;
                targetIndex = i;
            }
        }
        return players[targetIndex];
    }

    public Player[] getTop(final int count) {
        quickSort(this.players, "rating");
        Player[] topPlayers = new Player[count];
        int j = 0;
        for (int i = players.length - 1; i >= count - 1; i--) {
            topPlayers[j] = players[i];
            j++;
        }
        return topPlayers;
    }

    public Player[] getBottom(final int count) {
        quickSort(this.players, "rating");
        Player[] topPlayers = new Player[count];
        for (int i = 0; i < count; i++) {
            topPlayers[i] = players[i];
        }
        return topPlayers;
    }

    public boolean join(final Player player) {
        quickSort(this.players, "id");

        if (findPlayer(this.players, player.getId()) != null) {

        }

        return findPlayer(this.players, player.getId()) != null ? true : false;
    }

    public boolean leave(final Player player) {
        return false;
    }

    public Player[] getPlayers() {
        return players;
    }

    private static void quickSort(Player[] players, String type) {
        quickSortRecursive(players, 0, players.length - 1, type);
    }

    private static void quickSortRecursive(Player[] players, int left, int right, String type) {
        if (left >= right) {
            return;
        }

        int pivotPos;

        if (Objects.equals(type, "rating")) {
            pivotPos = partition(players, left, right);
        } else {
            pivotPos = partitionID(players, left, right);
        }
        quickSortRecursive(players, left, pivotPos -1, type);
        quickSortRecursive(players, pivotPos + 1, right, type);
    }

    private static int partition(Player[] players, int left, int right) {
        Player pivot = players[right];
        int i = (left - 1);

        for (int j = left; j < right; j++) {
            if (players[j].getRating() < pivot.getRating()) {
                i++;
                swap(players, i, j);
            }
        }

        int pivotPos = i + 1;

        swap(players, pivotPos, right);

        return pivotPos;
    }

    private static void swap(Player[] players, int pos1, int pos2) {
        Player temp = players[pos1];
        players[pos1] = players[pos2];
        players[pos2] = temp;
    }

    private static int partitionID(Player[] players, int left, int right) {
        Player pivot = players[right];
        int i = (left - 1);

        for (int j = left; j < right; j++) {
            if (players[j].getId() < pivot.getId()) {
                i++;
                swap(players, i, j);
            }
        }

        int pivotPos = i + 1;

        swap(players, pivotPos, right);

        return pivotPos;
    }

    public static Player binarySearchPlayer(Player[] arr, int targetId, int left, int right) {
        if (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid].getId() == targetId) {
                return arr[mid]; // 플레이어를 찾았을 때 반환
            } else if (arr[mid].getId() < targetId) {
                return binarySearchPlayer(arr, targetId, mid + 1, right); // 오른쪽 반열로 재귀 호출
            } else {
                return binarySearchPlayer(arr, targetId, left, mid - 1); // 왼쪽 반열로 재귀 호출
            }
        }

        return null; // 플레이어를 찾지 못한 경우 null 반환
    }

    public static Player findPlayer(Player[] arr, int targetId) {
        return binarySearchPlayer(arr, targetId, 0, arr.length - 1);
    }


}