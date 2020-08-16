package stack;

public class Stack{
    private Integer[] _list;
    private int size = 0;
    private int maxCapacity = 20;

    public Stack() {
        _list = new Integer[maxCapacity];
    }

    public Stack(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        _list = new Integer[maxCapacity];
    }

    public void push(int i) {
        if(size >= maxCapacity) {
            throw new IllegalStateException("This container has reached to max capacity.");
        }
        _list[size++] = i;
    }

    public int pop() {
        int result = _list[size-1];
        _list[size-1] = null;
        size--;
        return result;
    }

    public int peek() {
        return _list[size-1];

    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

//    public int size() {
//        int count = 0;
//        for(int i = 0; i < maxCapacity; i++) {
//            if(_list[i] != null) {
//                count++;
//            }
//        }
//        return count;
//    }
}

