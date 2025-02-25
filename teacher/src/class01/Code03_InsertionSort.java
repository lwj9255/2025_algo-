package class01;

import java.util.Arrays;
import static A_utils.ArrayUtils.*;

public class Code03_InsertionSort {

	public static void insertionSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		// 不只1个数
		for (int i = 1; i < arr.length; i++) { // 0 ~ i 做到有序
			for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
				swap(arr, j, j + 1);
			}
		}
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;// 测试次数
		int maxSize = 10;// 随机数组最大size
		int maxValue = 100;// 随机数组中value的最大值
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);// 每轮都随机生成一个数组
			int[] arr1 = copyArray(arr);// 复制
			int[] arr2 = copyArray(arr);// 复制
			insertionSort(arr1);// 用自己的方法排序
			Arrays.sort(arr2);// 用官方方法排序
			if (!isEqual(arr1, arr2)) {// 如果不相等
				printArray(arr);// 打印arr
				printArray(arr1);// 打印arr1
				printArray(arr2);// 打印arr2
				succeed = false;
				System.out.println();
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
		// 测试成功，展示一个
		int[] arr = generateRandomArray(maxSize, maxValue);
		printArray(arr);
		insertionSort(arr);
		printArray(arr);
	}


}
