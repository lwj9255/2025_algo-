package class09;

public class Test_CopyListWithRandom {
    public static class Node {
        int val;
        Node next;
        Node random;

        Node(int _val) {
            val = _val;
            next = null;
            random = null;
        }
    }

    public static Node copyListWithRandom(Node head) {
        if (head == null) {
            return null;
        }
        Node next = null;
        Node n = head;
        while (n != null) {
            next = n.next;
            Node newNode = new Node(n.val);
            n.next = newNode;
            newNode.next = next;
            n = next;
        }
        Node t = null;
        n = head;
        // 先设置好复制节点的所有随机指针
        while (n != null) {
            t = n.next;
            next = t.next;
            if (n.random != null) {
                t.random = n.random.next;
            } else {
                t.random = null;
            }
            n = next;
        }

        n = head;
        Node ans = head.next;
        while (n != null) {
            t = n.next;
            next = t.next;

            n.next = next;
            if (next != null) {
                t.next = next.next;
            } else {
                t.next = null;
            }
            n = next;
        }
        return ans;
    }
}
