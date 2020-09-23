package lexer;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// TODO: Use Regex to separate tokens
public class Lexer {
    Scanner sc;
    String next;
    List<Token> tokenList;
    public Lexer(String str) {
        sc = new Scanner(str);
        sc.useDelimiter("[\\p{javaWhitespace}]+");
        tokenList = new ArrayList();
    }
    public Lexer(Reader reader) {
        sc = new Scanner(reader);
        sc.useDelimiter("[\\p{javaWhitespace}]+");
        tokenList = new ArrayList();
    }
    public List<Token> analyze() {
        while(sc.hasNext()) {
            next = sc.next();
            if(next.matches(TokenType.NUMBER.getPattern())) {
                tokenList.add(new NumToken(next));
            } else if(next.matches((TokenType.STRING.getPattern()))){
                // strip double quotation
                tokenList.add(new StringToken(next.replace("\"", "")));
            } else if(next.matches(TokenType.WORD.getPattern())){
                tokenList.add(new WordToken(next));
            } else if(next.matches(TokenType.SINGLE_SYMBOL.getPattern())){
                tokenList.add(new SingleSymbolToken(next));
            } else if(next.matches(TokenType.MULTI_SYMBOL.getPattern())){
                tokenList.add(new MultiSymbolToken(next));
            } else {
                return null;
            }
        }
        return tokenList;
    }

}
