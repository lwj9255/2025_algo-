package class11;

import java.util.ArrayList;
import java.util.List;

public class Test_EncodeNaryTreeToBinaryTree {
    // N叉树节点
    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;

    // 二叉树节点
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static class EncodeNaryTreeToBinaryTree {
        // N叉树变成二叉树
        public static TreeNode enCode(Node node) {
            if (node == null) {
                return null;
            }
            TreeNode t = new TreeNode(node.val);
            t.left = en(node.children);
            return t;
        }

        private static TreeNode en(List<Node> children) {
            TreeNode head = null;
            TreeNode cur = null;
            for (Node child : children) {
                TreeNode tNode = new TreeNode(child.val);
                if (head == null) {
                    head = tNode;
                } else {
                    cur.right = tNode;
                }
                cur = tNode;
                cur.left = en(child.children);
            }
            return head;
        }

        public static Node decode(TreeNode tNode) {
            if (tNode == null) {
                return null;
            }
            // 处理当前节点的左节点，获得当前N叉树节点的子节点列表
            return new Node(tNode.val, de(tNode.left));
        }

        private static List<Node> de(TreeNode tNode) {
            List<Node> children = new ArrayList<>();
            while (tNode != null) {
                Node node = new Node(tNode.val, de(tNode.left));
                children.add(node);
                tNode = tNode.right;
            }
            return children;
        }
    }
}
