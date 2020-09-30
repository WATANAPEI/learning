package node;

import lexer.LexicalType;
import parser.Parser;
import parser.Value;

import java.util.*;

class FuncCallNode extends Node {
    String callee;
    List<Node> args;

    private FuncCallNode() {
        args = new ArrayList<>();
    }

    public void assignCallee(String name) {
        this.callee = name;
    }

    public void assignArg(Node node) {
        args.add(node);
    }

    public static Node checkNode(Parser parser) {
        FuncCallNode funcCallNode = new FuncCallNode();
        funcCallNode.assignCallee(parser.getCurrent().orElseThrow().getImage());
        parser.getNext(); // consume
        parser.consume(LexicalType.OPEN_BRA);
        //for now argument is only one
        funcCallNode.assignArg(ExprNode.checkNode(parser));
        parser.consume(LexicalType.CLOSE_BRA);
        return funcCallNode;
    }

    @Override
    public Optional<Value> value() {
        return Optional.empty();
    }

    @Override
    public Optional<Value> eval(Map<String, Value> symbolTable, Map<String, Node> functionTable) {
        FuncDefNode funcDefNode = (FuncDefNode)functionTable.get(this.callee);
        Node stmtsNode = funcDefNode.getStmts();
        Map<String, Value> localEnv = new HashMap();
        List<String> argStrings = funcDefNode.getArgs();
        for(int i = 0; i < argStrings.size(); i++) {
            Value v = this.args.get(i).eval(symbolTable, functionTable).orElseThrow();
            localEnv.put(argStrings.get(i), v);
        }
        return stmtsNode.eval(localEnv, functionTable);
        //TODO: put key and value. key should be name of argument in func def.
    }
}
