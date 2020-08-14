import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniqueStringCheckerTest {
    @Test
    public void testUniqueString() {
        assertEquals(true, UniqueStringChecker.check("abc"));
    }

    @Test
    public void testSameString() {
        assertEquals(false, UniqueStringChecker.check("xxx"));
    }

    @Test
    public void testEmptyString() {
        assertEquals(false, UniqueStringChecker.check(""));
    }

//    @Test
//    public void checkAsciiNum() {
//        System.out.println("a: " + UniqueStringChecker.checkAsciiNumber('a'));
//        System.out.println("Z: " + UniqueStringChecker.checkAsciiNumber('Z'));
//        System.out.println("): " + UniqueStringChecker.checkAsciiNumber(')'));
//    }

//    @Test
//    public void checkBooleanArrayInitNum() {
//        boolean[] test = new boolean[4];
//        assertEquals(false, test[0]);
//    }


}