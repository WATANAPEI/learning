package stack;

import java.util.Arrays;

public class MultiStack {
    Integer[] _list;
    int[] stackPointer; // 1-based indexing
    int stackNum;
    int capacity;

    public MultiStack(int stackNum, int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be greater than 0.");
        }
        this.stackNum = stackNum;
        this.capacity = capacity;
        _list = new Integer[stackNum * capacity];
        stackPointer = new int[stackNum+1];
        Arrays.fill(stackPointer, -1);
    }

    public void push(int stackNumber, int val) {
        if(stackPointer[stackNumber] == capacity-1) {
            throw new IllegalArgumentException("stackNumber exceeds internal capacity." +
                    "Capacity is " + this.capacity);
        }
        stackPointer[stackNumber]++;
        _list[stackPointer[stackNumber]] = val;
    }

    public int pop(int stackNumber) {
        if(stackNumber > stackNum) {
            throw new IllegalArgumentException("stackNumber exceeds internal stack number." +
                    "internal number is " + this.stackNum);
        }

        int result = _list[stackPointer[stackNumber]];
        _list[stackPointer[stackNumber]] = null;
        stackPointer[stackNumber]--;
        return result;
    }

    public int peek(int stackNumber) {
        return _list[stackPointer[stackNumber]];
    }

    public int getSize(int stackNumber) {
        return stackPointer[stackNumber]+1;
    }


}
