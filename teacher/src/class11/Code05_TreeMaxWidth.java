package class11;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Code05_TreeMaxWidth {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	// 用map实现
	public static int maxWidthUseMap(Node head) {
		if (head == null) {
			return 0;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.add(head);
		// key 在 哪一层，value
		HashMap<Node, Integer> map = new HashMap<>();
		map.put(head, 1);
		int curLevel = 1; // 当前你正在统计哪一层的宽度
		int width = 0; // 当前层curLevel层，宽度目前是多少
		int max = 0;// 记录最大值
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			int nodeLevel = map.get(cur);// 查看现在遍历到的节点的层
			if (cur.left != null) {
				map.put(cur.left, nodeLevel + 1);
				queue.add(cur.left);
			}
			if (cur.right != null) {
				map.put(cur.right, nodeLevel + 1);
				queue.add(cur.right);
			}
			// 如果遍历到的节点就在当前统计的层
			if (nodeLevel == curLevel) {
				width++;
				// 如果不在，那说明之前的层已经统计完了
			} else {
				// 更新一下max的值
				max = Math.max(max, width);
				// 统计的层+1
				curLevel++;
				// 宽度重设为1
				width = 1;
			}
		}
		max = Math.max(max, width);
		return max;
	}

	public static int maxWidthNoMap(Node head) {
		if (head == null) {
			return 0;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.add(head);
		Node curEnd = head; // 当前层，最右节点是谁
		Node nextEnd = null; // 下一层，最右节点是谁
		int max = 0;
		int width = 0; // 当前层的节点数
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			if (cur.left != null) {
				queue.add(cur.left);
				nextEnd = cur.left;
			}
			if (cur.right != null) {
				queue.add(cur.right);
				nextEnd = cur.right;
			}
			width++;
			if (cur == curEnd) {
				max = Math.max(max, width);
				width = 0;
				curEnd = nextEnd;
			}
		}
		return max;
	}

	// for test
	public static Node generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}

	// for test
	public static Node generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.5) {
			return null;
		}
		Node head = new Node((int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		return head;
	}

	public static void main(String[] args) {
		int maxLevel = 10;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			if (maxWidthUseMap(head) != maxWidthNoMap(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");

	}

}
