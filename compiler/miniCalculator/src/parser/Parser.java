package parser;

import lexer.LexicalType;
import lexer.NullToken;
import lexer.Token;
import lexer.TokenType;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class Parser {
    List<Token> tokens;
    Iterator<Token> itr;
    Token next;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.itr = tokens.iterator();
        this.next = null;
    }

    public Optional<Token> peekNext() {
        if(next != null) {
            return Optional.of(next);
        }
        if(itr.hasNext()) {
            next = itr.next();
            return Optional.of(next);
        }
        return Optional.empty();
    }

    public boolean checkLexicalType(LexicalType type) {
        Token token = peekNext().orElse(new NullToken());
        if(token.tokenType() != TokenType.NULL_TOKEN && token.lexicalType() == type) {
            return true;
        }
        return false;
    }

    public boolean consume(LexicalType type) {
        if(checkLexicalType(type)) {
            Token token = getNext().orElseThrow(); // just eat token
            if(token.lexicalType() == type) {
                return true;
            }
        }
        return false;
    }

    public Optional<Token> getNext() {
        if(next != null) {
            Token result = next;
            next = null;
            return Optional.of(result);
        } else {
            if(itr.hasNext()) {
                return Optional.of(itr.next());
            }
            return Optional.empty();
        }
    }

    /**
     * <Root> := {<Stmt>}
     * <Stmt> := <Expr> | <String> | <Assign>
     * <Assign> := <Word> <=> <Expr>
     * <Expr> := <Term> { <+|-> <Term>}
     * <Term> := <Factor> { <*|/> <Factor>}
     * <Factor> := <(> <Expr> <)> | <Word> | <Number>
     * @return
     */
    public Optional<Node> parse() {
        Node rootNode = RootNode.checkNode(this).orElseThrow();
        return Optional.ofNullable(rootNode);
    }

}
