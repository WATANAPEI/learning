import Compress.CompressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompressServiceTest {
    CompressService c;

    @BeforeEach
    public void setUp() {
        c = new CompressService();
    }

    @Test
    public void testSampleStringCompress() {
        String testStr = "AAABCCDDDD";
        String compressed = c.compressByCount(testStr);
        assertEquals("A3BC2D4", compressed);
    }

    @Test
    public void testSimpleStringCompress() {
        String testStr = "AAAA";
        String compressed = c.compressByCount(testStr);
        assertEquals("A4", compressed);

    }

}