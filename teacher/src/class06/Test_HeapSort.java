package class06;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Test_HeapSort {
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // nlogn，新来的数放最后，从下往上排序
        for(int i = 1;i< arr.length;i++){
            heapinsert(arr,i);
        }
        // n，从末尾开始排序
//        for(int i = arr.length-1;i>=0;i--){
//            heapify(arr,arr.length,i);
//        }
        int heapsize = arr.length;
        swap(arr,0,--heapsize);
        while(heapsize>0){
            heapify(arr,heapsize,0);
            swap(arr,0,--heapsize);
        }


    }

    public static void heapify(int[] arr, int heapsize, int index) {
        int left = index * 2 + 1;
        while (left < heapsize) {
            int max = left + 1 < heapsize ? (arr[left] > arr[left +1] ? left : left+1) : left;
            max = arr[max] > arr[index] ? max : index;
            if(max == index){
                break;
            }
            swap(arr,index,max);
            index = max;
            left = index * 2 + 1;
        }
    }

    public static void heapinsert(int[] arr,int index) {
        while (arr[index] > arr[(index-1 )/ 2 ]) {
            swap(arr,index,(index-1 )/ 2 );
            index = (index-1 )/ 2;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
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
            heapSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "failed");

        int[] arr = generateRandomArray(maxSize, maxValue);
        int[] arr1 = generateRandomArray(maxSize, maxValue);
        int[] arr2 = copyArray(arr1);
        heapSort(arr1);
        printArray(arr1);
        comparator(arr2);
        printArray(arr2);
    }


}
