package academy.pocu.comp3500.lab6.app;

import academy.pocu.comp3500.lab6.League;
import academy.pocu.comp3500.lab6.leagueofpocu.Player;

public class Program {

    public static void main(String[] args) {

        Player player1 = new Player(1, "player1", 4);
        Player player2 = new Player(2, "player2", 6);
        Player player3 = new Player(3, "player3", 7);
        Player player4 = new Player(4, "player4", 9);
        Player player5 = new Player(5, "player5", 11);
        Player player6 = new Player(6, "player6", 12);

        League league = new League(new Player[]{player6, player4, player1, player2, player5, player3 });
        league.traverseInOrderPreSuc(league.getPlayers(), league.getTreeNodeOrNull(league.getPlayers(), player6), false);

        System.out.println("pre : " + league.getPreNode()); //
        System.out.println("sus : " + league.getSucNode()); // 6




        // getTop(), getBottom()
//        Player[] topPlayers = league.getTop(3);
//
//        assert (topPlayers[0].getId() == player6.getId());
//        assert (topPlayers[1].getId() == player5.getId());
//        assert (topPlayers[2].getId() == player4.getId());
//
//        Player[] bottomPlayers = league.getBottom(3);
//
//        assert (bottomPlayers[0].getId() == player1.getId());
//        assert (bottomPlayers[1].getId() == player2.getId());
//        assert (bottomPlayers[2].getId() == player3.getId());
//
//        // join()
//        boolean joinSuccess = league.join(new Player(7, "player7", 10));
//        assert (joinSuccess);
//
//        joinSuccess = league.join(new Player(1, "player1", 4));
//        assert (!joinSuccess);
//
//        // leave()
//        boolean leaveSuccess = league.leave(new Player(5, "player5", 11));
//        assert (leaveSuccess);
//
//        leaveSuccess = league.leave(new Player(5, "player5", 11));
//        assert (!leaveSuccess);
    }
}