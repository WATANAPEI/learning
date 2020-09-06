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
        RootNode rootNode = new RootNode();

        Optional<Node> node = parseStmt();
        rootNode.addChildNode(node.orElseThrow());
        while(itr.hasNext()) {
            node = parseStmt();
            rootNode.addChildNode(node.orElseThrow());
        }
        return Optional.ofNullable(rootNode);
    }
    public Optional<Node> parseStmt() {
        Token token = itr.next();
        if(token.tokenType() == TokenType.NUMBER) {
            return Optional.of(new NumberLiteralNode(token));
        } else if(token.tokenType() == TokenType.STRING) {
            return Optional.of(new StringLiteralNode(token));
        } else {
            return Optional.empty();
        }
    }
}
