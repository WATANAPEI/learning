package node;

import lexer.LexicalType;
import parser.Parser;
import parser.Value;

import java.util.List;
import java.util.Map;
import java.util.Optional;

class FuncNode extends Node {
    String name;
    List<Node> args;
    Node stmts;

    private FuncNode() {
    }

    public void assignName(String name) {
        this.name = name;
    }

    public void assignArg(Node node) {
        args.add(node);
    }

    public void assignStmts(Node node) {
        this.stmts = node;
    }

    public static Node checkNode(Parser parser) {
        FuncNode funcNode = new FuncNode();
        parser.consume(LexicalType.FUNC);
        funcNode.assignName(parser.getCurrent().orElseThrow().getImage());
        parser.getNext(); // consume
        parser.consume(LexicalType.OPEN_BRA);
        funcNode.assignArg(StmtListNode.checkNode(parser));
        parser.consume(LexicalType.CLOSE_BRA);
        funcNode.assignStmts(StmtListNode.checkNode(parser));
        return funcNode;
    }

    @Override
    public Optional<Value> value() {
        return Optional.empty();
    }

    @Override
    public Optional<Value> eval(Map<String, Value> symbolTable, Map<String, Node> functionTable) {
        functionTable.put(this.name, this);
        return Optional.empty();
    }
}
