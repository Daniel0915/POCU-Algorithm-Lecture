package academy.pocu.comp3500.lab6.app;

import academy.pocu.comp3500.lab6.League;
import academy.pocu.comp3500.lab6.leagueofpocu.Player;

public class Program {

    public static void main(String[] args) {
        Player player1 = new Player(1, "player1", 12);
        Player player2 = new Player(2, "player2", 17);
        Player player3 = new Player(3, "player3", 11);
        Player player4 = new Player(4, "player4", 18);
        Player player5 = new Player(5, "player5", 10);

        League league = new League(new Player[]{player1, player2, player3, player4, player5});

        Player[] topPlayers = league.getTop(3); // player4, player2, player1
    }

    public static void G02_test() {
        Player player5 = new Player(5, "player5", 11);
        Player player6 = new Player(6, "player6", 12);


        League league = new League(new Player[]{player5, player6});

        boolean leaveSuccess = league.leave(player6);
        assert (leaveSuccess);


        leaveSuccess = league.leave(player6);
        assert (!leaveSuccess);

        league = new League(new Player[]{player6, player5});


        leaveSuccess = league.leave(player6);
        assert (leaveSuccess);


        leaveSuccess = league.leave(player6);
        assert (!leaveSuccess);




        league = new League(new Player[]{player6, player5});


        leaveSuccess = league.leave(player5);
        assert (leaveSuccess);


        leaveSuccess = league.leave(player5);
        assert (!leaveSuccess);




        league = new League(new Player[]{player6, player5});


        leaveSuccess = league.leave(player5);
        assert (leaveSuccess);


        leaveSuccess = league.leave(player5);
        assert (!leaveSuccess);
    }

    public static void pocu() {
        // Constructors
        League emptyLeague = new League();

        Player[] emptyLeaguePlayers = emptyLeague.getTop(10);

        assert (emptyLeaguePlayers.length == 0);

        Player player1 = new Player(1, "player1", 4);
        Player player2 = new Player(2, "player2", 6);
        Player player3 = new Player(3, "player3", 7);
        Player player4 = new Player(4, "player4", 9);
        Player player5 = new Player(5, "player5", 11);
        Player player6 = new Player(6, "player6", 12);

        League league = new League(new Player[]{player6, player4, player1, player2,
                player5, player3});

        // findMatchOrNull()
        Player match = league.findMatchOrNull(player3);
        assert (match.getId() == player2.getId());

        match = league.findMatchOrNull(player4);
        assert (match.getId() == player5.getId());

        match = league.findMatchOrNull(player6);
        assert (match.getId() == player5.getId());

        // getTop(), getBottom()
        Player[] topPlayers = league.getTop(3);

        assert (topPlayers[0].getId() == player6.getId());
        assert (topPlayers[1].getId() == player5.getId());
        assert (topPlayers[2].getId() == player4.getId());

        Player[] bottomPlayers = league.getBottom(3);

        assert (bottomPlayers[0].getId() == player1.getId());
        assert (bottomPlayers[1].getId() == player2.getId());
        assert (bottomPlayers[2].getId() == player3.getId());

        // join()
        boolean joinSuccess = league.join(new Player(7, "player7", 10));
        assert (joinSuccess);

        joinSuccess = league.join(new Player(1, "player1", 4));
        assert (!joinSuccess);

        // leave()
        boolean leaveSuccess = league.leave(new Player(5, "player5", 11));
        assert (leaveSuccess);

        leaveSuccess = league.leave(new Player(5, "player5", 11));
        assert (!leaveSuccess);
    }

}