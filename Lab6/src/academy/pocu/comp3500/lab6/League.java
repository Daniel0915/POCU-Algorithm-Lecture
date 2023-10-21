package academy.pocu.comp3500.lab6;

import academy.pocu.comp3500.lab6.leagueofpocu.Player;
import java.util.ArrayList;
import java.util.List;

public class League {
    private TreeNode players;
    private TreeNode sucNode;
    private TreeNode preNode;
    private List<TreeNode> arrayTreeNode;
    private int playersCnt;
    private int minDiff = Integer.MAX_VALUE;
    private int minDiffPre = Integer.MAX_VALUE;
    private int minDiffSuc = Integer.MAX_VALUE;
    private int diff;
    private int diffPre;
    private int diffSuc;

    private TreeNode matchNode;

    public League() {

    }


    public League(Player[] players) {
        TreeNode playersNode = new TreeNode(players[0]);
        this.playersCnt = players.length;
        for (int i = 1; i < players.length; i++) {
            insertRecursive(playersNode, players[i]);
        }
        this.players = playersNode;
        this.arrayTreeNode = new ArrayList<>();
    }

    public TreeNode getPlayers() {
        return players;
    }

    public TreeNode getSucNode() {
        return sucNode;
    }

    public TreeNode getPreNode() {
        return preNode;
    }

    public int getPlayersCnt() {
        return playersCnt;
    }

    public TreeNode getMatchNode() {
        return matchNode;
    }

    public Player findMatchOrNull(final Player player) {
        // 리그 참여 중인 선수가 없을 경우
        if (this.playersCnt == 0) {
            return null;
        }

        TreeNode playerTreeNode = getTreeNodeOrNull(this.players, player);

        if (playerTreeNode == null) {
            return null;
        }

        // 1명만 선수가 등록되면 매칭 성사불가로 null
        if (players.getLeft() == null && players.getRight() == null) {
            return null;
        }

        traverseInOrderMatchNode(this.players, playerTreeNode, false);

        this.diff = 0;
        this.minDiff = Integer.MAX_VALUE;

        return this.matchNode.getPlayer();
    }

    public Player[] getTop(final int count) {
        Player[] result = new Player[0];

        int size = count;

        // 필요한 상위 선수(count)가 존재하는 선수보다 많을 경우
        int diff = count - playersCnt;
        if (diff > 0) {
            size = count - diff;
        }

        if (this.players != null) {
            result = new Player[size];
            getTopRecursive(this.players, result, 0, count);
        }

        return result;
    }

    public Player[] getBottom(final int count) {
        Player[] result = new Player[0];

        int size = count;

        // 필요한 하위 선수(count)가 존재하는 선수보다 많을 경우
        int diff = count - playersCnt;
        if (diff > 0) {
            size = count - diff;
        }

        if (this.players != null) {
            result = new Player[size];
            getBottomRecursive(this.players, result, 0, count);
        }

        return result;
    }

    public boolean join(final Player player) {
        if (getTreeNodeOrNull(this.players, player) != null) {
            return false;
        }
        insertRecursive(this.players, player);
        this.playersCnt++;
        return true;
    }

    public boolean leave(final Player player) {
        TreeNode findTreeNode = getTreeNodeOrNull(this.players, player);
        if (findTreeNode != null) {
            traverseInOrderPreSuc(this.players, findTreeNode, false);
            if (this.preNode == null && this.sucNode != null) {
                findTreeNode.setPlayer(this.sucNode.getPlayer());
                this.sucNode = null;

                this.playersCnt--;
                return true;
            }

            if (this.preNode != null && this.sucNode == null) {
                findTreeNode.setPlayer(this.preNode.getPlayer());
                this.preNode = null;

                this.playersCnt--;
                return true;
            }

            // preNode, sucNode 모두 있을 경우
            findTreeNode.setPlayer(this.sucNode.getPlayer());
            this.sucNode = null;

            this.playersCnt--;
            return true;
        }
        return false;
    }

    public static TreeNode insertRecursive(final TreeNode treeNode, Player player) {
        if (treeNode == null) {
            return new TreeNode(player);
        }

        if (player.getRating() < treeNode.getPlayer().getRating()) {
            treeNode.setLeft(insertRecursive(treeNode.getLeft(), player));
        } else {
            treeNode.setRight(insertRecursive(treeNode.getRight(), player));
        }

        return treeNode;
    }

    public TreeNode getTreeNodeOrNull(final TreeNode treeNode, Player player) {
        if (treeNode == null) {
            return null;
        }

        if (treeNode.getPlayer().getRating() == player.getRating()) {
            return treeNode;
        }

        if (player.getRating() < treeNode.getPlayer().getRating()) {
            return getTreeNodeOrNull(treeNode.getLeft(), player);
        }

        return getTreeNodeOrNull(treeNode.getRight(), player);
    }

