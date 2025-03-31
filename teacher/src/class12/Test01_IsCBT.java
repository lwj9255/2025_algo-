package class12;

import java.util.LinkedList;

public class Test01_IsCBT {
    public static class Node{
        public int value;
        public Node right;
        public Node left;

        public Node(int value){
            this.value = value;
        }
    }

    public static boolean isCBT(Node node){
        if(node == null){
            return true;
        }
        return process(node).isCBT;
    }

    public static class Info{
        public boolean isCBT;
        public int height;
        public boolean isFull;

        public Info(boolean isCBT,int height,boolean isFull){
            this.isCBT = isCBT;
            this.height = height;
            this.isFull = isFull;
        }
    }

    public static Info process(Node node){
        if(node == null){
            return new Info(true,0,true);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        int height = Math.max(leftInfo.height,rightInfo.height)+1;

        boolean isFull = false;
        if(rightInfo.isFull&&leftInfo.isFull&&rightInfo.height==leftInfo.height){
            isFull = true;
        }

        boolean isCBT = false;
        if(isFull){
            isCBT = true;
        }else{
            if(leftInfo.isCBT&&rightInfo.isCBT){
                if(leftInfo.isCBT&&rightInfo.isFull&&leftInfo.height- rightInfo.height==1){
                    isCBT = true;
                }
                if(leftInfo.isFull&&rightInfo.isFull&&leftInfo.height- rightInfo.height==1){
                    isCBT = true;
                }
                if(leftInfo.isFull&&rightInfo.isCBT&&leftInfo.height== rightInfo.height){
                    isCBT = true;
                }
            }
        }
        return new Info(isCBT,height,isFull);
    }

    public static boolean isCBT1(Node head) {
        if (head == null) {
            return true;
        }
        LinkedList<Node> queue = new LinkedList<>();
        // 是否遇到过左右两个孩子不双全的节点
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if (
                // 如果遇到了不双全的节点之后，又发现当前节点不是叶节点
                    (leaf && (l != null || r != null))
                            ||
                            (l == null && r != null)

            ) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
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
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isCBT(head) != isCBT1(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
