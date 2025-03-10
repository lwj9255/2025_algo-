package class11;

import java.util.ArrayList;
import java.util.List;

// 本题测试链接：https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree
public class Code03_EncodeNaryTreeToBinaryTree {

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
	};

	// 二叉树节点
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	class EncodeNaryTreeToBinaryTree {

		// 将N叉树节点变成二叉树节点
		public TreeNode encode(Node node) {
			if (node == null) {
				return null;
			}
			TreeNode t = new TreeNode(node.val);
			// 头节点的孩子节点也要变成二叉树
			t.left = en(node.children);
			return t;
		}

		// N叉树节点的孩子节点中，最左侧的一个作为左孩子，剩余的作为该左孩子的右孩子
		private TreeNode en(List<Node> children) {
			TreeNode head = null;
			TreeNode cur = null;
			for (Node child : children) {
				TreeNode tNode = new TreeNode(child.val);
				// 第一个孩子作为头节点
				if (head == null) {
					head = tNode;
				} else {
					cur.right = tNode; // 剩余节点作为右孩子
				}
				cur = tNode;
				// 如果该节点还有孩子节点，递归
				cur.left = en(child.children);
			}
			return head;
		}

		// 二叉树变成N叉树
		public Node decode(TreeNode root) {
			if (root == null) {
				return null;
			}
			return new Node(root.val, de(root.left));
		}

		public List<Node> de(TreeNode root) {
			List<Node> children = new ArrayList<>();
			// 如果为空则直接返回 base case
			while (root != null) {
				// 如果不为空则递归调用，直到该节点的左孩子
				Node cur = new Node(root.val, de(root.left));
				children.add(cur);
				root = root.right;
			}
			return children;
		}

	}

}
