package class07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Test_EveryStepShowBoss {
    /**
     * 给定两个数组
     * arr[i]表示客户编号，op[i]表示客户操作
     */
    public static class Data {
        public int[] arr;
        public boolean[] op;

        public Data(int[] a, boolean[] o) {
            arr = a;
            op = o;
        }
    }

    /**
     * 要实现的功能
     * 给定arr、op、得奖数k
     * 从左到右遍历，视为时间往前进
     * 遍历arr数组和op数组，遍历每一步输出一个得奖名单
     */
    public static List<List<Integer>> topK(int[] arr, boolean[] op, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        WhoIsWinner whoIsWinner = new WhoIsWinner(k);
        for (int i = 0; i < arr.length; i++) {
            whoIsWinner.operate(i, arr[i], op[i]);
            ans.add(whoIsWinner.whoiswinner());
        }
        return ans;
    }

    public static class Customer {
        public int id;
        public int buy;
        public int enterTime;

        public Customer(int id, int buy, int enterTime) {
            this.id = id;
            this.buy = buy;
            this.enterTime = 0;// 每个客户的初始进入时间都是0，等进入了某个区域再调整
        }
    }

    public static class CandidateComparator implements Comparator<Customer> {
        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.buy != o2.buy ? o2.buy - o1.buy : o1.enterTime - o2.enterTime;
        }
    }

    public static class WinnerComparator implements Comparator<Customer> {
        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.buy != o2.buy ? o1.buy - o2.buy : o1.enterTime - o2.enterTime;
        }
    }

    public static class WhoIsWinner {
        private HashMap<Integer, Customer> customers;
        private HeapGreater<Customer> candHeap;
        private HeapGreater<Customer> winnerHeap;
        private final int limit;

        public WhoIsWinner(int limit) {
            this.customers = new HashMap<Integer, Customer>();
            this.candHeap = new HeapGreater<>(new CandidateComparator());
            this.winnerHeap = new HeapGreater<>(new WinnerComparator());
            this.limit = limit;
        }

        public List<Integer> whoiswinner() {
            List<Integer> ans = new ArrayList<>();
            List<Customer> customers = winnerHeap.getAllElements();
            for (Customer customer : customers) {
                ans.add(customer.id);
            }
            return ans;
        }

        public void operate(int time, int id, boolean buyOrRefund) {
            // 之前没买过并且取消订单，直接忽视
            if (!buyOrRefund && !customers.containsKey(id)) {
                return;
            }
            // 一、对顾客实例和顾客列表调整
            // 之前没买过，先把顾客对象创建出来
            if (!customers.containsKey(id)) {
                customers.put(id, new Customer(id, 0, 0));
            }
            Customer customer = customers.get(id);
            // 统一管理顾客实例中buy的调整
            if (buyOrRefund) {
                customer.buy++;
            } else {
                customer.buy--;
            }
            if (customer.buy == 0) {
                customers.remove(id);
            }
            // 二、对候选区和得奖区调整
            // 如果候选区和得奖区都没这个顾客，说明是第一次买
            if (!candHeap.contains(customer) && !winnerHeap.contains(customer)) {
                // 第一次进区，调整时间
                customer.enterTime = time;
                // 如果得奖区还没满
                if (winnerHeap.size() < limit) {
                    winnerHeap.push(customer);
                    // 如果得奖区满了，那就放到候选区
                } else {
                    candHeap.push(customer);
                }
                // 如果顾客在候选区里
            } else if (candHeap.contains(customer)) {
                if (customer.buy == 0) {
                    candHeap.remove(customer);
                } else {
                    candHeap.resign(customer);
                }
            } else {
                if (customer.buy == 0) {
                    winnerHeap.remove(customer);
                } else {
                    winnerHeap.resign(customer);
                }
            }
            winnerMove(time);
        }

        public void winnerMove(int time) {
            if (candHeap.isEmpty()) {
                return;
            }
            if (winnerHeap.size() < limit) {
                Customer c = candHeap.pop();
                c.enterTime = time;
                winnerHeap.push(c);
            } else {
                // 当候选区订单最大的客户的订单量大于得奖区订单最小的客户的订单量
                if (candHeap.peek().buy > winnerHeap.peek().buy) {
                    Customer newWinner = candHeap.pop();
                    Customer oldWinner = winnerHeap.pop();
                    newWinner.enterTime = time;
                    oldWinner.enterTime = time;
                    candHeap.push(oldWinner);
                    winnerHeap.push(newWinner);
                }
            }
        }

    }

    // 干完所有的事，模拟，不优化
    public static List<List<Integer>> compare(int[] arr, boolean[] op, int k) {
        HashMap<Integer, Code02_EveryStepShowBoss.Customer> map = new HashMap<>();
        ArrayList<Code02_EveryStepShowBoss.Customer> cands = new ArrayList<>();
        ArrayList<Code02_EveryStepShowBoss.Customer> daddy = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int id = arr[i];
            boolean buyOrRefund = op[i];
            if (!buyOrRefund && !map.containsKey(id)) {
                ans.add(getCurAns(daddy));
                continue;
            }
            // 没有发生：用户购买数为0并且又退货了
            // 用户之前购买数是0，此时买货事件
            // 用户之前购买数>0， 此时买货
            // 用户之前购买数>0, 此时退货
            if (!map.containsKey(id)) {
                map.put(id, new Code02_EveryStepShowBoss.Customer(id, 0, 0));
            }
            // 买、卖
            Code02_EveryStepShowBoss.Customer c = map.get(id);
            if (buyOrRefund) {
                c.buy++;
            } else {
                c.buy--;
            }
            if (c.buy == 0) {
                map.remove(id);
            }
            // c
            // 下面做
            if (!cands.contains(c) && !daddy.contains(c)) {
                if (daddy.size() < k) {
                    c.enterTime = i;
                    daddy.add(c);
                } else {
                    c.enterTime = i;
                    cands.add(c);
                }
            }
            cleanZeroBuy(cands);
            cleanZeroBuy(daddy);
            cands.sort(new Code02_EveryStepShowBoss.CandidateComparator());
            daddy.sort(new Code02_EveryStepShowBoss.DaddyComparator());
            move(cands, daddy, k, i);
            ans.add(getCurAns(daddy));
        }
        return ans;
    }

    public static void move(ArrayList<Code02_EveryStepShowBoss.Customer> cands, ArrayList<Code02_EveryStepShowBoss.Customer> daddy, int k, int time) {
        if (cands.isEmpty()) {
            return;
        }
        // 候选区不为空
        if (daddy.size() < k) {
            Code02_EveryStepShowBoss.Customer c = cands.get(0);
            c.enterTime = time;
            daddy.add(c);
            cands.remove(0);
        } else { // 等奖区满了，候选区有东西
            if (cands.get(0).buy > daddy.get(0).buy) {
                Code02_EveryStepShowBoss.Customer oldDaddy = daddy.get(0);
                daddy.remove(0);
                Code02_EveryStepShowBoss.Customer newDaddy = cands.get(0);
                cands.remove(0);
                newDaddy.enterTime = time;
                oldDaddy.enterTime = time;
                daddy.add(newDaddy);
                cands.add(oldDaddy);
            }
        }
    }

    public static void cleanZeroBuy(ArrayList<Code02_EveryStepShowBoss.Customer> arr) {
        List<Code02_EveryStepShowBoss.Customer> noZero = new ArrayList<Code02_EveryStepShowBoss.Customer>();
        for (Code02_EveryStepShowBoss.Customer c : arr) {
            if (c.buy != 0) {
                noZero.add(c);
            }
        }
        arr.clear();
        for (Code02_EveryStepShowBoss.Customer c : noZero) {
            arr.add(c);
        }
    }

    public static List<Integer> getCurAns(ArrayList<Code02_EveryStepShowBoss.Customer> daddy) {
        List<Integer> ans = new ArrayList<>();
        for (Code02_EveryStepShowBoss.Customer c : daddy) {
            ans.add(c.id);
        }
        return ans;
    }


    // 为了测试
    public static Data randomData(int maxValue, int maxLen) {
        int len = (int) (Math.random() * maxLen) + 1;
        int[] arr = new int[len];
        boolean[] op = new boolean[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
            op[i] = Math.random() < 0.5 ? true : false;
        }
        return new Data(arr, op);
    }

    // 为了测试
    public static boolean sameAnswer(List<List<Integer>> ans1, List<List<Integer>> ans2) {
        if (ans1.size() != ans2.size()) {
            return false;
        }
        for (int i = 0; i < ans1.size(); i++) {
            List<Integer> cur1 = ans1.get(i);
            List<Integer> cur2 = ans2.get(i);
            if (cur1.size() != cur2.size()) {
                return false;
            }
            cur1.sort((a, b) -> a - b);
            cur2.sort((a, b) -> a - b);
            for (int j = 0; j < cur1.size(); j++) {
                if (!cur1.get(j).equals(cur2.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int maxValue = 10;
        int maxLen = 100;
        int maxK = 6;
        int testTimes = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            Data testData = randomData(maxValue, maxLen);
            int k = (int) (Math.random() * maxK) + 1;
            int[] arr = testData.arr;
            boolean[] op = testData.op;
            List<List<Integer>> ans1 = topK(arr, op, k);
            List<List<Integer>> ans2 = compare(arr, op, k);
            if (!sameAnswer(ans1, ans2)) {
                for (int j = 0; j < arr.length; j++) {
                    System.out.println(arr[j] + " , " + op[j]);
                }
                System.out.println(k);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("出错了！");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
