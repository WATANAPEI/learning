package MiniHeap;

import java.util.ArrayList;
import java.util.List;

public class MiniHeap {
    List list;
    public MiniHeap() {
        list = new ArrayList();
    }

    public void insert(int v) {
        list.add(v);
        upMin(list.size()-1);
    }

    public int extractMin() {
        int minValue = top();
        list.set(0, getTail());
        if(list.size() > 2) {
            int smallerChildIndex =
                    (int)list.get(1) > (int)list.get(2) ?
                            2 : 1;
            swap(0, smallerChildIndex);
        }
        return minValue;
    }

    private void swap(int i, int j) {
        int tmp = (int)list.get(i);
        list.set(i, (int)list.get(j));
        list.set(j, tmp);

    }
    private int getTail() {
        return (int)list.get(list.size()-1);
    }


    public void upMin(int index) {
        if (index == 0) {
            return;
        }
        int parentIndex = (index-1) / 2;
        int value = (int)list.get(index);
        int parentValue = (int)list.get(parentIndex);
        if(parentValue > value) {
            swap(parentIndex, index);
            upMin(parentIndex);
        }
    }

    public int top() {
        return (int)list.get(0);
    }
}
