package class06;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Test_Heap {
    public static class MyMaxHeap {
        private int[] heap;
        private final int limit;
        private int heapsize;

        public MyMaxHeap(int limit) {
            this.limit = limit;
            heap = new int[limit];
            heapsize = 0;
        }

        private boolean isEmpty() {
            return heapsize == 0;
        }

        private boolean isFull() {
            return heapsize == limit;
        }

        private void push(int v) {
            if (this.isFull()) {
                throw new RuntimeException("堆已满");
            }
            heap[heapsize] = v;
            heapInsert(heap, heapsize++);
        }

        private int pop() {
            if (this.isEmpty()) {
                throw new RuntimeException("堆是空的");
            }
            int ans = heap[0];
            swap(heap, 0, --heapsize);
            heapify(heap, 0, heapsize);
            return ans;
        }

        private int peek() {
            if (this.isEmpty()) {
                throw new RuntimeException("堆是空的");
            }
            return heap[0];
        }


        private void swap(int[] arr, int i, int j) {
            int t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
        }

        private void heapInsert(int[] heap, int index) {
            while (heap[index] > heap[(index - 1) / 2]) {
                swap(heap, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int[] heap, int index, int heapsize) {
            int left = (index * 2) + 1;
            while (left < heapsize) {
                int largest = left + 1 < heapsize ? (heap[left] > heap[left + 1] ? left : left + 1) : left;
                largest = heap[largest] > heap[index] ? largest : index;
                if(largest == index){
                    break;
                }
                swap(heap,index,largest);
                index = largest;
                left = (index * 2) + 1;
            }
        }

        // 递增
        public static class MyHeapComparator implements Comparator<Integer> {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        }

        public static class RightMaxHeap {
            private int[] arr;
            private final int limit;
            private int size;

            public RightMaxHeap(int limit) {
                arr = new int[limit];
                this.limit = limit;
                size = 0;
            }

            public boolean isEmpty() {
                return size == 0;
            }

            public boolean isFull() {
                return size == limit;
            }

            public void push(int value) {
                if (size == limit) {
                    throw new RuntimeException("heap is full");
                }
                arr[size++] = value;
            }

            public int pop() {
                int maxIndex = 0;
                for (int i = 1; i < size; i++) {
                    if (arr[i] > arr[maxIndex]) {
                        maxIndex = i;
                    }
                }
                int ans = arr[maxIndex];
                arr[maxIndex] = arr[--size];
                return ans;
            }

        }

        public static void main(String[] args) {
            int value = 1000;
            int limit = 100;
            int testTimes = 1000000;
            for (int i = 0; i < testTimes; i++) {
                int curLimit = (int) (Math.random() * limit) + 1;
                MyMaxHeap my = new MyMaxHeap(curLimit);
                RightMaxHeap test = new RightMaxHeap(curLimit);
                int curOpTimes = (int) (Math.random() * limit);
                for (int j = 0; j < curOpTimes; j++) {
                    if (my.isEmpty() != test.isEmpty()) {
                        System.out.println("Oops!");
                    }
                    if (my.isFull() != test.isFull()) {
                        System.out.println("Oops!");
                    }
                    if (my.isEmpty()) {
                        int curValue = (int) (Math.random() * value);
                        my.push(curValue);
                        test.push(curValue);
                    } else if (my.isFull()) {
                        if (my.pop() != test.pop()) {
                            System.out.println("Oops!");
                        }
                    } else {
                        if (Math.random() < 0.5) {
                            int curValue = (int) (Math.random() * value);
                            my.push(curValue);
                            test.push(curValue);
                        } else {
                            if (my.pop() != test.pop()) {
                                System.out.println("Oops!");
                            }
                        }
                    }
                }
            }
            System.out.println("finish!");

        }


    }
}
