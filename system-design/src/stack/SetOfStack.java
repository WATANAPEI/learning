package stack;

import java.util.ArrayList;
import java.util.List;

public class SetOfStack {
    List<Stack> stackList;
    int maxCapacity = 10;
    int set = 1;
    int currentSet;

    public SetOfStack() {
        stackList = new ArrayList();
        for(int i = 0;i < set; i++) {
            stackList.add(new Stack(maxCapacity));
        }
        currentSet = 0;
    }

    public SetOfStack(int capacity, int set) {
        this.maxCapacity = capacity;
        stackList = new ArrayList(set);
        for(int i =0;i < set; i++) {
            stackList.add(new Stack(maxCapacity));
        }
        currentSet = 0;
    }

    public void push(int value) {
        if (stackList.get(currentSet).isCapable()) {
            stackList.get(currentSet).push(value);
            return;
        }
        stackList.add(new Stack(stackList.get(0).getStackLength()));
        stackList.get(stackList.size()-1).push(value);
        currentSet++;
    }

    public int pop() {
        if(stackList.get(currentSet).isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        int result = stackList.get(currentSet).pop();
        if(stackList.get(currentSet).isEmpty() && getStackNumber() != 1) {
            stackList.remove(currentSet);
            currentSet--;
        }
        return result;
    }

    public int getStackNumber() {
        return stackList.size();
    }

    private boolean isEmpty(int setNumber) {
        return stackList.get(setNumber).isEmpty();
    }
}
