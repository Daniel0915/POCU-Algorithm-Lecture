package academy.pocu.comp3500.assignment1.app;

import academy.pocu.comp3500.assignment1.PocuBasketballAssociation;
import academy.pocu.comp3500.assignment1.pba.GameStat;
import academy.pocu.comp3500.assignment1.pba.Player;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public class Program {

    public static void main(String[] args) {
        {
            Player[] players = new Player[]{
                    new Player("player0", 91, 77, 92, 7),
                    new Player("player1", 87, 49, 53, 97),
                    new Player("player2", 91, 41, 89, 10),
                    new Player("player3", 39, 9, 81, 63),
                    new Player("player4", 2, 77, 43, 41),
                    new Player("player5", 46, 39, 59, 53),
                    new Player("player6", 51, 33, 70, 73),
                    new Player("player7", 32, 64, 62, 73),
                    new Player("player8", 61, 84, 81, 24),
                    new Player("player9", 96, 97, 7, 40),
                    new Player("player10", 17, 0, 91, 58),
                    new Player("player11", 43, 36, 86, 48),
                    new Player("player12", 65, 37, 68, 5),
                    new Player("player13", 68, 81, 41, 96),
                    new Player("player14", 33, 100, 21, 44),
                    new Player("player15", 40, 0, 92, 9),
            };

            final int TEAM_SIZE = 8;

            Player[] outPlayers = new Player[TEAM_SIZE];
            Player[] scratch = new Player[TEAM_SIZE];

            long maxTeamwork = PocuBasketballAssociation.findDreamTeam(players, TEAM_SIZE, outPlayers, scratch);

            assert (maxTeamwork == 21240);
        }

    }

    private static Player getPlayerOrNull(final Player[] players, final String id) {
        for (Player player : players) {
            if (player.getName().equals(id)) {
                return player;
            }
        }

        return null;
    }
}
