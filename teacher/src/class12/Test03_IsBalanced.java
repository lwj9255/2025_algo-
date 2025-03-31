package class12;

public class Test03_IsBalanced {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isBalanced(Node node){
        if(node == null){
            return true;
        }
        return process(node).isbalanced;
    }

    public static class Info{
        public boolean isbalanced;
        public int height;

        public Info(boolean isbalanced, int height) {
            this.isbalanced = isbalanced;
            this.height = height;
        }
    }

    public static Info process(Node node){
        if(node == null){
            return new Info(true,0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        int height = Math.max(leftInfo.height, rightInfo.height)+1;
        boolean isbalanced = false;
        if(leftInfo.isbalanced&&rightInfo.isbalanced&&Math.abs(leftInfo.height- rightInfo.height)<=1){
            isbalanced=true;
        }
        return new Info(isbalanced,height);
    }

    public static boolean isBalanced2(Node head) {
        return process2(head).isBalanced;
    }

    public static class Info2{
        public boolean isBalanced;
        public int height;

        public Info2(boolean i, int h) {
            isBalanced = i;
            height = h;
        }
    }

    public static Info2 process2(Node x) {
        if(x == null) {
            return new Info2(true, 0);
        }
        Info2 leftInfo = process2(x.left);
        Info2 rightInfo = process2(x.right);
        int height = Math.max(leftInfo.height, rightInfo.height)  + 1;
        boolean isBalanced = true;
        if(!leftInfo.isBalanced) {
            isBalanced = false;
        }
        if(!rightInfo.isBalanced) {
            isBalanced = false;
        }
        if(Math.abs(leftInfo.height - rightInfo.height) > 1) {
            isBalanced = false;
        }
        return new Info2(isBalanced, height);
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
            if (isBalanced(head) != isBalanced2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
