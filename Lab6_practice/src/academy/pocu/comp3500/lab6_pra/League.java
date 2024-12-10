package academy.pocu.comp3500.lab6_pra;

import academy.pocu.comp3500.lab6_pra.leagueofpocu.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

public class League {
    private TreeNode treeNode;

    public League(Player[] players) {
        for (int index = 0; index < players.length; index++) {
            this.treeNode = insertRecursive(treeNode, players[index]);
        }
    }

    public Player findMatchOrNull(final Player player) {
        if (treeNode == null) {
            return null;
        }

        if (getNodeOrNull(treeNode, player) == null) {
            return null;
        }

        Stack<TreeNode> nodes = new Stack<>();

        TreeNode current = treeNode;

        boolean isTargetData = false;

        List<Player> playerList = new ArrayList<>();

        while (!nodes.empty() || current != null) {
            if (current != null) {
                nodes.push(current);
                current = current.getLeft();
            } else {
                current = nodes.pop();

                playerList.add(current.getPlayer());
                if (isTargetData) {
                    break;
                }
                if (current.getPlayer().getRating() == player.getRating()) {
                    isTargetData = true;
                }

                current = current.getRight();
            }
        }

        if (playerList.size() == 1) {
            return null;
        }

        if (playerList.size() == 2) {
            if (playerList.get(0) == player) {
                return playerList.get(1);
            }
            return playerList.get(0);
        }

        if (playerList.size() >= 3) {
            Player lastIndexElement = playerList.get(playerList.size() - 1);
            Player middleIndexElement = playerList.get(playerList.size() - 2);
            Player firstIndexElement = playerList.get(playerList.size() - 3);

            if (middleIndexElement.getRating() == player.getRating()) {
                int diffFirstMiddle = Math.abs(middleIndexElement.getRating() - firstIndexElement.getRating());
                int diffLastMiddle = Math.abs(middleIndexElement.getRating() - lastIndexElement.getRating());

                if (diffFirstMiddle > diffLastMiddle) {
                    return lastIndexElement;
                }

                if (diffFirstMiddle <= diffLastMiddle) {
                    return firstIndexElement;
                }
            }

            if (lastIndexElement.getRating() == player.getRating()) {
                return middleIndexElement;
            }
        }
        return null;
    }

    public Player[] getTop(final int count) {
        return getTopCntTraverseInOrderDesc(treeNode, count);
    }

    public Player[] getBottom(final int count) {
        return getBottomCntTraverseInOrder(treeNode, count);
    }

    public boolean join(final Player player) {
        if (getNodeOrNull(treeNode, player) != null) {
            return false;
        }

        treeNode = insertRecursive(treeNode, player);
        return true;
    }

    public boolean leave(final Player player) {
        TreeNode findNode = getNodeOrNull(treeNode, player);
        if (findNode == null) {
            return false;
        }

        // 자식 노드가 없을 경우, 대상 노드 삭제
        if (findNode.getLeft() == null && findNode.getRight() == null) {
            findNode = null;
            return true;
        }

        List<TreeNode> nodes = getTreeNodesTraverseInOrder(findNode, findNode);

        Map<String, TreeNode> preAndSucNodeMap = getPreAndSucNode(nodes, findNode);

        TreeNode findNodeLeftMaxNode = preAndSucNodeMap.get("findNodeLeftMaxNode");
        TreeNode findNodeRightMinNode = preAndSucNodeMap.get("findNodeRightMinNode");

        // 오른쪽 하위 노드가 없는 경우, 왼쪽 하위 노드 중 최대값의 노드와 swap
        if (findNodeRightMinNode == null) {
            findNode.setPlayer(findNodeLeftMaxNode.getPlayer());
            findNodeLeftMaxNode = null;
            return true;
        }

        // 왼쪽 하위 노드가 없는 경우, 오른쪽 하위 노드 중 최소값의 노드와 swap
        if (findNodeLeftMaxNode == null) {
            if (findNodeRightMinNode.getLeft() == null && findNodeRightMinNode.getRight() == null) {
                findNode.setPlayer(findNodeRightMinNode.getPlayer());
                findNodeRightMinNode = null;
                return true;
            }
            findNode = findNodeRightMinNode;
            return true;
        }

        findNode.setPlayer(findNodeRightMinNode.getPlayer());
        findNodeRightMinNode = null;
        return true;
    }

    public TreeNode getNodeOrNull(TreeNode treeNode, Player player) {
        if (treeNode == null) {
            return null;
        }

        int nowTreeNodeRating = treeNode.getPlayer().getRating();

        if (nowTreeNodeRating == player.getRating()) {
            return treeNode;
        }

        // 노드의 왼쪽 탐색
        if (nowTreeNodeRating > player.getRating()) {
            return getNodeOrNull(treeNode.getLeft(), player);
        }

        // 노드의 오른쪽 탐색
        return getNodeOrNull(treeNode.getRight(), player);
    }

    public TreeNode insertRecursive(final TreeNode treeNode, Player player) {
        if (treeNode == null) {
            return new TreeNode(player);
        }

        int nowTreeNodeRating = treeNode.getPlayer().getRating();

        // 왼쪽 노드
        if (player.getRating() < nowTreeNodeRating) {
            treeNode.setLeft(insertRecursive(treeNode.getLeft(), player));
        // 오른쪽 노드
        } else {
            treeNode.setRight(insertRecursive(treeNode.getRight(), player));
        }

        return treeNode;
    }

