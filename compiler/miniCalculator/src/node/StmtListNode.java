package node;

import lexer.LexicalType;
import parser.Parser;
import parser.StringValue;
import parser.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <root> := {<stmtList>}
 * <stmtlist> := { <stmt> }
 * <stmt> := <expr> <;> | <assign> <;> | <if> | <condition> <;> | <for>
 * <for> := <FOR> <(> <assign> <;> <condition> <;> <assign> <)> <stmtList>
 * <if> := <IF> <(> <condition> <)> <stmtList> { <ELSE> <stmtList> }
 * <assign> := <word> <=> <expr>
 * <condition> := <expr> <Compare> <expr>
 * <expr> := <term> { <+|-> <term>}
 * <term> := <factor> { <*|/> <factor>}
 * <factor> := <(> <expr> <)> | <word> | <number> | <string>
 * <compare> := < <> | == | >= | <= | != >
 * @return
 */

class StmtListNode extends Node {
    private List<Node> nodeList;

    private StmtListNode() {
        nodeList = new ArrayList();
    }

    public void addChildNode(Node node) {
        nodeList.add(node);
    }

    public static Node checkNode(Parser parser) {
        StmtListNode stmtListNode = new StmtListNode();
        while(parser.peekNext().isPresent()) {
            if(parser.checkCurrentLexicalType(LexicalType.OPEN_CURBRA)) {
                parser.consume(LexicalType.OPEN_CURBRA);
                while(!parser.checkNextLexicalType(LexicalType.CLOSE_CURBRA)) {
                    stmtListNode.addChildNode(StmtNode.checkNode(parser));
                }
                parser.consume(LexicalType.CLOSE_CURBRA);
            } else {
                stmtListNode.addChildNode(StmtNode.checkNode(parser));
            }
        }
        return stmtListNode;
    }

    @Override
    public Optional<Value> value() {
        return Optional.empty();
    }

    @Override
    public Optional<Value> eval(Map<String, Value> symbolTable, Map<String, Node> functionTable) {
        StringBuilder sb = new StringBuilder();
        for(Node node: nodeList) {
            node.eval(symbolTable, functionTable).ifPresent(e -> {
                e.getSValue().ifPresent(f -> sb.append(f + "\n"));
            });
        }
        return Optional.ofNullable(new StringValue(sb.toString()));
    }
}
