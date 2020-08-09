import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum TokenType {
    Number("\\d+");

    private String pattern;

    TokenType(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return this.pattern;
    }
}

public class Lexer {
    Scanner sc;
    String next;
    List<Token> tokenList;
    public Lexer(Reader reader) {
        sc = new Scanner(reader);
        tokenList = new ArrayList();
    }
    public List<Token> parse() {
        while(sc.hasNext()) {
            next = sc.next();
            if(next.matches(TokenType.Number.getPattern())) {
                tokenList.add(new NumToken(next));
            } else {
                return null;
            }
        }
        return tokenList;
    }

}

abstract class Token {
    String image;
    TokenType type;
    public String getImage() {
        return this.image;
    }

}

class NumToken extends Token {
    public NumToken(String image) {
        this.image = image;
        this.type = TokenType.Number;
    }


}

//abstract class Node {
//
//}
//
//class NumberNode extends Node {
//    Value val;
//    public NumberNode(int i) {
//        val = new IValue(i);
//    }
//
//}

//interface Value {
//    public int getIValue();
//    public String getSValue();
//    public double getDValue();
//    public boolean getBValue();
//}
//
//class IValue implements Value {
//    int value;
//    public IValue(int i) {
//        this.value = i;
//    }
//    public int get
//
//}