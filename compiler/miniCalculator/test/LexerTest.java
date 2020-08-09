import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

class LexerTest {

    @Test
    public void testParseNumber() {
        String str = "2";
        Reader reader = new StringReader(str);
        Lexer lexer = new Lexer(reader);
        List<Token> token = lexer.parse();
        assertEquals(1, token.size());
        assertEquals("2", token.get(0).getImage());


    }

    @Test
    public void checkRegex() {
        String str = "2";
        String pattern = "\\d+";
        assertEquals(true, str.matches(pattern));
    }

}