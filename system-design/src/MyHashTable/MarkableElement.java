package MyHashTable;
public class MarkableElement {
    private Integer value;
    private boolean empty;

    public MarkableElement() {
        this.empty = false;
    }

    public MarkableElement(int value, boolean empty) {
        this.value = value;
        this.empty = empty;
    }

    public Integer getValue() {
        if(this.empty) {
            return null;
        }
        return value;
    }

    public boolean checkEmpty() {
        return empty;
    }

    public void setValue(int value) {
        if (empty == false) {
            this.value = value;
        }
    }
}
