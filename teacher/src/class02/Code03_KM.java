package class02;

import java.util.HashMap;
import java.util.HashSet;

// 输入一定能够保证，数组中所有的数都出现了M次，只有一种数出现了K次
// 1 <= K < M
// 返回这种数
public class Code03_KM {

	// 死方法找出出现K次的数
	public static int test(int[] arr, int k, int m) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int num : arr) {
			if (map.containsKey(num)) {
				map.put(num, map.get(num) + 1);
			} else {
				map.put(num, 1);
			}
		}
		int ans = 0;
		for (int num : map.keySet()) {
			if (map.get(num) == k) {
				ans = num;
				break;
			}
		}
		return ans;
	}

	public static HashMap<Integer, Integer> map = new HashMap<>();

	// 只有一种数出现了K次，其他数都出现了M次
	public static int onlyKTimes(int[] arr, int k, int m) {
		if (map.size() == 0) {
			mapCreater(map); // 二进制中数值与位置的映射，如 32 -> 5，64 -> 6
		}
		int[] t = new int[32];

		// 数组t中记录了每个位置的1出现了几次
		// t[0] 0位置的1出现了几个
		// t[i] i位置的1出现了几个
		for (int num : arr) {
			while (num != 0) {
				int rightOne = num & (-num);
				t[map.get(rightOne)]++;
				num ^= rightOne; // 最右侧的1与该数字本身异或，消去该位置的1，循环计算出下一个最右侧的1
			}
		}
		int ans = 0;

		// 数组t中遍历，若该位置的1出现次数无法被m除尽，说明出现k次的数在该位置上也为1，对ans中该位置调整为1
		for (int i = 0; i < 32; i++) {
			if (t[i] % m != 0) {
				ans |= (1 << i);
			}
		}
		// 如果这个出现了K次的数，就是0
		// 那么上面代码中的 : ans |= (1 << i);
		// 就不会发生
		// 那么ans就会一直维持0，最后返回0，也是对的！

		return ans;
	}

	/**
	 * 生成一个包含32个键值对的 HashMap
	 * 其中键是 2 的幂（1, 2, 4, 8, 16, ...）
	 * 值是从 0 到 31 的整数，表示二进制位对应的索引。
	 * 1 -> 0
	 * 2 -> 1
	 * 4 -> 2
	 * ...
	 * 536870912 -> 29
	 * 1073741824 -> 30
	 * 2147483648 -> 31
	 * @param map
	 */
	public static void mapCreater(HashMap<Integer, Integer> map) {
		int value = 1;
		for (int i = 0; i < 32; i++) {
			map.put(value, i);
			value <<= 1;
		}
	}

	// 更简洁的写法，去掉了辅助映射的数组map
	public static int km(int[] arr, int k, int m) {
		// help数组记录每个位置出现1的次数
		int[] help = new int[32];

		// 遍历数组
		for (int num : arr) {
			// 对32个位置进行遍历
			for (int i = 0; i < 32; i++) {
				// 当前值右移 i 后与1与运算，若等于1，说明当前值在 i 位置为1，那么在help数组中对该位置 i 加一
				help[i] += (num >> i) & 1;
			}
		}
		int ans = 0;
		for (int i = 0; i < 32; i++) {
			help[i] %= m;
			if (help[i] != 0) {
				ans |= 1 << i;
			}
		}
		return ans;
	}

	// 创建数组
	public static int[] randomArray(int maxKinds, int range, int k, int m) {
		// 出现次数为k的数的值
		int ktimeNum = randomNumber(range);

		// Math.random()返回的是≥0但是＜1的数，因此有可能是0，所以要+2，保证数字的种类至少是2个
		int numKinds = (int) (Math.random() * maxKinds) + 2;

		// 数组大小为k * 1 + (numKinds - 1) * m
		int[] arr = new int[k + (numKinds - 1) * m];

		// 先在数组中填入k个ktimeNum
		int index = 0;
		for (; index < k; index++) {
			arr[index] = ktimeNum;
		}

		// 数字种类减去一，并记录已有的数字
		numKinds--;
		HashSet<Integer> set = new HashSet<>();
		set.add(ktimeNum);

		while (numKinds != 0) {
			int curNum = 0;
			// 如果新的随机数已经出现过了，那么重新随机
			do {
				curNum = randomNumber(range);
			} while (set.contains(curNum));

			// 随机完成后，记录，种类减一，并往数组中加入m个该数
			set.add(curNum);
			numKinds--;
			for (int i = 0; i < m; i++) {
				arr[index++] = curNum;
			}
		}
		// arr 填好了，但此时数组是有序的，相同的数字扎堆
		for (int i = 0; i < arr.length; i++) {
			// i 位置的数，随机和j位置的数做交换
			int j = (int) (Math.random() * arr.length);// 0 ~ N-1
			int tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
		}
		return arr;
	}

	// 获得[-range, +range]之间的一个随机整数
	public static int randomNumber(int range) {
		return (int) (Math.random() * (range + 1)) - (int) (Math.random() * (range + 1));
	}

	// 为了测试
	public static void main(String[] args) {
		int kinds = 5;
		int range = 30;
		int testTime = 100000;
		int max = 9;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int a = (int) (Math.random() * max) + 1; // a 1 ~ 9
			int b = (int) (Math.random() * max) + 1; // b 1 ~ 9
			int k = Math.min(a, b);
			int m = Math.max(a, b);
			// k < m
			if (k == m) {
				m++;
			}
			int[] arr = randomArray(kinds, range, k, m);
			int ans1 = test(arr, k, m);
			int ans2 = onlyKTimes(arr, k, m);
			int ans3 = km(arr, k, m);
			if (ans1 != ans2 || ans1 != ans3) {
				System.out.println(ans1);
				System.out.println(ans3);
				System.out.println("出错了！");
			}
		}
		System.out.println("测试结束");
	}

}
