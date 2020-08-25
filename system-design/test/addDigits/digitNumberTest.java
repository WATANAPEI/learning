package addDigits;

import org.junit.jupiter.api.Test;

import java.sql.SQLSyntaxErrorException;

import static org.junit.jupiter.api.Assertions.*;

class digitNumberTest {
    @Test
    public void testCreateDigitNumber() {
        DigitNumber d = new DigitNumber(4325);
        assertEquals(5, d.getDigitNumber(0));
        assertEquals(2, d.getDigitNumber(1));
        assertEquals(3, d.getDigitNumber(2));
        assertEquals(4, d.getDigitNumber(3));

    }

    @Test
    public void testAdd() {
        DigitNumber d1 = new DigitNumber(122);
        DigitNumber d2 = new DigitNumber(567);
        d1.add(d2);
        assertEquals(9, d1.getDigitNumber(0));
        assertEquals(8, d1.getDigitNumber(1));
        assertEquals(6, d1.getDigitNumber(2));
    }

    @Test
    public void testAddCarry() {
        DigitNumber d1 = new DigitNumber(321);
        DigitNumber d2 = new DigitNumber(999);
        d1.add(d2);
        assertEquals(0, d1.getDigitNumber(0));
        assertEquals(2, d1.getDigitNumber(1));
        assertEquals(3, d1.getDigitNumber(2));
        assertEquals(1, d1.getDigitNumber(3));

    }

    @Test
    public void testGetValue() {
        DigitNumber d1 = new DigitNumber(324);
        assertEquals(324, d1.getValue());
    }


}