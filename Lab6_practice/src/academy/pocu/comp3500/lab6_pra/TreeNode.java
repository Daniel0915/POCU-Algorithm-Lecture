package academy.pocu.comp3500.lab6_pra;

import academy.pocu.comp3500.lab6_pra.leagueofpocu.Player;

public class TreeNode {
    private Player player;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "player=" + player +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
