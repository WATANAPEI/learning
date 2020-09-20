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
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("42"));

    }

    @Test
    public void testStrToken() {
        String strStr = "\"ufo\"";
        List<Token> tokens = new Lexer(strStr).analyze();
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("ufo"));

    }

    @Test
    public void testRootNode() {
        String str = "\"ufo\" 42";
        List<Token> tokens = new Lexer(str).analyze();
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("ufo\n42"));

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
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        assertEquals(Optional.empty(), node.value()); //root node doesn't return value
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("5"));

    }

    @Test
    public void testMulCalculation() {
        String symbolStr = "21 * 2";
        List<Token> tokens = new Lexer(symbolStr).analyze();
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        assertEquals(Optional.empty(), node.value()); //root node doesn't return value
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("42"));
    }

    @Test
    public void testZeroDivision() {
        String symbolStr = "21 / 0";
        List<Token> tokens = new Lexer(symbolStr).analyze();
        Node node = new Parser(tokens).parse()
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
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("word\n21"));

    }

    @Test
    public void testAssignAdd() {
        String assign = "x = 2 + 2; x";
        List<Token> tokens = new Lexer(assign).analyze();
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("4"));

    }
    @Test
    public void testAssignMul() {
        String assign = "y = 4 * 3; y";
        List<Token> tokens = new Lexer(assign).analyze();
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("12"));

    }

    @Test
    public void testCalcOrder() {
        String assign = "y = 6 + 4 * 3; y";
        List<Token> tokens = new Lexer(assign).analyze();
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("18"));
    }

    @Test
    public void testBracket() {
        String assign = "y = ( 6 + 4 ) * 3; y";
        List<Token> tokens = new Lexer(assign).analyze();
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("30"));
    }

    @Test
    public void testMultiVariables() {
        String assign = "x = 3; y =  ( x + 4 )  * 3; y";
        List<Token> tokens = new Lexer(assign).analyze();
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("21"));
    }

    public void testEqual() {
        String str = "x = 2; x > 1";
        List<Token> tokens = new Lexer(str).analyze();
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("21"));

    }

    @Test
    public void checkRegex() {
        String str = "2";
        String pattern = "\\d+";
        assertEquals(true, str.matches(pattern));
    }
}