package Queue;

import java.util.Arrays;

public class Queue {
    private int[] _list;
    private int front;
    private int rear;
    private int defaultCapacity = 20;

    public Queue() {
        _list = new int[defaultCapacity];
        front = 0;
        rear = 0;
    }

    public void enq(int val) {
        if(front == rear && _list[front] != 0) {
            System.out.println("Buffer overflow. Deque first.");
            return;
        }
        _list[rear] = val;
        if(rear == _list.length) {
            //_list = Arrays.copyOf(_list, _list.length * 2);
            rear = 0;
        }
        upheap(rear);
        rear++;
    }

    private void upheap(int i) {
        int parentIndex;
        if(isOutIndex(i)) {
            throw new IndexOutOfBoundsException("index error.");
        }
        if(isRoot(i)) {
            return;
        }
        parentIndex = (i-1) / 2;
        if(_list[parentIndex] > _list[i]) {
            swap(i, parentIndex);
            upheap(parentIndex);
        }
        return;
    }

    private boolean isOutIndex(int i) {
        return i < 0 || i > rear;
    }

    private boolean isRoot(int i) {
        return i == 0;
    }

    private void swap(int i, int j) {
        int tmp = _list[i];
        _list[i] = _list[j];
        _list[j] = tmp;
    }

    public int deq() {
        if(front == rear && _list[front] == 0) {
            System.out.println("Queue.Queue is empty. Enqueue first.");
            return 0;
        }
        int result = _list[front];
        _list[front++] = 0;
        return result;
    }

    public int peek() {
        return _list[front];
    }

    public void dump() {
        for(int i = 0; i < _list.length; i++) {
            System.out.print(_list[i] + " ");
            if(i == _list.length - 1) {
                System.out.println();
            }
        }
        System.out.println("Front: " + front);
        System.out.println("Rear: " + rear);
    }


}
