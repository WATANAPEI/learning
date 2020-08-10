package lexer;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lexer {
    Scanner sc;
    String next;
    List<Token> tokenList;
    public Lexer(Reader reader) {
        sc = new Scanner(reader);
        tokenList = new ArrayList();
    }
    public List<Token> analyze() {
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
