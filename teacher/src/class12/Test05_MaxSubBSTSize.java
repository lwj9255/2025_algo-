package class12;

public class Test05_MaxSubBSTSize {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value) {
            val = value;
        }
    }

    public static int maxSubBSTSize(TreeNode node){
        if(node == null){
            return 0;
        }
        return process(node).maxsub;
    }

    public static class Info{
        public int max;
        public int min;
        public int maxsub;
        public int size;

        public Info(int max, int min, int maxsub, int size) {
            this.max = max;
            this.min = min;
            this.maxsub = maxsub;
            this.size = size;
        }
    }

    public static Info process(TreeNode node){
        if(node == null){
            return null;
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int max = node.val;
        int min = node.val;
        int maxsub = 0;
        int size = 1;
        if(leftInfo!=null){
            max = Math.max(max,leftInfo.max);
            min = Math.min(min,leftInfo.min);
            maxsub = Math.max(maxsub, leftInfo.maxsub);
            size += leftInfo.size;
        }
        if(rightInfo!=null){
            max = Math.max(max,rightInfo.max);
            min = Math.min(min,rightInfo.min);
            maxsub = Math.max(maxsub, rightInfo.maxsub);
            size += rightInfo.size;
        }

        boolean isBST = true;
        if(leftInfo!=null){
            if(leftInfo.maxsub!= leftInfo.size||node.val<= leftInfo.max){
                isBST = false;
            }
        }
        if(rightInfo!=null){
            if(rightInfo.maxsub!= rightInfo.size||node.val>= rightInfo.min){
                isBST = false;
            }
        }

        if(isBST){
            maxsub = size;
        }

        return new Info(max,min,maxsub,size);
    }
}
