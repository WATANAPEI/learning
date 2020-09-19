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

class ExprNode extends Node {
    Node node;

    private ExprNode() {
    }

    public void addChildNode(Node node) {
        this.node = node;
    }

    public static Optional<Node> checkNode(Parser parser) {
        ExprNode stmtNode = new ExprNode();
        Node lhsNode = TermNode.checkNode(parser).orElseThrow();
        while(parser.checkLexicalType(LexicalType.ADD) || parser.checkLexicalType(LexicalType.SUB)) {
            stmtNode.addChildNode(new BinOpNode(parser.getNext().orElseThrow(), lhsNode, TermNode.checkNode(parser).orElseThrow()));
            return Optional.of(stmtNode);
        }
        stmtNode.addChildNode(lhsNode);
        return Optional.of(stmtNode);
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
