package StackConstantComplexity;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class StackCC {
    LinkedList<Integer> _list;
    LinkedList<Integer> _min;

    public StackCC() {
        _list  = new LinkedList();
        _min = new LinkedList();
    }

    public void push(int v) {
        _list.addLast(v);
        if(_min.isEmpty() || _min.peekLast() > v) {
            _min.addLast(v);
        }
    }

    public int pop() {
        if(_list.isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }
        return _list.removeLast();
    }

    public int peekMin() {
        return _min.peekLast();
    }
}
