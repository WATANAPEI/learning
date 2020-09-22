package node;

import lexer.LexicalType;
import lexer.Token;
import lexer.TokenType;
import parser.Parser;
import parser.Value;

import java.util.Map;
import java.util.Optional;

class IfNode extends Node {
    Node condition;
    Node trueStmt;
    Node falseStmt;

    private IfNode() {
    }

    public void assignCondition(Node node) {
        this.condition = node;
    }

    public void assignTrueStmt(Node node) {
        this.trueStmt = node;
    }

    public void assignFalseStmt(Node node) {
        this.falseStmt = node;
    }

    public static Node checkNode(Parser parser) {
        IfNode ifNode = new IfNode();
        parser.consume(LexicalType.IF);
        parser.consume(LexicalType.OPEN_BRA);
        ifNode.assignCondition(ConditionNode.checkNode(parser));
        parser.consume(LexicalType.CLOSE_BRA);
        ifNode.assignTrueStmt(StmtNode.checkNode(parser));
        if(parser.checkCurrentLexicalType(LexicalType.ELSE)) {
            parser.consume(LexicalType.ELSE);
            ifNode.assignFalseStmt(StmtNode.checkNode(parser));
        }
        return ifNode;
    }

    @Override
    public Optional<Value> value() {
        return Optional.empty();
    }

    @Override
    public Optional<Value> eval(Map<String, Value> symbolTable) {
        boolean condition = this.condition.eval(symbolTable)
                .orElseThrow(() -> new IllegalStateException("No condition value"))
                .getBValue()
                .orElseThrow(() -> new IllegalStateException("No BValue is defined"));
        if(condition) {
            return trueStmt.eval(symbolTable);
        } else {
            if(falseStmt != null) {
                return falseStmt.eval(symbolTable);
            } else {
                return Optional.empty();
            }
        }
    }
}
