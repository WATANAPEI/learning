import evaluator.Evaluator;
import lexer.Lexer;
import lexer.Token;
import org.junit.jupiter.api.Test;
import parser.Parser;
import parser.Node;
import semanticAnalyzer.SemanticAnalyzer;

import static org.junit.jupiter.api.Assertions.*;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

class LexerTest {

    @Test
    public void testTokens() {
        String numStr = "42";
        String strStr = "ufo";
        String symbolStr = "*";
        List<Token> tokens = new Lexer(numStr).analyze();
        Node node = new Parser(tokens).parse();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        evaluator.eval();

    }

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
    }

    @Test
    public void checkRegex() {
        String str = "2";
        String pattern = "\\d+";
        assertEquals(true, str.matches(pattern));
    }

}