    private TreeNode getNearTreeNodeOrNull(final TreeNode treeNode, Player player, final TreeNode parent) {
        if (treeNode == null) {
            return null;
        }

        if (treeNode.getPlayer().getRating() == player.getRating()) {
            if (treeNode.getLeft() == null && treeNode.getRight() == null && parent != null) {
                return parent;
            }

            if (treeNode.getLeft() == null && treeNode.getRight() != null) {
                return treeNode.getRight();
            }

            if (treeNode.getLeft() != null && treeNode.getRight() == null) {
                return treeNode.getLeft();
            }

            int leftDiff = Math.abs(treeNode.getPlayer().getRating() - treeNode.getLeft().getPlayer().getRating());
            int rightDiff = Math.abs(treeNode.getPlayer().getRating() - treeNode.getRight().getPlayer().getRating());

            if (leftDiff >= rightDiff) {
                return treeNode.getRight();
            }

            return treeNode.getLeft();
        }

        if (player.getRating() < treeNode.getPlayer().getRating()) {
            return getNearTreeNodeOrNull(treeNode.getLeft(), player, treeNode);
        }

        return getNearTreeNodeOrNull(treeNode.getRight(), player, treeNode);
    }

    private int getTopRecursive(TreeNode current, Player[] result, int index, int size) {
        if (current == null || index == size) {
            return index;
        }

        index = getTopRecursive(current.getRight(), result, index, size);

        if (index < size) {
            result[index] = current.getPlayer();
            index++;
        }

        return getTopRecursive(current.getLeft(), result, index, size);
    }

    private int getBottomRecursive(TreeNode current, Player[] result, int index, int size) {
        if (current == null || index == size) {
            return index;
        }

        index = getBottomRecursive(current.getLeft(), result, index, size);

        if (index < size) {
            result[index] = current.getPlayer();
            index++;
        }

        return getBottomRecursive(current.getRight(), result, index, size);
    }

    private void traverseInOrderMatchNode(TreeNode treeNode, TreeNode targetNode, boolean isMatchNode) {
        if (isMatchNode) {
            return;
        }

        if (treeNode == null) {
            return;
        }

        traverseInOrderMatchNode(treeNode.getLeft(), targetNode, isMatchNode); // left

        if (targetNode.getPlayer().getRating() != treeNode.getPlayer().getRating()) {
            this.diff = Math.abs(targetNode.getPlayer().getRating() - treeNode.getPlayer().getRating());;

            if (this.diff <= this.minDiff) {
                this.minDiff = this.diff;
                this.matchNode = treeNode;
            }
        }
        traverseInOrderMatchNode(treeNode.getRight(), targetNode, isMatchNode); // right
    }

    public void traverseInOrderPreSuc(TreeNode treeNode, TreeNode targetNode, boolean isPreSuc) {
        if (isPreSuc) {
            return;
        }

        if (treeNode == null) {
            return;
        }

        traverseInOrderPreSuc(treeNode.getLeft(), targetNode, isPreSuc);

        if (targetNode.getPlayer().getRating() != treeNode.getPlayer().getRating()) {
            if (treeNode.getPlayer().getRating() < targetNode.getPlayer().getRating()) {
                this.diffPre = Math.abs(targetNode.getPlayer().getRating() - treeNode.getPlayer().getRating());

                if (this.diffPre <= this.minDiffPre) {
                    this.minDiffPre = this.diffPre;
                    this.preNode = treeNode;
                }
            }

            if (treeNode.getPlayer().getRating() > targetNode.getPlayer().getRating()) {
                this.diffSuc = Math.abs(targetNode.getPlayer().getRating() - treeNode.getPlayer().getRating());

                if (this.diffSuc <= this.minDiffSuc) {
                    this.minDiffSuc = this.diffSuc;
                    this.sucNode = treeNode;
                    isPreSuc = true;
                }
            }
        }

        traverseInOrderPreSuc(treeNode.getRight(), targetNode, isPreSuc);
    }

    public void findPreSuc(TreeNode treeNode, TreeNode targetNode) {
        // Base case
        if (treeNode == null) {
            return;
        }

        // If key is present at root
        if (treeNode.getPlayer().getRating() == targetNode.getPlayer().getRating()) {
            // The maximum value in left
            // subtree is predecessor

            if (treeNode.getLeft() != null) {
                TreeNode tmp = treeNode.getLeft();
                while (tmp.getRight() != null)
                    tmp = tmp.getRight();

                preNode = tmp;
            }

            // The minimum value in
            // right subtree is successor
            if (treeNode.getRight() != null) {
                TreeNode tmp = treeNode.getRight();

                while (tmp.getLeft() != null)
                    tmp = tmp.getLeft();

                sucNode = tmp;
            }
            return;
        }

        // If key is smaller than
        // root's key, go to left subtree
        if (treeNode.getPlayer().getRating() > targetNode.getPlayer().getRating()) {
            sucNode = treeNode;
            findPreSuc(treeNode.getLeft(), targetNode);
            // Go to right subtree
        } else {
            preNode = treeNode;
            findPreSuc(treeNode.getRight(), targetNode);
        }
    }
}