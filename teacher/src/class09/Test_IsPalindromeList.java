package class09;

public class Test_IsPalindromeList {

    public static class Node {
        public int value;
        public Node next;

        public Node(int v) {
            value = v;
        }
    }

    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node fast = head;
        Node slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next.next;
        }
        Node n1 = slow;
        Node n2 = n1.next;
        n1.next = null;
        Node n3 = null;
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        Node last = n1;
        Node r = n1;
        Node l = head;
        boolean ans = true;
        while (r != null && l != null) {
            if (r.value != l.value) {
                ans = false;
                break;
            }
            r = r.next;
            l = l.next;
        }
        n1 = last.next;
        while (n1 != null) {
            n2 = n1.next;
            n1.next = last;
            last = n1;
            n1 = n2;
        }
        return ans;
    }

    public static void main(String[] args) {
        Node head = null;
        head = new Node(0);
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(3);
        head.next.next.next.next = new Node(4);
        head.next.next.next.next.next = new Node(4);
        head.next.next.next.next.next.next = new Node(3);
        head.next.next.next.next.next.next.next = new Node(2);
        head.next.next.next.next.next.next.next.next = new Node(1);
        head.next.next.next.next.next.next.next.next.next = new Node(0);

        System.out.println(isPalindrome(head));

        System.out.println("=========================");

    }
}
