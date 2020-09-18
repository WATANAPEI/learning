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
 * <Factor> := <(> <Expression> | <Word> | <Number> <)>
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
            Node exprNode = ExprNode.checkNode(parser).orElseThrow();
            stmtNode.addChildNode(exprNode);
            return Optional.of(stmtNode);
        } else if(token.tokenType() == TokenType.STRING) {
            token = parser.getNext().orElseThrow();
            stmtNode.addChildNode(new StringLiteralNode(token));
            return Optional.of(stmtNode);
        } else if(token.tokenType() == TokenType.WORD) {
            Node assignNode = AssignNode.checkNode(parser).orElseThrow();
            stmtNode.addChildNode(assignNode);
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
    public Optional<String> eval(Map<String, Value> symbolTable) {
        return node.eval(symbolTable);
    }
}
