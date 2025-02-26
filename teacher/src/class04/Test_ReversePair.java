package class04;

public class Test_ReversePair {
    public static int reversePair(int[] arr) {
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
        int i = 0;
        int p1 = m;
        int p2 = r;
        int res = 0;
        while(p1>=l&&p2>m){
            if(arr[p1] > arr[p2]){
                res += (p2-m);
                p1--;
            }else{
                p2--;
            }
        }
        p1 = l;
        p2 = m+1;
        while(p1<=m&&p2<=r){
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while(p1<=m){
            help[i++] = arr[p1++];
        }
        while(p2<=r){
            help[i++] = arr[p2++];
        }
        for(i = 0;i<help.length;i++){
            arr[l++] = help[i];
        }
        return res;
    }
}
