package parser;

import lexer.LexicalType;
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
        if(itr.hasNext()) {
            next = itr.next();
            return Optional.of(next);
        }
        return Optional.empty();
    }

    public boolean consume(LexicalType type) {
        Token token = getNext().orElseThrow();
        if(token.lexicalType() != type) {
            return false;
        }
        return true;
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
     * <Stmt> := <Term> { <+|-> <Term>} | <String>
     * <Term> := <Factor> { <*|/> <Factor>}
     * <Factor> := <Number>
     * @return
     */
    public Optional<Node> parseRoot() {
        RootNode rootNode = new RootNode();

        Optional<Node> node = parseStmt();
        rootNode.addChildNode(node.orElseThrow());
        while(itr.hasNext()) {
            node = parseStmt();
            rootNode.addChildNode(node.orElseThrow());
        }
        return Optional.ofNullable(rootNode);
    }

    public Optional<Node> parseTerm() {
        Token token = getNext().orElseThrow();
        LexicalType[] acceptable = {LexicalType.ADD, LexicalType.SUB, LexicalType.MUL, LexicalType.DIV};

    }

    public Optional<Node> parseStmt() {
        Token token = getNext().orElseThrow();
        if(token.tokenType() == TokenType.NUMBER) {
            if(peekNext().orElseThrow().tokenType() == TokenType.SINGLE_SYMBOL) {
                return new BiOppNode(next, new NumberLiteralNode(token), parseTerm());
            }
            return Optional.of(new NumberLiteralNode(token));
        } else if(token.tokenType() == TokenType.STRING) {
            return Optional.of(new StringLiteralNode(token));
        } else {
            return Optional.empty();
        }


        /*
        if(token.tokenType() == TokenType.NUMBER) {
            return Optional.of(new NumberLiteralNode(token));
        } else if(token.tokenType() == TokenType.STRING) {
            return Optional.of(new StringLiteralNode(token));
        } else {
            return Optional.empty();
        }
         */
    }
}
