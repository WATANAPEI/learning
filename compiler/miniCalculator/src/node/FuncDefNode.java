package node;

import lexer.LexicalType;
import parser.Parser;
import parser.Value;

import java.util.List;
import java.util.Map;
import java.util.Optional;

class FuncDefNode extends Node {
    String name;
    List<String> args;
    Node stmts;

    private FuncDefNode() {
    }

    public void assignName(String name) {
        this.name = name;
    }

    public void assignArg(String wordNode) {
        args.add(wordNode);
    }

    public void assignStmts(Node node) {
        this.stmts = node;
    }

    public List<String> getArgs() {
        return this.args;
    }

    public Node getStmts() {
        return this.stmts;
    }

    public static Node checkNode(Parser parser) {
        FuncDefNode funcDefNode = new FuncDefNode();
        parser.consume(LexicalType.FUNC);
        funcDefNode.assignName(parser.getCurrent().orElseThrow().getImage());
        parser.getNext(); // consume
        parser.consume(LexicalType.OPEN_BRA);
        //assume argument is only one
        WordNode wordNode = (WordNode)WordNode.checkNode(parser);
        funcDefNode.assignArg(wordNode.getImage());
        parser.consume(LexicalType.CLOSE_BRA);
        funcDefNode.assignStmts(StmtListNode.checkNode(parser));
        return funcDefNode;
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
