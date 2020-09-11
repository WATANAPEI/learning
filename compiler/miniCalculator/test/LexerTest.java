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
import java.util.Optional;

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
    public void testRootNode() {
        String str = "\"ufo\" 42";
        List<Token> tokens = new Lexer(str).analyze();
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
    public void testAddCalculation() {
        String symbolStr = "3 + 2";
        List<Token> tokens = new Lexer(symbolStr).analyze();
        assertEquals(TokenType.NUMBER, tokens.get(0).tokenType());
        assertEquals(TokenType.SINGLE_SYMBOL, tokens.get(1).tokenType());
        assertEquals(TokenType.NUMBER, tokens.get(2).tokenType());
        Node node = new Parser(tokens).parseRoot()
                .orElseThrow();
        assertEquals(Optional.empty(), node.value()); //root node doesn't return value
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        evaluator.eval();

    }

    @Test
    public void testMulCalculation() {
        String symbolStr = "21 * 2";
        List<Token> tokens = new Lexer(symbolStr).analyze();
        Node node = new Parser(tokens).parseRoot()
                .orElseThrow();
        assertEquals(Optional.empty(), node.value()); //root node doesn't return value
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        evaluator.eval();
    }

    @Test
    public void testZeroDivision() {
        String symbolStr = "21 / 0";
        List<Token> tokens = new Lexer(symbolStr).analyze();
        Node node = new Parser(tokens).parseRoot()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertThrows(ArithmeticException.class, () -> evaluator.eval()
        );

    }

    @Test
    public void testSemicolon() {
        String str = "\"word\"; 21";
        List<Token> tokens = new Lexer(str).analyze();
        Node node = new Parser(tokens).parseRoot()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        evaluator.eval();

    }

    @Test
    public void testAssign() {
        String assign = "x = 2 + 2";
        List<Token> tokens = new Lexer(assign).analyze();
        tokens.stream().forEach(e -> System.out.println(e.toString()));
        Node node = new Parser(tokens).parseRoot()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        evaluator.eval();

    }

    @Test
    public void checkRegex() {
        String str = "2";
        String pattern = "\\d+";
        assertEquals(true, str.matches(pattern));
    }


}