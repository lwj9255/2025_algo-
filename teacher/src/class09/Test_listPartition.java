package class09;

public class Test_listPartition {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node listPartition1(Node head, int v) {
        if (head == null || head.next == null) {
            return head;
        }
        Node sh = null;
        Node st = null;
        Node eh = null;
        Node et = null;
        Node lh = null;
        Node lt = null;
        Node next = null;
        while (head != null) {
            next=head.next;
            if (head.value < v) {
                if (sh == null & st == null) {
                    sh = head;
                    st = head;
                }
                st.next = head;
                st = head;
            } else if (head.value == v) {
                if (eh == null && et == null) {
                    eh = head;
                    et = head;
                }
                et.next = head;
                et = head;
            } else {
                if (lh == null && lt == null) {
                    lh = head;
                    lt = head;
                }
                lt.next = head;
                lt = head;
            }
            head = next;
        }
        if (sh == null) {
            if (eh != null) {
                head = eh;
                if (lh != null) {
                    et.next = lh;
                    lt.next = null;
                    return head;
                }
            } else {
                head = lh;
                lt.next = null;
                return head;
            }
        } else {
            head = sh;
            if (eh != null) {
                st.next = eh;
                if (lh != null) {
                    et.next = lh;
                    lt.next = null;
                    return head;
                } else {
                    et.next = null;
                    return head;
                }
            } else {
                if (lh != null) {
                    st.next = lh;
                    lt.next = null;
                    return head;
                }else{
                    st.next = null;
                    return head;
                }
            }
        }
        return head;
    }

    public static Node listPartition2(Node head, int pivot) {
        Node sH = null; // small head
        Node sT = null; // small tail
        Node eH = null; // equal head
        Node eT = null; // equal tail
        Node mH = null; // big head
        Node mT = null; // big tail
        Node next = null; // save next node
        // every node distributed to three lists
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else if (head.value == pivot) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            } else {
                if (mH == null) {
                    mH = head;
                    mT = head;
                } else {
                    mT.next = head;
                    mT = head;
                }
            }
            head = next;
        }
        // 小于区域的尾巴，连等于区域的头，等于区域的尾巴连大于区域的头
        if (sT != null) { // 如果有小于区域
            sT.next = eH;
            eT = eT == null ? sT : eT; // 下一步，谁去连大于区域的头，谁就变成eT
        }
        // 下一步，一定是需要用eT 去接 大于区域的头
        // 有等于区域，eT -> 等于区域的尾结点
        // 无等于区域，eT -> 小于区域的尾结点
        // eT 尽量不为空的尾巴节点
        if (eT != null) { // 如果小于区域和等于区域，不是都没有
            eT.next = mH;
        }
        return sH != null ? sH : (eH != null ? eH : mH);
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        head1 = listPartition1(head1, 5);
        printLinkedList(head1);
    }
}
