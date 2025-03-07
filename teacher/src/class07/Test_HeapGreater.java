package class07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Test_HeapGreater<T> {
    private ArrayList<T> heap;
    private int heapsize;
    private HashMap<T, Integer> indexMap;
    private Comparator<? super T> comp;

    public Test_HeapGreater(Comparator<? super T> comp) {
        this.comp = comp;
        heapsize = 0;
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
    }

    public boolean isEmpty() {
        return heapsize == 0;
    }

    public int size() {
        return heapsize;
    }

    public boolean contains(T key) {
        return indexMap.containsKey(key);
    }

    public T peek() {
        return heap.get(0);
    }

    public void push(T obj) {
        heap.add(obj);
        indexMap.put(obj, heapsize);
        heapInsert(heapsize++);
    }

    public T pop() {
        T ans = heap.get(0);
        swap(0, heapsize - 1);
        indexMap.remove(ans);
        heap.remove(heapsize - 1);
        heapsize--;
        heapify(0);
        return ans;
    }

    public void remove(T obj) {
        T replace = heap.get(--heapsize);
        int index = indexMap.get(obj);
        indexMap.remove(obj);
        heap.remove(heapsize);
        if (obj != replace) {
            heap.set(index,replace);
            indexMap.put(replace,index);
            resign(index);
        }
    }

    private void resign(int index) {
        heapInsert(index);
        heapify(index);
    }

    public List<T> getAllElements(){
        List<T> ans = new ArrayList<>();
        for (T t : heap) {
            ans.add(t);
        }
        return ans;
    }

    private void heapify(int index) {
        int left = index * 2 + 1;
        while (left < heapsize) {
            int best = (left + 1) < heapsize && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? left + 1 : left;
            best = comp.compare(heap.get(best), heap.get(index)) < 0 ? best : index;
            if (index == best) {
                break;
            }
            swap(index, best);
            index = best;
            left = index * 2 + 1;
        }
    }

    private void heapInsert(int index) {
        while (index > 0 && comp.compare(heap.get(index), heap.get(index / 2 - 1)) < 0) {
            swap(index, index / 2 - 1);
        }
    }


    private void swap(int i, int j) {
        T o1 = heap.get(i);
        T o2 = heap.get(j);
        heap.set(i, o2);
        heap.set(j, o1);
        indexMap.put(o1, j);
        indexMap.put(o2, i);
    }
}
