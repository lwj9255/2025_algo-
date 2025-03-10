package class11;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Test_TreeMaxWidth {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static int maxWidthUseMap(Node head){
        if(head == null){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        HashMap<Node,Integer> map = new HashMap<>();
        queue.add(head);
        map.put(head,1);
        int curlevel = 1;
        int max = 0;
        int width = 0;
        while(!queue.isEmpty()){
            Node curNode = queue.poll();
            int nodelevel = map.get(curNode);
            if(curNode.left!=null){
                map.put(curNode.left,nodelevel+1);
                queue.add(curNode.left);
            }
            if(curNode.right!=null){
                map.put(curNode.right,nodelevel+1);
                queue.add(curNode.right);
            }
            if(nodelevel == curlevel){
                width++;
            }else{
                max = Math.max(width,max);
                curlevel++;
                width = 1;
            }
        }
        max = Math.max(width,max);
        return max;
    }

    public static int maxWidthNoMap(Node head){
        if(head == null){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curEnd = head;
        Node nextEnd = null;
        int max = 0;
        int width = 0;
        while(!queue.isEmpty()){
            Node curNode = queue.poll();
            if(curNode.left!=null){
                queue.add(curNode.left);
                nextEnd = curNode.left;
            }
            if(curNode.right!=null){
                queue.add(curNode.right);
                nextEnd = curNode.right;
            }
            width++;
            if(curEnd == curNode){
                max = Math.max(max,width);
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
