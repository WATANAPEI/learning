package stack;

import java.util.LinkedList;
import java.util.List;

public class StackByLinkedList {
    LinkedList<Integer> _list;

    public StackByLinkedList() {
        _list = new LinkedList();
    }

    public int pop() {
        return _list.removeLast();
    }

    public void push(Integer value) {
        _list.addLast(value);
    }

    public int peek() {
        return _list.peekLast();
    }

    public boolean is_empty() {
        return _list.isEmpty();
    }


}
