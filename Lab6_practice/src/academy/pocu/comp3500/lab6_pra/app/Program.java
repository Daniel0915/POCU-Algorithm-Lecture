package academy.pocu.comp3500.lab6_pra.app;

import academy.pocu.comp3500.lab6_pra.League;
import academy.pocu.comp3500.lab6_pra.TreeNode;
import academy.pocu.comp3500.lab6_pra.leagueofpocu.Player;
import academy.pocu.comp3500.lab6_pra.leagueofpocu.Test;
import java.util.Arrays;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        Player player1 = new Player(1, "player1", 12);
        Player player2 = new Player(2, "player2", 13);
        Player player3 = new Player(3, "player3", 15);

        League league = new League(new Player[]{player1, player2, player3});

        league.getTreeNode();
    }

}
