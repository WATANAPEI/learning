import evaluator.Evaluator;
import lexer.Lexer;
import lexer.Token;
import lexer.TokenType;
import org.junit.jupiter.api.Test;
import parser.Parser;
import node.Node;
import semanticAnalyzer.SemanticAnalyzer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

class LexerTest {

    @Test
    public void testNumToken() {
        String str = "42;";
        List<Token> tokens = new Lexer().analyze(str);
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("42\n"));

    }

    @Test
    public void testStrToken() {
        String str = "\"ufo\";";
        List<Token> tokens = new Lexer().analyze(str);
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("ufo\n"));

    }

    @Test
    public void testRootNode() {
        String str = "\"ufo\";42;";
        List<Token> tokens = new Lexer().analyze(str);
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("ufo\n42\n"));

    }

    @Test
    public void testMulToken() {
        String str = "*;";
        List<Token> tokens = new Lexer().analyze(str);
        assertEquals(TokenType.SINGLE_SYMBOL, tokens.get(0).tokenType());
        assertEquals("*", tokens.get(0).getImage());
    }

    @Test
    public void testAddCalculation() {
        String str = "3+2;";
        List<Token> tokens = new Lexer().analyze(str);
        assertEquals(TokenType.NUMBER, tokens.get(0).tokenType());
        assertEquals(TokenType.SINGLE_SYMBOL, tokens.get(1).tokenType());
        assertEquals(TokenType.NUMBER, tokens.get(2).tokenType());
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        assertEquals(Optional.empty(), node.value()); //root node doesn't return value
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("5\n"));

    }

    @Test
    public void testMulCalculation() {
        String str = "21*2;";
        List<Token> tokens = new Lexer().analyze(str);
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        assertEquals(Optional.empty(), node.value()); //root node doesn't return value
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("42\n"));
    }

    @Test
    public void testZeroDivision() {
        String str = "21/0;";
        List<Token> tokens = new Lexer().analyze(str);
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertThrows(ArithmeticException.class, () -> evaluator.eval()
        );

    }

    @Test
    public void testSemicolon() {
        String str = "\"word\";21;";
        List<Token> tokens = new Lexer().analyze(str);
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("word\n21\n"));

    }

    @Test
    public void testAssignAdd() {
        String str = "x=2+2;x;";
        List<Token> tokens = new Lexer().analyze(str);
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("4\n"));

    }

    @Test
    public void testAssignMul() {
        String str = "y = 4 * 3; y;";
        List<Token> tokens = new Lexer().analyze(str);
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("12\n"));

    }

    @Test
    public void testCalcOrder() {
        String str = "y = 6 + 4 * 3; y;";
        List<Token> tokens = new Lexer().analyze(str);
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("18\n"));
    }

    @Test
    public void testBracket() {
        String str = "y = (6+4)*3; y;";
        List<Token> tokens = new Lexer().analyze(str);
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("30\n"));
    }

    @Test
    public void testMultiVariables() {
        String str = "x = 3; y = (x+4) * 3; y;";
        List<Token> tokens = new Lexer().analyze(str);
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("21\n"));
    }

    @Test
    public void testGreater() {
        String str = "x = 2; x > 1;";
        List<Token> tokens = new Lexer().analyze(str);
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("true\n"));
    }

    @Test
    public void testAddGreater() {
        String str = "x=2;\nx + 3 > 6;";
        List<Token> tokens = new Lexer().analyze(str);
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("false\n"));
    }

    @Test
    public void testGreaterThanEqual() {
        String str = "x = 2; x >= 1;";
        List<Token> tokens = new Lexer().analyze(str);
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("true\n"));
    }

    @Test
    public void testEqual() {
        String str = "x = 3; x == 1;";
        List<Token> tokens = new Lexer().analyze(str);
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("false\n"));
    }

    @Test
    public void testIfStatement() {
        String str = "x=3; IF (x == 3) y=5; ELSE y = 10; y;";
        List<Token> tokens = new Lexer().analyze(str);
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("5\n"));
    }

    @Test
    public void testIfStmtWithoutELSE() {
        String str = "y = 5; IF(y < 3) y = 10; y;";
        List<Token> tokens = new Lexer().analyze(str);
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("5\n"));
    }

    @Test
    public void testStrSplitter() {
        String str = "tmp=5; x = 32; z == 621;y";
        List<String> strList = new Lexer().splitString(str);

    }

    @Test
    public void testStmtList() {
        String str = "e = 0; x = 3; e; x;";
        List<Token> tokens = new Lexer().analyze(str);
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("0\n3\n"));

    }

    @Test
    public void testForLoop() {
        String str = "FOR ( i = 0 ; i < 4 ; i = i + 1 ) i;";
        List<Token> tokens = new Lexer().analyze(str);
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("0\n1\n2\n3\n\n"));
    }

    @Test
    public void testFunction() {
        String str = "FUNC double(x) { y = x + 1; RETURN y * y; } double(3);";
        List<Token> tokens = new Lexer().analyze(str);
        Node node = new Parser(tokens).parse()
                .orElseThrow();
        Node ast = new SemanticAnalyzer(node).check();
        Evaluator evaluator = new Evaluator(ast);
        assertTrue(evaluator.eval().equals("16\n\n"));
    }

}