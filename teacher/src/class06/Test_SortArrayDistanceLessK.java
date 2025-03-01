package class06;

import java.util.PriorityQueue;

public class Test_SortArrayDistanceLessK {
    public static void sortedArrDistanceLessK(int[] arr, int k) {
        if (arr == null || arr.length < 2) {
            return;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int index = 0;
        for (; index < ((arr.length > k) ? k - 1 : arr.length - 1); index++) {
            minHeap.add(arr[index]);
        }

        for(int i = 0;index<arr.length-1;i++,index++){
            minHeap.add(arr[index]);
            arr[i] = minHeap.poll();
        }
    }


}
