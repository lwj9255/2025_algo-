package class05;

public class Test_CountOfRangeSum {
    public static int countOfRangeSum(int[] arr, int lower, int upper) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        long[] presum = new long[arr.length];
        presum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            presum[i] = presum[i - 1] + arr[i];
        }
        return process(presum, 0, arr.length - 1, lower, upper);
    }

    private static int process(long[] presum, int l, int r, int lower, int upper) {
        // base case
        // 如果左等于右，说明组内只有一个数，这个数是前缀和，代表了0-l的数的和，如果在范围内，那么对结果加一
        if (l == r) {
            if (presum[l] >= lower && presum[l] <= upper) {
                return 1;
            }
        } else {
            return 0;
        }

        int m = l + ((r - l) >> 1);
        return process(presum, l, m, lower, upper)
                + process(presum, m + 1, r, lower, upper)
                + merge(presum, l, m, r, lower, upper);
    }

    private static int merge(long[] presum, int l, int m, int r, int lower, int upper) {
        long[] help = new long[presum.length];
        int res = 0;
        // LR为窗口左右边界，目的是从左组第一个开始向右滑动
        int L = l;
        int R = l;
        // p指向右组第一个，目的是从右组第一个开始比较左组有多少个符合它的区间
        for (int p = m + 1; p <= r; p++) {
            // 由于右组已经被排序过了，因此presum[p]不一定是0-p的数的和
            // 但是可以确定的是，presum[p]一定是0-x的数的和，x＞所有左组的下标
            // 左组中的每个值都代表0-y的值
            // 假设presum[p]的值是X，左组中有个值是Y，是0-y-1的数的和，那么只要X-Y落在[lower,upper]，就说明y-x的数的和是符合的
            // 也就是Y要落在[X-lower,X-upper]上
            long max = presum[p] - lower;
            long min = presum[p] - upper;
            while (presum[R] >= max) {
                R++;
            }
            while (presum[L] < min) {
                L++;
            }
            res += R - L;
        }

        int p1 = l;
        int p2 = m + 1;
        int i = 0;
        while (p1 <= m && p2 <= r) {
            help[i++] = presum[p1] < presum[p2] ? presum[p1++] : presum[p2++];
        }
        while (p1 <= m) {
            help[i++] = presum[p1++];
        }
        while (p2 <= r) {
            help[i++] = presum[p2++];
        }
        for (i = 0; i < help.length; i++) {
            presum[l++] = help[i];
        }
        return res;

    }


}
