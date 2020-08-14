import uniqueString.UniqueStringChecker;
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
//        System.out.println("a: " + UniqueString.UniqueStringChecker.checkAsciiNumber('a'));
//        System.out.println("Z: " + UniqueString.UniqueStringChecker.checkAsciiNumber('Z'));
//        System.out.println("): " + UniqueString.UniqueStringChecker.checkAsciiNumber(')'));
//    }

//    @Test
//    public void checkBooleanArrayInitNum() {
//        boolean[] test = new boolean[4];
//        assertEquals(false, test[0]);
//    }


}