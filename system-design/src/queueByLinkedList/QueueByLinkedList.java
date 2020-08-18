package queueByLinkedList;

public class QueueByLinkedList {
    private MyLinkedList l;
    private int size;

    public QueueByLinkedList() {
        l = new MyLinkedList();
    }

    public void enqueue(int i) {
        l.append(i);
        size++;
    }

    public int size() {
        return this.size;
    }

    public int dequeue() {
        int top = l.get(0);
        l.delete(0);
        return top;

    }


}

