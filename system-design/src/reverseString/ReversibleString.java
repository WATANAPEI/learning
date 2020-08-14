package reverseString;

public class ReversibleString {
    char[] str;

    public ReversibleString(String str) {
        this.str = str.toCharArray();
    }

    public String reverse() {
        StringBuilder result = new StringBuilder("");
        for(int i = str.length-1; i >= 0; i--) {
            result.append(str[i]);
        }
        return result.toString();
    }

    public void reverseInPlace() {
        for(int i = 0; i < Math.floor(str.length/2); i++) {
            swap(i, str.length-1-i);
        }
    }

    private void swap(int i, int j) {
        char tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }

    public String getString() {
        return String.valueOf(str);
    }
}
