package node;

import lexer.LexicalType;
import parser.Parser;
import parser.StringValue;
import parser.Value;

import java.util.Map;
import java.util.Optional;

class ForNode extends Node {
    Node initial;
    Node continueCondition;
    Node continueAssign;
    Node stmtNode;

    private ForNode() {
    }

    public void assignCondition(Node node) {
        this.continueCondition = node;
    }

    public void assignInitial(Node node) {
        this.initial = node;
    }

    public void assignContinueAssign(Node node) {
        this.continueAssign = node;
    }

    public void assignStmt(Node node) {
        this.stmtNode = node;
    }


    public static Node checkNode(Parser parser) {
        ForNode forNode = new ForNode();
        parser.consume(LexicalType.FOR);

        parser.consume(LexicalType.OPEN_BRA);
        forNode.assignInitial(AssignNode.checkNode(parser));
        parser.consume(LexicalType.SEMI_COLON);

        forNode.assignCondition(ConditionNode.checkNode(parser));
        parser.consume(LexicalType.SEMI_COLON);

        forNode.assignContinueAssign(AssignNode.checkNode(parser));
        parser.consume(LexicalType.CLOSE_BRA);

        if(parser.checkCurrentLexicalType(LexicalType.OPEN_CURBRA)) {
            forNode.assignStmt(StmtListNode.checkNode(parser));
        } else {
            forNode.assignStmt(StmtNode.checkNode(parser));
        }
        return forNode;
    }

    @Override
    public Optional<Value> value() {
        return Optional.empty();
    }

    @Override
    public Optional<Value> eval(Map<String, Value> symbolTable) {
        StringBuilder sb = new StringBuilder();
        initial.eval(symbolTable);
        for(;;) {
            boolean condition = this.continueCondition.eval(symbolTable)
                    .orElseThrow(() -> new IllegalStateException("No condition value"))
                    .getBValue()
                    .orElseThrow(() -> new IllegalStateException("No BValue is defined"));
            if(condition) {
                stmtNode.eval(symbolTable).ifPresent(e -> {
                    e.getSValue().ifPresent(f -> sb.append(f + "\n"));
                });

                continueAssign.eval(symbolTable);
            } else {
                return Optional.ofNullable(new StringValue(sb.toString()));
            }
        }
    }
}
