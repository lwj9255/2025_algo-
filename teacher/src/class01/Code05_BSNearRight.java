package class01;

import java.util.Arrays;

import static A_utils.CommonUtils.*;
import static A_utils.ArrayUtils.*;

public class Code05_BSNearRight {

	// 在arr上，找满足<=value的最右位置
	public static int nearestIndex(int[] arr, int value) {
		int L = 0;
		int R = arr.length - 1;
		int index = -1; // 记录最右的对号
		while (L <= R) {
			int mid = L + ((R - L) >> 1);
			if (arr[mid] <= value) {
				index = mid;
				L = mid + 1;
			} else {
				R = mid - 1;
			}
		}
		return index;
	}


	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 10;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			Arrays.sort(arr);
			int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
			if (numIndexInArr(arr, value) != nearestIndex(arr, value)) {
				printArray(arr);
				System.out.println(value);
				System.out.println(numIndexInArr(arr, value));
				System.out.println(nearestIndex(arr, value));
				succeed = false;
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}

}
