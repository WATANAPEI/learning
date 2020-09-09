package queueByLinkedList;

public class MyLinkedList<T> {
    private ListElement<T> first;
    private ListElement<T> last;
    private int size;

    public MyLinkedList() {
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public void append(int value) {
        ListElement l = last;
        ListElement newElement = new ListElement(value, last, null);
        last = newElement;
        if(l == null) {
            first = newElement;
        } else {
            l.next = newElement;
        }
        size++;
    }

    public void delete(int index) {
        ListElement current = getElement(index);
        ListElement previous = current.previous;
        if(index == 0) {
            if(size != 1) {
                first = current.next;
                first.previous = null;
            } else {
                first = null;
                last = null;
            }
        } else if(index == size - 1) {
            last = previous;
            last.next = null;
        } else {
            previous.next = current.next;
            current.previous = previous;
        }
        size--;
    }

    public T get(int index) {
        return getElement(index).value;
    }

    private ListElement<T> getElement(int index) {
        //TODO: consider minus indexing
        ListElement current = first;
        ListElement nextElement = first.next;
        int i = 0;
        while(i < index) {
            current = nextElement;
            nextElement = current.next;
            i++;
        }
        return current;
    }

    public void insert(int index, int value){
        ListElement current = getElement(index);
        ListElement prev;
        ListElement newElement;
        if(index == 0) {
            current = first;
            first = new ListElement(value, null, current);
        } else {
            prev = current.previous;
            newElement = new ListElement(value, prev, current);
            prev.next = newElement;
            current.previous = newElement;
        }
        size++;
    }

    public T last() {
        return this.last.value;
    }

    private static class ListElement<T> {
        private ListElement previous;
        private ListElement next;
        private T value;

        public ListElement() {
            this.previous = null;
            this.next = null;
            this.value = null;
        }

        public ListElement(T value, ListElement previous, ListElement next) {
            this(previous, next);
            this.value = value;
        }

        public ListElement(ListElement previous, ListElement next) {
            this.previous = previous;
            this.next = next;
            this.value = null;
        }

    }

}
