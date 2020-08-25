package addDigits;

import java.util.LinkedList;

public class DigitNumber{
    LinkedList<Integer> _value = new LinkedList();

    public DigitNumber(int value) {
        int restDigits = value;
        int leastDigit;
        int length = String.valueOf(value).length();
        for(int i = 0; i < length; i++) {
            leastDigit = restDigits % 10;
            restDigits /= 10;
            _value.addLast(leastDigit);
        }
    }

    private void addDummyDigit() {
        _value.addLast(0);
    }

    public int getDigitNumber(int index) {
        return _value.get(index);
    }

    public void add(DigitNumber d) {
        int tmpResult;
        int carry = 0;
        for(int i = 0; i < _value.size(); i++) {
            tmpResult = _value.get(i) + d.getDigitNumber(i) + carry;
            carry = 0;
            if(tmpResult > 9) {
                carry = 1;
                tmpResult = tmpResult % 10;
            }
            _value.set(i, tmpResult);
        }
        if(carry == 1) {
            _value.addLast(1);
        }
    }

    public int getValue() {
        int result = 0;
        for(int i = 0; i < _value.size(); i++) {
            result = (int) (result + _value.get(i) * Math.pow(10, i));
        }
        return result;
    }

}