    public TreeNode findParentTraversePreOrder(final TreeNode treeNode, Player player) {
        if (treeNode == null) {
            return null;
        }

        if (treeNode.getPlayer().getRating() == player.getRating()) {
            return null;
        }

        Stack<TreeNode> nodes = new Stack<>();

        nodes.push(treeNode);

        while (!nodes.empty()) {
            TreeNode node = nodes.pop();

            if (node.getRight() != null) {
                if (node.getRight().getPlayer().getRating() == player.getRating()) {
                    return node;
                }
                nodes.push(node.getRight());
            }

            if (node.getLeft() != null) {
                if (node.getLeft().getPlayer().getRating() == player.getRating()) {
                    return node;
                }
                nodes.push(node.getLeft());
            }
        }

        return null;
    }

    public TreeNode getTreeNode() {
        return treeNode;
    }

    /**
     * 중위 순회 + 내림차순
     * @param node
     * @param count
     * @return
     */
    public Player[] getTopCntTraverseInOrderDesc(final TreeNode node, int count) {
        if (node == null) {
            return null;
        }

        Player[] players = new Player[count];

        int indexCnt = 0;

        TreeNode current = node;

        Stack<TreeNode> nodes = new Stack<>();

        while (!nodes.empty() || current != null) {
            if (indexCnt >= count) {
                break;
            }

            if (current != null) {
                nodes.push(current);
                current = current.getRight();
            } else {
                current = nodes.pop();

                players[indexCnt] = current.getPlayer();
                ++indexCnt;

                current = current.getLeft();
            }
        }

        return players;
    }

    public Player[] getBottomCntTraverseInOrder(final TreeNode node, int count) {
        if (node == null) {
            return null;
        }

        Player[] players = new Player[count];

        int indexCnt = 0;

        TreeNode current = node;

        Stack<TreeNode> nodes = new Stack<>();

        while (!nodes.empty() || current != null) {
            if (indexCnt >= count) {
                break;
            }

            if (current != null) {
                nodes.push(current);
                current = current.getLeft();
            } else {
                current = nodes.pop();

                players[indexCnt] = current.getPlayer();
                ++indexCnt;

                current = current.getRight();
            }
        }
        return players;
    }

    // 중위 순회로 parentNode 의 susNode 까지 리스트 조회
    public List<TreeNode> getTreeNodesTraverseInOrder(final TreeNode node, final TreeNode parentNode) {
        if (node == null) {
            return null;
        }

        List<TreeNode> players = new ArrayList<>();

        boolean isParentSucNode = false;

        TreeNode current = node;

        Stack<TreeNode> nodes = new Stack<>();

        while (!nodes.empty() || current != null) {
            if (current != null) {
                nodes.push(current);
                current = current.getLeft();
            } else {
                current = nodes.pop();

                players.add(current);

                if (isParentSucNode) {
                    break;
                }

                if (current.getPlayer().getRating() == parentNode.getPlayer().getRating()) {
                    isParentSucNode = true;
                }

                current = current.getRight();
            }
        }
        return players;
    }

    public Map<String, TreeNode> getPreAndSucNode(List<TreeNode> nodes, TreeNode parentNode) {
        boolean isParentPreNode = false;

        TreeNode findNodeLeftMaxNode = null;
        TreeNode findNodeRightMinNode = null;

        for (int index = nodes.size() - 1; index >= 0; index--) {
            if (isParentPreNode) {
                findNodeLeftMaxNode = nodes.get(index);
                break;
            }

            if (index == nodes.size() -1) {
                if (nodes.get(index).getPlayer().getRating() != parentNode.getPlayer().getRating()) {
                    findNodeRightMinNode = nodes.get(index);
                }
            }

            if (nodes.get(index).getPlayer().getRating() == parentNode.getPlayer().getRating()) {
                isParentPreNode = true;
            }
        }

        Map<String, TreeNode> resultMap = new HashMap<>();

        resultMap.put("findNodeLeftMaxNode", findNodeLeftMaxNode);
        resultMap.put("findNodeRightMinNode", findNodeRightMinNode);

        return resultMap;
    }

    public TreeNode delete(TreeNode root, int data) {
        if (root == null) {
            return root;
        }

        if (root.getPlayer().getRating() > data) {
            root.setLeft(delete(root.getLeft(), data));
        } else if (root.getPlayer().getRating() < data) {
            root.setRight(delete(root.getRight(), data));
            return root;
        }


        // If One of the children is empty
        if (root.getLeft() == null) {
            TreeNode temp = root.getRight();
            return temp;
        } else if (root.getRight() == null) {
            TreeNode temp = root.getLeft();
            return temp;
        }

        // If both children exist
        else {
            TreeNode succParent = root;

            TreeNode succ = root.getRight();
            // Find successor
            while (succ.getLeft() != null) {
                succParent = succ;
                succ = succ.getLeft();
            }

            if (succParent != root) {
                succParent.setLeft(succ.getRight());
            } else {
                succParent.setRight(succ.getRight());
            }

            root.setPlayer(succ.getPlayer());

            return root;
        }
    }


}