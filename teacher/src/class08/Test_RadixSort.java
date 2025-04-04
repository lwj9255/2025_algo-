package class08;

import java.util.Arrays;

public class Test_RadixSort {
    public static void radixSort(int[] arr){
        if(arr == null|| arr.length < 2){
            return;
        }
        radixSort(arr,0,arr.length-1,maxbits(arr));
    }

    private static void radixSort(int[] arr, int L, int R, int maxbits) {
        int[] help = new int[R-L+1];

        for(int d =1;d<=maxbits;d++){
            int[] count = new int[10];
            for(int i =L;i<=R;i++){
                int index = (arr[i]/(int)Math.pow(10,d-1))%10;
                count[index]++;
            }
            for(int i =1;i<10;i++){
                count[i] = count[i]+count[i-1];
            }
            for(int j =R;j>=L;j--){
                int index = (arr[j]/(int)Math.pow(10,d-1))%10;
                help[count[index]-1] = arr[j];
                count[index]--;
            }
            for(int i =0,j=L;i<help.length;i++){
                arr[j++] = help[i];
            }
        }
    }

    public static int maxbits(int[] arr){
        int max = Integer.MIN_VALUE;
        for(int i =0;i<arr.length;i++){
            max = Math.max(max,arr[i]);
        }
        int ans = 0;
        while(max!=0){
            ans++;
            max /=10;
        }
        return ans;
    }

    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
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
        int maxSize = 1000;
        int maxValue = 10000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            radixSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        radixSort(arr);
        printArray(arr);

    }
}
