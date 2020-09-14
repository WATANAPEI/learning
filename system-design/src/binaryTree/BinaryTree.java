package binaryTree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T> {
    List<T> list;
    int size = 0;

    public BinaryTree() {
        list = new ArrayList<>();
    }

    public void add(T e) {
        list.add(e);
        size++;
    }

}
