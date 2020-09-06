import evaluator.Evaluator;
import lexer.Lexer;
import lexer.Token;
import lexer.TokenType;
import org.junit.jupiter.api.Test;
import parser.Parser;
import parser.Node;
import semanticAnalyzer.SemanticAnalyzer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class LexerTest {

    @Test
    public void testNumToken() {
        String numStr = "42";
        List<Token> tokens = new Lexer(numStr).analyze();
        Node node = new Parser(tokens).parseRoot()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        evaluator.eval();

    }

    @Test
    public void testStrToken() {
        String strStr = "\"ufo\"";
        String symbolStr = "*";
        List<Token> tokens = new Lexer(strStr).analyze();
        Node node = new Parser(tokens).parseRoot()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        evaluator.eval();

    }

    @Test
    public void testMulToken() {
        String symbolStr = "*";
        List<Token> tokens = new Lexer(symbolStr).analyze();
        assertEquals(TokenType.SINGLE_SYMBOL, tokens.get(0).tokenType());
        assertEquals("*", tokens.get(0).getImage());
        /*
        Node node = new Parser(tokens).parse();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        evaluator.eval();
         */
    }

    @Test
    public void checkRegex() {
        String str = "2";
        String pattern = "\\d+";
        assertEquals(true, str.matches(pattern));
    }

}