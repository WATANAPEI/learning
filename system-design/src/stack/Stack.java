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

    public boolean isCapable() {
        return size < maxCapacity;
    }

    public int getStackLength() {
        return this.maxCapacity;
    }


    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * sort stacked element.
     * After sorting, elements which are sorted along with specified order
     * can be popped.
     * for now, sort descending.
     */
    public void sort() {
        // use a stack as a buffer. order elements reversed argument order.
        Stack buffer = new Stack();
        int tmp;
        while (!this.isEmpty()) {
            tmp = this.pop();
            if (!buffer.isEmpty()) {
                if (tmp >= buffer.peek()) {
                    this.push(buffer.pop());
                }
            }
            buffer.push(tmp);
        }
        while(!buffer.isEmpty()) {
            this.push(buffer.pop());
        }
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

