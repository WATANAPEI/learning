package queueBy2Stack;

import stack.Stack;

public class QueueByStacks {
    Stack stackForEnque;
    Stack stackForDeque;

    public QueueByStacks() {
        stackForEnque = new Stack();
        stackForDeque = new Stack();
    }

    public void enque(int i) {
        if(!stackForDeque.isEmpty()) {
            moveToEnque();
        }
        stackForEnque.push(i);
    }

    public int deque() {
        if(!stackForEnque.isEmpty()) {
            moveToDeque();
        }
        if(stackForDeque.isEmpty()) {
            throw new IllegalStateException("queue is empty.");
        }
        return stackForDeque.pop();
    }

    private void moveToEnque() {
        int elements = stackForDeque.size();
        while(elements > 0) {
            stackForEnque.push(stackForDeque.pop());
            elements--;
        }
    }

    private void moveToDeque() {
        int elements = stackForEnque.size();
        while(elements > 0) {
            stackForDeque.push(stackForEnque.pop());
            elements--;
        }
    }


}
