package class01;

import java.util.Arrays;

public class Test_BubbleSort {
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                }
            }
        }
    }

    public static int[] randomArr(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];// 1-maxvalue
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static int[] copyArr(int[] arr) {
        int[] newarr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newarr[i] = arr[i];
        }
        return newarr;
    }

    public static boolean arrIsEqual(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length)
            return false;
        if (arr1 == null && arr2 != null || arr1 != null && arr2 == null)
            return false;
        if (arr1 == null && arr2 == null)
            return true;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i])
                return false;
        }
        return true;
    }

    public static void printArr(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxSzie = 1000;
        int maxValue = 500;
        boolean succeed = true;
        for (int i = 0; i < 10000; i++) {
            int[] arr1 = randomArr(maxSzie, maxValue);
            int[] arr2 = copyArr(arr1);
            bubbleSort(arr1);
            Arrays.sort(arr2);
            if (!arrIsEqual(arr1, arr2)) {
                succeed = false;
                System.out.print("arr1:");
                printArr(arr1);
                printArr(arr2);
                break;
            }
        }
        System.out.println(succeed ? "成功" : "失败");
        int[] arr1 = randomArr(maxSzie, maxValue);
        int[] arr2 = copyArr(arr1);
        bubbleSort(arr1);
        Arrays.sort(arr2);
        printArr(arr1);
        printArr(arr2);
    }
}
