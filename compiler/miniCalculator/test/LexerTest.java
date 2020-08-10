import lexer.Lexer;
import lexer.Token;
import org.junit.jupiter.api.Test;
import parser.Parser;
import parser.Node;

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
        List<Token> tokens = lexer.analyze();
        assertEquals(1, tokens.size());
        assertEquals("2", tokens.get(0).getImage());
        Parser parser = new Parser(tokens);
        Node node = parser.parse();
        assertEquals(2, node.value().getIValue());
        assertEquals(2, node.eval().getIValue());


    }

    @Test
    public void checkRegex() {
        String str = "2";
        String pattern = "\\d+";
        assertEquals(true, str.matches(pattern));
    }

}