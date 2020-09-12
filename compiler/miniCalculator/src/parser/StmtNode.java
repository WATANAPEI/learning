package parser;

import lexer.LexicalType;
import lexer.NullToken;
import lexer.Token;
import lexer.TokenType;

import java.util.Map;
import java.util.Optional;

/**
 * <Root> := {<Stmt>}
 * <Stmt> := <Expr> | <String> | <Assign>
 * <Assign> := <Word> <=> <Expr>
 * <Expr> := <Term> { <+|-> <Term>}
 * <Term> := <Factor> { <*|/> <Factor>}
 * <Factor> := <Number>
 * @return
 */

class StmtNode extends Node {
    Node node;

    private StmtNode() {
    }

    public void addChildNode(Node node) {
        this.node = node;
    }

    public static Optional<Node> checkNode(Parser parser) {
        StmtNode stmtNode = new StmtNode();
        Token token = parser.peekNext().orElse(new NullToken());
        if(token.tokenType() == TokenType.NUMBER) {
            Node lhsNode = TermNode.checkNode(parser).orElseThrow();
            while(parser.checkLexicalType(LexicalType.ADD) || parser.checkLexicalType(LexicalType.SUB)) {
                stmtNode.addChildNode(new BinOpNode(parser.getNext().orElseThrow(), lhsNode, TermNode.checkNode(parser).orElseThrow()));
                return Optional.of(stmtNode);
            }
            stmtNode.addChildNode(lhsNode);
            return Optional.of(stmtNode);
        } else if(token.tokenType() == TokenType.STRING) {
            token = parser.getNext().orElseThrow();
            stmtNode.addChildNode(new StringLiteralNode(token));
            return Optional.of(stmtNode);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Value> value() {
        return Optional.ofNullable(node.value().orElse(null));
    }

    @Override
    public void eval(Map symbolTable) {
        node.eval(symbolTable);
    }
}
