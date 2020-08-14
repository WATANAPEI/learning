import stringPermutation.StringPermutationChecker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringPermutationCheckerTest {
    StringPermutationChecker spc;

    @Test
    public void testStringPermutation() {
        boolean result = StringPermutationChecker.check("bin", "Nib");
        assertEquals(false, result);
    }

    @Test
    public void testStringSort() {
        String str = "nib";
        assertEquals("bin", StringPermutationChecker.sort(str));
    }

}