package parser;

import lexer.Token;
import lexer.TokenType;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class Parser {
    List<Token> tokens;
    Iterator<Token> itr;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.itr = tokens.iterator();
    }

    /**
     * <Root> := {<Stmt>}
     * <Stmt> := <Term> { <+|-> <Term>} | <String>
     * <Term> := <Factor> { <*|/> <Factor>}
     * <Factor> := <Number>
     * @return
     */
    public Optional<Node> parseRoot() {
        Optional<Node> node = Optional.ofNullable(parseStmt());
        return node;
    }
    public Node parseStmt() {
        Token token = itr.next();
        if(token.tokenType() == TokenType.NUMBER) {
            return new NumberLiteralNode(token);
        } else if(token.tokenType() == TokenType.STRING) {
            return new StringLiteralNode(token);
        } else {
            return null;
        }
    }
}
