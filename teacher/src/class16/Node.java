package class16;

import java.util.ArrayList;

// 点结构的描述
public class Node {
	public int value;//点的值
	public int in;// 入度，多少个点指向这个点
	public int out;// 出度，这个点指向多少个点
	public ArrayList<Node> nexts;// 邻居，也就是指向了哪些点
	public ArrayList<Edge> edges;// 边，指向点的边

	public Node(int value) {
		this.value = value;
		in = 0;
		out = 0;
		nexts = new ArrayList<>();
		edges = new ArrayList<>();
	}
}
