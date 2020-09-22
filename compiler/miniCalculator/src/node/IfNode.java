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

//    public static Node checkNode(Parser parser) {
//        IfNode ifNode = new IfNode();
//        parser.consume(LexicalType.IF);
//        parser.consume(LexicalType.OPEN_BRA);
//        ifNode.assignCondition(ConditionNo);
//    }

    @Override
    public Optional<Value> value() {
        return Optional.empty();
    }

    @Override
    public Optional<Value> eval(Map<String, Value> symbolTable) {
        symbolTable.put(condition.value().orElseThrow().getSValue().orElseThrow(),
                trueStmt.eval(symbolTable).orElse(null));
        return Optional.empty();
    }
}
