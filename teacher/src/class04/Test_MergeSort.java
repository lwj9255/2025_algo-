package class04;

import java.util.Arrays;

public class Test_MergeSort {
    // 递归实现
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int L, int R) {
        // 递归出口 base case
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int p1 = l;
        int p2 = mid + 1;
        int i = 0;
        while (p1 <= mid && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l++] = help[i];
        }
    }

    // 非递归实现
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        int mergeSize = 1;//步长
        while (mergeSize < N) {
            int L = 0;
            while (L < N) {
                if (L + mergeSize >= N) {
                    break;
                }
                int M = L + mergeSize - 1;
                int R = Math.min(N - 1, M + mergeSize);
                merge(arr, L, M, R);
                L = R + 1;
            }
            // 防止溢出
            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }

    public static void mergeSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int N = arr.length;
        int mergeSize = 1;
        while (mergeSize < N) {
            int L = 0;
            while (L < N) {
                if (L + mergeSize >= N) {
                    break;
                }
                int M = L + mergeSize - 1;
                int R = Math.min(M + mergeSize, N - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
            if (mergeSize > N / 2) {
                return;
            }
            mergeSize <<= 1;
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
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < 10000; i++) {
            int[] arr1 = randomArr(maxSzie, maxValue);
            int[] arr2 = copyArr(arr1);
            mergeSort1(arr1);
            mergeSort3(arr2);
            if (!arrIsEqual(arr1, arr2)) {
                succeed = false;
                printArr(arr1);
                printArr(arr2);
                break;
            }
        }
        System.out.println(succeed ? "成功" : "失败");
        int[] arr1 = randomArr(maxSzie, maxValue);
        int[] arr2 = copyArr(arr1);
        printArr(arr1);
        printArr(arr2);
        mergeSort1(arr1);
        mergeSort3(arr2);
        printArr(arr1);
        printArr(arr2);
    }
}
