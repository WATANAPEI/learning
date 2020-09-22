package parser;

import node.Node;
import lexer.LexicalType;
import lexer.NullToken;
import lexer.Token;
import lexer.TokenType;
import node.RootNode;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class Parser {
    List<Token> tokens;
    Iterator<Token> itr;
    Token next;
    Token current;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.itr = tokens.iterator();
        this.next = null;
        this.current = itr.next();
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

    public boolean checkCurrentLexicalType(LexicalType type) {
        Token token = getCurrent().orElse(new NullToken());
        if(token.tokenType() != TokenType.NULL_TOKEN && token.lexicalType() == type) {
            return true;
        }
        return false;
    }

    public boolean checkNextLexicalType(LexicalType type) {
        Token token = peekNext().orElse(new NullToken());
        if(token.tokenType() != TokenType.NULL_TOKEN && token.lexicalType() == type) {
            return true;
        }
        return false;
    }

    public void consume(LexicalType type) {
        if(checkCurrentLexicalType(type)) {
            Token token = getCurrent()
                    .orElseThrow(()-> new IllegalStateException("No Token.")); // just eat token
            current = token;
            if(token.lexicalType() == type) {
                getNext(); // proceed a token
                return;
            }
        }
        throw new IllegalStateException("Token Consuming error. Expected: " + type.toString());
    }

    public Optional<Token> getCurrent() {
        if(current == null) {
            return Optional.empty();
        }
        return Optional.of(this.current);
    }

    public Optional<Token> getNext() {
        if(next != null) {
            Token result = next;
            current = result;
            next = null;
            return Optional.of(result);
        } else {
            if(itr.hasNext()) {
                Token result = itr.next();
                current = result;
                return Optional.of(result);
            }
            current = null;
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
        Node rootNode = RootNode.checkNode(this);
        return Optional.ofNullable(rootNode);
    }

}
