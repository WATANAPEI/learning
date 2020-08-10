package parser;

import lexer.Token;
import lexer.TokenType;

import java.util.Iterator;
import java.util.List;

public class Parser {
    List<Token> tokens;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Node parse() {
        Iterator<Token> itr = tokens.iterator();
        while(itr.hasNext()) {
            Token token = itr.next();
            if(token.tokenType() == TokenType.Number) {
                return new NumberNode(token, itr);
            } else {
                return null;
            }
        }
        return null;
    }
}
