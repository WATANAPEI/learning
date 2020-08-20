package stack;

import java.util.ArrayList;
import java.util.List;

public class SetOfStack {
    List<Stack> s;
    int maxCapacity = 20;
    int set = 2;
    int currentSet;

    public SetOfStack() {
        s = new ArrayList();
        for(int i = 0;i < set; i++) {
            s.add(new Stack(maxCapacity));
        }
        currentSet = 0;
    }

    public SetOfStack(int capacity, int set) {
        this.maxCapacity = capacity;
        s = new ArrayList(set);
        for(int i =0;i < set; i++) {
            s.add(new Stack(maxCapacity));
        }
        currentSet = 0;
    }

    public void push(int value) {
        if (s.get(currentSet).isCapable()) {
            s.get(currentSet).push(value);
            return;
        }
        s.add(new Stack(s.get(0).getStackLength()));
        s.get(s.size()-1).push(value);
        currentSet++;
    }

    public int pop() {
        return 0;
    }

    public int getStackNumber() {
        return s.size();
    }

    private boolean isEmpty(int setNumber) {
        return s.get(setNumber).isEmpty();
    }
}
