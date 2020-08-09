import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReversibleStringTest {

//    @Test
//    public void testReverse() {
//        ReversibleString rs = new ReversibleString("aaaabbc");
//        String correctStr = "cbbaaaa";
//        assertEquals(correctStr, rs.reverse());
//    }

    @Test
    public void testNoString() {
        ReversibleString rs =  new ReversibleString("");
        rs.reverseInPlace();
        String correctStr = "";
        assertEquals(correctStr, rs.getString());
    }

    @Test
    public void testReverseInPlace() {
        ReversibleString rs =  new ReversibleString("aaaabbc");
        rs.reverseInPlace();
        String correctStr = "cbbaaaa";
        assertEquals(correctStr, rs.getString());

    }

}