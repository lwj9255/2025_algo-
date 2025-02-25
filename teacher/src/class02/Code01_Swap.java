package class02;

public class Code01_Swap {
	
	public static void main(String[] args) {
		// 异或运算交换两个整数
		int a = 16;
		int b = 603;
		
		System.out.println(a);
		System.out.println(b);

		a = a ^ b;
		b = a ^ b;
		a = a ^ b;

		System.out.println(a);
		System.out.println(b);

		// 异或运算交换数组中的两个位置
		int[] arr = {3,1,100};

		int i = 0;
		int j = 1;

		System.out.println(arr[i] + " , " + arr[j]);
		
		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];
		
		System.out.println(arr[i] + " , " + arr[j]);
	}
	
	
	public static void swap (int[] arr, int i, int j) {
		// arr[0] = arr[0] ^ arr[0];
		arr[i]  = arr[i] ^ arr[j];
		arr[j]  = arr[i] ^ arr[j];
		arr[i]  = arr[i] ^ arr[j];
	}
	
	

}
