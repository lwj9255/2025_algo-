package class07;

import java.util.*;

public class Test_CoverMax {
    public static int coverMax1(int[][] lines) {
        Line[] ls = new Line[lines.length];
        for (int i = 0; i < lines.length; i++) {
            Line line = new Line(lines[i][0], lines[i][1]);
            ls[i] = line;
        }
        // 以开始位置从小到大排序
        Arrays.sort(ls, new StartComparator());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int max = 0;
        for (int i = 0; i < lines.length; i++) {
            while (!minHeap.isEmpty() && ls[i].start >= minHeap.peek()) {
                minHeap.poll();
            }
            minHeap.add(ls[i].end);
            max = Math.max(max, minHeap.size());
        }
        return max;
    }

    public static int coverMax2(int[][] lines) {
        Arrays.sort(lines, (a, b) -> (a[0] - b[0]));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int max = 0;
        for (int i = 0; i < lines.length; i++) {
            while (!minHeap.isEmpty() && lines[i][0] >= minHeap.peek()) {
                minHeap.poll();
            }
            minHeap.add(lines[i][1]);
            max = Math.max(max, minHeap.size());
        }
        return max;
    }

    public static class Line {
        public int start;
        public int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class StartComparator implements Comparator<Line> {
        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    public static int coverMaxComparator(int[][] lines) {
        int minStart = Integer.MAX_VALUE;
        int maxEnd = Integer.MIN_VALUE;
        for (int i = 0; i < lines.length; i++) {
            minStart = Math.min(minStart, lines[i][0]);
            maxEnd = Math.max(maxEnd, lines[i][1]);
        }
        int max = 0;
        for (double p = minStart + 0.5; p < maxEnd; p += 1) {
            int curl = 0;
            for (int i = 0; i < lines.length; i++){
                if(lines[i][0]<p && lines[i][1] > p){
                    curl++;
                }
            }
            max= Math.max(max,curl);
        }
        return max;
    }

    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        int N = 100;
        int L = 0;
        int R = 200;
        int testTimes = 200000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            int ans1 = coverMax1(lines);
            int ans2 = coverMax2(lines);
            int ans3 = coverMaxComparator(lines);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
    }
}
