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
        if(itr.hasNext()) {
            if(next != null) {
                return Optional.of(next);
            }
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

    public Optional<Node> parseFactor() {
        Token token = getNext().orElse(new NullToken());
        if(token.tokenType() == TokenType.NUMBER) {
            return Optional.of(new NumberLiteralNode(token));
        }else {
            return Optional.empty();
        }
    }

    public Optional<Node> parseTerm() {
        Token token = getNext().orElse(new NullToken());
        if(token.tokenType() == TokenType.NUMBER) {
            Node lhsNode = parseFactor().orElseThrow();
            while(checkLexicalType(LexicalType.MUL) || checkLexicalType(LexicalType.DIV)) {
                return Optional.of(new BinOpNode(getNext().orElseThrow(), lhsNode, parseFactor().orElseThrow()));
            }
            return Optional.of(lhsNode);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Node> parseStmt() {
        Token token = peekNext().orElse(new NullToken());
        if(token.tokenType() == TokenType.NUMBER) {
            Node lhsNode = parseTerm().orElseThrow();
            while(checkLexicalType(LexicalType.ADD) || checkLexicalType(LexicalType.SUB)) {
                return Optional.of(new BinOpNode(getNext().orElseThrow(), lhsNode, parseTerm().orElseThrow()));
            }
            return Optional.of(lhsNode);
        } else if(token.tokenType() == TokenType.STRING) {
            return Optional.of(new StringLiteralNode(token));
        } else {
            return Optional.empty();
        }

    }
}
