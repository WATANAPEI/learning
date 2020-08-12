import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SubstringCheckerTest {

    @Test
    public void testSubString() {
        String s1 = "bcabca";
        String s2 = "abc";
        assertEquals(true, SubstringChecker.is_substring(s1, s2));
    }

    @Test
    public void testNotSubstring() {
        String s1 = "newspaper";
        String s2 = "bun";
        assertEquals(false, SubstringChecker.is_substring(s1, s2));
    }

    @Test
    public void checkRemoveDuplicate() {
        String s1 = "1111bbbbcc";
        assertEquals("1bc", SubstringChecker.removeDuplicate(s1));
    }



}