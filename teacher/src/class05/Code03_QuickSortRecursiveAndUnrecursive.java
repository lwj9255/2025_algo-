package class05;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code03_QuickSortRecursiveAndUnrecursive {

	// 荷兰国旗问题
	public static int[] netherlandsFlag(int[] arr, int L, int R) {
		if (L > R) {
			return new int[] { -1, -1 };
		}
		if (L == R) {
			return new int[] { L, R };
		}
		int less = L - 1;
		int more = R;
		int index = L;
		while (index < more) {
			if (arr[index] == arr[R]) {
				index++;
			} else if (arr[index] < arr[R]) {
				swap(arr, index++, ++less);
			} else {
				swap(arr, index, --more);
			}
		}
		swap(arr, more, R);
		return new int[] { less + 1, more };
	}

	// arr[L..R]上，以arr[R]位置的数做划分值
	// <= X > X
	// <= X X
	// 返回arr[R]应该在的位置，左侧都小于等于它，右侧都大于它
	public static int partition(int[] arr, int L, int R) {
		if (L > R) {
			return -1;
		}
		if (L == R) {
			return L;
		}
		int lessEqual = L - 1;
		int index = L;
		while (index < R) {
			if (arr[index] <= arr[R]) {
				swap(arr, index, ++lessEqual);
			}
			index++;
		}
		swap(arr, ++lessEqual, R);
		return lessEqual;
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// 快排1.0
	// 每次最右侧数摆在该摆的位置，左右两侧是比他小/大的数
	public static void quickSort1(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process1(arr, 0, arr.length - 1);
	}

	public static void process1(int[] arr, int L, int R) {
		if (L >= R) {
			return;
		}
		// L..R partition arr[R] [ <=arr[R] arr[R] >arr[R] ]
		int M = partition(arr, L, R);
		process1(arr, L, M - 1);
		process1(arr, M + 1, R);
	}





	// 快排2.0
	// 每次选最右侧数，相同的摆在一起，左右是比他小/大的数
	public static void quickSort2(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process2(arr, 0, arr.length - 1);
	}

	// arr[L...R] 排有序，快排2.0方式
	public static void process2(int[] arr, int L, int R) {
		if (L >= R) {
			return;
		}
		// [ equalArea[0]  ,  equalArea[0]]
		int[] equalArea = netherlandsFlag(arr, L, R);
		process2(arr, L, equalArea[0] - 1);
		process2(arr, equalArea[1] + 1, R);
	}

	// 快排递归版本3.0
	// 比2.0多了个每轮随机选一个数来比较
	public static void quickSort3(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process3(arr, 0, arr.length - 1);
	}


	public static void process3(int[] arr, int L, int R) {
		if (L >= R) {
			return;
		}
		swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
		int[] equalArea = netherlandsFlag(arr, L, R);
		process3(arr, L, equalArea[0] - 1);
		process3(arr, equalArea[1] + 1, R);
	}


	// 快排非递归版本需要的辅助类
	// 用来记录要处理的是什么范围上的排序
	public static class Op {
		public int l;
		public int r;

		public Op(int left, int right) {
			l = left;
			r = right;
		}
	}

	// 快排3.0 非递归版本 用栈来执行
	public static void quickSort4(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int N = arr.length;
		swap(arr, (int) (Math.random() * N), N - 1);
		int[] equalArea = netherlandsFlag(arr, 0, N - 1);
		int el = equalArea[0];
		int er = equalArea[1];
		Stack<Op> stack = new Stack<>();
		stack.push(new Op(0, el - 1));
		stack.push(new Op(er + 1, N - 1));
		while (!stack.isEmpty()) {
			Op op = stack.pop(); // op.l ... op.r
			if (op.l < op.r) {
				swap(arr, op.l + (int) (Math.random() * (op.r - op.l + 1)), op.r);
				equalArea = netherlandsFlag(arr, op.l, op.r);
				el = equalArea[0];
				er = equalArea[1];
				stack.push(new Op(op.l, el - 1));
				stack.push(new Op(er + 1, op.r));
			}
		}
	}

	// 快排3.0 非递归版本 用队列来执行
	public static void quickSort5(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int N = arr.length;
		swap(arr, (int) (Math.random() * N), N - 1);
		int[] equalArea = netherlandsFlag(arr, 0, N - 1);
		int el = equalArea[0];
		int er = equalArea[1];
		Queue<Op> queue = new LinkedList<>();
		queue.offer(new Op(0, el - 1));
		queue.offer(new Op(er + 1, N - 1));
		while (!queue.isEmpty()) {
			Op op = queue.poll();
			if (op.l < op.r) {
				swap(arr, op.l + (int) (Math.random() * (op.r - op.l + 1)), op.r);
				equalArea = netherlandsFlag(arr, op.l, op.r);
				el = equalArea[0];
				er = equalArea[1];
				queue.offer(new Op(op.l, el - 1));
				queue.offer(new Op(er + 1, op.r));
			}
		}
	}

	// 生成随机数组（用于测试）
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}

	// 拷贝数组（用于测试）
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

	// 对比两个数组（用于测试）
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

	// 打印数组（用于测试）
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// 跑大样本随机测试（对数器）
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		System.out.println("test begin");
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			int[] arr3 = copyArray(arr1);
			quickSort3(arr1);
			quickSort2(arr2);
			quickSort3(arr3);
			if (!isEqual(arr1, arr2) || !isEqual(arr1, arr3)) {
				succeed = false;
				break;
			}
		}
		System.out.println("test end");
		System.out.println("测试" + testTime + "组是否全部通过：" + (succeed ? "是" : "否"));
	}

}
