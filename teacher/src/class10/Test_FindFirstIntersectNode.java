package class10;

public class Test_FindFirstIntersectNode {
    public static class Node {
        public int value;
        public Node next;

        public Node(int v) {
            value = v;
        }
    }

    public static Node findFirstIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        if (getLoopNode(head1) == null && getLoopNode(head2) == null) {
            return noLoop(head1, head2);
        } else if(getLoopNode(head1)!=null&&getLoopNode(head2)!=null) {
            return bothLoop(head1, getLoopNode(head1), head2, getLoopNode(head2));
        }
        return null;
    }


    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node fast = head.next.next;
        Node slow = head.next;
        while (fast != slow) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    private static Node noLoop(Node head1, Node head2) {
        int n = 0;
        Node n1 = head1;
        Node n2 = head2;
        while (n1.next != null) {
            n1 = n1.next;
            n--;
        }
        while (n2.next != null) {
            n2 = n2.next;
            n++;
        }
        if(n1!=n2){
            return null;
        }
        n1 = n > 0 ? head2 : head1;
        n2 = n1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while(n!=0){
            n1=n1.next;
            n--;
        }
        while (n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }

    private static Node bothLoop(Node head1, Node loopNode1, Node head2, Node loopNode2) {
        if (loopNode1 == loopNode2) {
            Node n1 = head1;
            Node n2 = head2;
            int n = 0;
            while (n1 != loopNode1) {
                n--;
                n1 = n1.next;
            }
            while (n2 != loopNode1) {
                n++;
                n2 = n2.next;
            }
            n1 = n > 0 ? head2 : head1;
            n2 = n1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n1 = n1.next;
                n--;
            }
            while (n1 != n2) {
                n1 = n1.next;
                n2 = n2.next;
            }
            return n1;
        } else {
            Node n1 = loopNode1.next;
            while (n1 != loopNode1) {
                if (n1 == loopNode2) {
                    return loopNode1;
                }
                n1 = n1.next;
            }
            return null;
        }
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(findFirstIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(findFirstIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(findFirstIntersectNode(head1, head2).value);

    }

}
