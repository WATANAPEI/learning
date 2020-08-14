package myHashTable;

/**
 * MyHashTable.MyHashTable
 * premise
 *  - if empty, return null
 *  - if key is duplicate with old key, overwrite old value with new value
 */
public class MyHashTable {
    private MarkableElement[] _list;
    private int mem = 100000; // number of element
    private int salt = 2453252;


    public MyHashTable() {
        _list = new MarkableElement[mem];
    }

    public void set(String key, Integer value) {
        int address = hash(key);
        _list[address] = new MarkableElement(value, false);
    }

    public void remove(String key) {
        int address = hash(key);
        _list[address] = new MarkableElement();
    }

    public Integer get(String key) {
        return _list[hash(key)].getValue();
    }

    private int hash(String key) {
        char[] chars = key.toCharArray();
        int hashed = 0;
        for(char c: chars) {
            hashed += c;
        }
        hashed = (hashed + salt) % mem;
        return hashed;

    }
}

