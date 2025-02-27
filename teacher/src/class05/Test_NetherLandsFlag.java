package class05;

public class Test_NetherLandsFlag {
    public static int[] netherLandsFlag(int[] nums, int L, int R){
        if(L>R){
            return new int[]{-1,-1};
        }
        if(L == R){
            return new int[]{L,R};
        }
        int less = L-1;
        int more = R;
        int index = L;
        while(index < more){
            if(nums[index] < nums[R]){
                swap(nums,index++,++less);
            }else if(nums[index] > nums[R]){
                swap(nums,index,--more);
            }else{
                index++;
            }
        }
        swap(nums,index,R);
        return new int[]{less+1,more};
    }

    public static void swap(int[] nums,int i,int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public static int[] compare(int[] nums, int L, int R){
        if(L>R){
            return new int[]{-1,-1};
        }
        if(L == R){
            return new int[]{L,R};
        }
        int less = 0;
        int equal = 0;
        for(int i = L;i<=R;i++){
            if(nums[i]< nums[R]){
                less++;
            }else if(nums[i] == nums[R]){
                equal++;
            }
        }
        return new int[]{L+less,L+less+equal-1};
    }


    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] ans1 = netherLandsFlag(arr1,0,arr1.length-1);
            int[] ans2 = compare(arr2,0,arr1.length-1);
            for(int j = 0;j<2;j++){
                if(ans1[j]!=ans2[j]){
                    succeed = false;
                    break;
                }
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");

    }
}
