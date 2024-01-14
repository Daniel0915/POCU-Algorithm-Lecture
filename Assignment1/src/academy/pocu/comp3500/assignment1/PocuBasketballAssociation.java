package academy.pocu.comp3500.assignment1;

import academy.pocu.comp3500.assignment1.pba.GameStat;
import academy.pocu.comp3500.assignment1.pba.Player;
import java.util.Objects;

public final class PocuBasketballAssociation {
    private PocuBasketballAssociation() {
    }

    public static void processGameStats(final GameStat[] gameStats, final Player[] outPlayers) {
        for (int i = 1; i <= outPlayers.length; i++) {
            int avgPoints = 0;
            int avgAssists = 0;
            int avgNumPasses = 0;

            int sumPoints = 0;
            int sumAssists = 0;
            int sumNumPasses = 0;
            int sumGoals = 0;
            int sumGoalAttempts = 0;
            int percentGoals = 0;

            int sumGames = 0;
            String playerName = "Player " + i;

            for (int j = 0; j < gameStats.length; j++) {
                if (Objects.equals(playerName, gameStats[j].getPlayerName())) {
                    sumGames++;
                    sumPoints += gameStats[j].getPoints();
                    sumAssists += gameStats[j].getAssists();
                    sumNumPasses += gameStats[j].getNumPasses();
                    sumGoals += gameStats[j].getGoals();
                    sumGoalAttempts += gameStats[j].getGoalAttempts();
                }
            }
            // 평균 구하기
            // 평균 득점수
            avgPoints = sumPoints / sumGames;
            // 평균 어시스트수
            avgAssists = sumAssists / sumGames;
            // 평균 패스수
            avgNumPasses = sumNumPasses / sumGames;

            // 슛 성공률
            percentGoals = 100 * sumGoals / sumGoalAttempts;

            outPlayers[i - 1].setName(playerName);
            outPlayers[i - 1].setPointsPerGame(avgPoints);
            outPlayers[i - 1].setAssistsPerGame(avgAssists);
            outPlayers[i - 1].setPassesPerGame(avgNumPasses);
            outPlayers[i - 1].setShootingPercentage(percentGoals);
        }
    }

    public static Player findPlayerPointsPerGame(final Player[] players, int targetPoints) {
        int minDiff = Integer.MAX_VALUE;
        int resultIndex = 0;

        for (int i = 0; i < players.length; ++i) {
            int diff = Math.abs(players[i].getPointsPerGame() - targetPoints);

            if (diff <= minDiff) {
                minDiff = diff;
                resultIndex = i;
            }
        }

        return players[resultIndex];
    }

    public static Player findPlayerShootingPercentage(final Player[] players, int targetShootingPercentage) {
        int minDiff = Integer.MAX_VALUE;
        int resultIndex = 0;

        for (int i = 0; i < players.length; ++i) {
            int diff = Math.abs(players[i].getShootingPercentage() - targetShootingPercentage);

            if (diff <= minDiff) {
                minDiff = diff;
                resultIndex = i;
            }
        }

        return players[resultIndex];
    }

    public static long find3ManDreamTeam(final Player[] players, final Player[] outPlayers, final Player[] scratch) {
        int sum = 0;

        for (int i = 0; i < players.length; i++) {
            scratch[0] = players[i];
            for (int j = i + 1; j < players.length; j++) {
                scratch[1] = players[j];
                for (int k = j + 1; k < players.length; k++) {
                    scratch[2] = players[k];
                    int scratchSum = getTeamWorkPoints(scratch);
                    if (scratchSum > sum) {
                        sum = scratchSum;
                        for (int l = 0; l < scratch.length; l++) {
                            outPlayers[l] = scratch[l];
                        }
                    }
                }
            }
        }
        return sum;
    }

    public static long findDreamTeam(final Player[] players, int k, final Player[] outPlayers, final Player[] scratch) {
        int[] teamWorkPoints = {0};
        generateCombinations(players, outPlayers, scratch, 0, 0, teamWorkPoints);
        long max = getTeamWorkPoints(outPlayers);
        return max;
    }

    public static int findDreamTeamSize(final Player[] players, final Player[] scratch) {
        int[] teamWorkPoints = {0};
        int[] bigTeamWorkPoints = {0};
        generateSubsets(players, scratch, 0, 0, teamWorkPoints, bigTeamWorkPoints);

        return bigTeamWorkPoints[0];
    }

    private static int getTeamWorkPoints(Player[] players) {
        int sumPassesPerGame = 0;
        int minAssistsPerGame = Integer.MAX_VALUE;

        for (int i = 0; i < players.length; i++) {
            sumPassesPerGame += players[i].getPassesPerGame();
            minAssistsPerGame = Math.min(players[i].getAssistsPerGame(), minAssistsPerGame);
        }

        return sumPassesPerGame * minAssistsPerGame;
    }

    private static void generateCombinations(final Player[] players, final Player[] outPlayers, final Player[] scratch, int index, int start, int[] teamWorkPoints) {
        int k = outPlayers.length;

        if (index == k) {
            int nowTeamWorkPoints = getTeamWorkPoints(scratch);

            if (nowTeamWorkPoints > teamWorkPoints[0]) {
                teamWorkPoints[0] = nowTeamWorkPoints;
                for (int l = 0; l < scratch.length; l++) {
                    outPlayers[l] = scratch[l];
                }
            }
            return;
        }

        for (int i = start; i < players.length; i++) {
            scratch[index] = players[i];
            // 다음 요소를 선택하기 위해 재귀 호출
            generateCombinations(players, outPlayers, scratch, index + 1, i + 1, teamWorkPoints);
        }
    }

    private static void generateSubsets(Player[] players, Player[] scratch, int currentIndex, int currentSize, int[] teamWorkPoints, int[] bigTeamWorkPoints) {
        if (currentIndex == players.length) {

            int nowTeamWorkPoints = getTeamWorkPointsForFindDreamTeamSize(scratch, currentSize);

            if (nowTeamWorkPoints > teamWorkPoints[0]) {
                teamWorkPoints[0] = nowTeamWorkPoints;
                bigTeamWorkPoints[0] = currentSize;
            }
            return;
        }

        // 현재 요소를 부분 집합에 포함하지 않는 경우
        generateSubsets(players, scratch, currentIndex + 1, currentSize, teamWorkPoints, bigTeamWorkPoints);

        // 현재 요소를 부분 집합에 포함하는 경우
        scratch[currentSize] = players[currentIndex];
        generateSubsets(players, scratch, currentIndex + 1, currentSize + 1, teamWorkPoints, bigTeamWorkPoints);
    }

    private static int getTeamWorkPointsForFindDreamTeamSize(Player[] players, int size) {
        int sumPassesPerGame = 0;
        int minAssistsPerGame = Integer.MAX_VALUE;

        for (int i = 0; i < size; i++) {
            sumPassesPerGame += players[i].getPassesPerGame();
            minAssistsPerGame = Math.min(players[i].getAssistsPerGame(), minAssistsPerGame);
        }

        return sumPassesPerGame * minAssistsPerGame;
    }
}