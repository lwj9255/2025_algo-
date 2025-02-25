package class02;

public class Code02_EvenTimesOddTimes {

    // 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，找到并打印这种数
    public static void printOddTimesNum1(int[] arr) {
        int eor = 0;
        for(int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println("奇数次数的为："+eor);
    }

    // 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，找到并打印这种数
    public static void printOddTimesNum2(int[] arr) {
        int eor1 = 0;
        for (int i = 0; i < arr.length; i++) {
            eor1 ^= arr[i];
        }
        // a 和 b是两种数
        // eor != 0
        // eor最右侧的1，提取出来
        // eor :     00110010110111000
        //-eor :     11001101001001000 取负等于反码加一
        // rightOne :00000000000001000
        int rightOne = eor1 & (-eor1); // 提取出最右的1

        int eor2 = 0; // eor'
        for (int i = 0; i < arr.length; i++) {
            //  arr[1] =  111100011110000
            // rightOne=  000000000010000
            if ((arr[i] & rightOne) != 0) {
                eor2 ^= arr[i];
            }
        }
        System.out.println("第一个数为："+eor2 + "，第二个数为：" + (eor1 ^ eor2));
    }


    // 求一个数位置上有多少个1
    public static int bit1counts(int N) {
        int count = 0;

        //   N:011011010000
        //  -N:100100110000
        //   R:000000010000
        // N^R:011011000000
        while (N != 0) {
            int rightOne = N & ((~N) + 1);
            count++;
            N ^= rightOne;
            // N -= rightOne
        }
        return count;
    }


    public static void main(String[] args) {

        int[] arr1 = {3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1};
        printOddTimesNum1(arr1);

        int[] arr2 = {4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2};
        printOddTimesNum2(arr2);

    }

}
