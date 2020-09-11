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
    public Optional<Node> parse() {
        RootNode rootNode = new RootNode();
        Optional<Node> node = new StmtNode().checkNode(this);
        rootNode.addChildNode(node.orElseThrow());
        while(itr.hasNext()) {
            node = new StmtNode().checkNode(this);
            rootNode.addChildNode(node.orElseThrow());
        }
        return Optional.ofNullable(rootNode);
    }

}
