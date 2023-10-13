package academy.pocu.comp3500.lab6;

import academy.pocu.comp3500.lab6.leagueofpocu.Player;
import java.util.ArrayList;
import java.util.List;

public class League {
    private TreeNode players;
    private TreeNode sucNode;
    private TreeNode preNode;
    private List<TreeNode> arrayTreeNode;

    public League() {}

    public League(Player[] players) {
        TreeNode playersNode = new TreeNode(players[0]);
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

    public Player findMatchOrNull(final Player player) {
        // 1명만 선수가 등록되면 매칭 성사불가로 null
        if (players.getLeft() == null && players.getRight() == null) {
            return null;
        }

        findPreSuc(this.players, getTreeNodeOrNull(this.players, player));

        if (this.preNode == null && this.sucNode != null) {
            return this.sucNode.getPlayer();
        }

        if (this.preNode != null && this.sucNode == null) {
            return this.preNode.getPlayer();
        }

        int preDiff = Math.abs(this.preNode.getPlayer().getRating() - player.getRating());
        int sucDiff = Math.abs(this.sucNode.getPlayer().getRating() - player.getRating());

        if (preDiff >= sucDiff) {
            return this.sucNode.getPlayer();
        }

        return this.preNode.getPlayer();
    }

    public Player[] getTop(final int count) {
        Player[] result = new Player[0];
        if (this.players != null) {
            result = new Player[count];
            getTopRecursive(this.players, result, 0, count);
        }
        return result;
    }

    public Player[] getBottom(final int count) {
        Player[] result = new Player[count];
        getBottomRecursive(this.players, result, 0, count);
        return result;
    }

    public boolean join(final Player player) {
        if (getTreeNodeOrNull(this.players, player) != null) {
            return false;
        }
        insertRecursive(this.players, player);
        return true;
    }

    public boolean leave(final Player player) {
        TreeNode findTreeNode = getTreeNodeOrNull(this.players, player);
        if (findTreeNode != null) {
            findPreSuc(this.players, findTreeNode);
            if (this.preNode == null && this.sucNode != null) {
                findTreeNode.setPlayer(this.sucNode.getPlayer());
                this.sucNode = null;
                return true;
            }

            if (this.preNode != null && this.sucNode == null) {
                findTreeNode.setPlayer(this.preNode.getPlayer());
                this.preNode = null;
            }

            // preNode, sucNode 모두 있을 경우
            findTreeNode.setPlayer(this.sucNode.getPlayer());
            this.sucNode = null;

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

    private void traverseInOrderSuccessor(TreeNode treeNode, TreeNode targetNode, boolean isTargetNextNode) {
        if (treeNode == null) {
            return;
        }

        traverseInOrderSuccessor(treeNode.getLeft(), targetNode, isTargetNextNode);

        if (isTargetNextNode == true) {
            this.sucNode = treeNode;
            return;
        }

        if (treeNode == targetNode) {
            isTargetNextNode = true;
        }

        traverseInOrderSuccessor(treeNode.getRight(), targetNode, isTargetNextNode);
    }

    public void traverseInOrderPredecessor(TreeNode treeNode, TreeNode targetNode) {
        if (treeNode == null) {
            return;
        }

        traverseInOrderPredecessor(treeNode.getLeft(), targetNode);

        if (treeNode.getPlayer().getRating() == targetNode.getPlayer().getRating()) {
            return; // 대상 노드를 찾았으므로 더 이상 진행하지 않음
        }

        this.preNode = treeNode; // 이전 노드 업데이트

        traverseInOrderPredecessor(treeNode.getRight(), targetNode);
    }

    private void traverseInOrder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        traverseInOrder(treeNode.getLeft());
        arrayTreeNode.add(treeNode);
        traverseInOrder(treeNode.getRight());
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