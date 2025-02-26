package class04;

public class Test_SmallSum {
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int L, int R) {
        // base case
        if (L == R) {
            return 0;
        }
        int M = L + ((R - L) >> 1);
        return process(arr, L, M) + process(arr, M + 1, R) + merge(arr, L, M, R);
    }

    private static int merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int res = 0;
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r) {
            res += arr[p1] < arr[p2] ? arr[p1] * (r - p2 + 1) : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l++] = help[i];
        }
        return res;
    }

    public static int compare(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
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
        int maxSzie = 10;
        int maxValue = 10;
        boolean succeed = true;
        for (int i = 0; i < 10000; i++) {
            int[] arr1 = randomArr(maxSzie, maxValue);
            int[] arr2 = copyArr(arr1);
//            int a = smallSum(arr1);
//            int b = compare(arr1);
            if (smallSum(arr1) != compare(arr2)) {
                succeed = false;
                printArr(arr1);
                System.out.println(smallSum(arr1) + "|" + compare(arr2));
                break;
            }
        }
        System.out.println(succeed ? "成功" : "失败");
        int[] arr1 = randomArr(maxSzie, maxValue);
        int[] arr2 = copyArr(arr1);
        printArr(arr1);

        System.out.println(smallSum(arr1) + "|" + compare(arr2));
    }
}
