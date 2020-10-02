package node;

import lexer.LexicalType;
import parser.Parser;
import parser.Value;

import java.util.Map;
import java.util.Optional;

class ReturnNode extends Node {
    Node node;

    private ReturnNode() {
    }

    public void assignReturnNode(Node node) {
        this.node = node;
    }


    public static Node checkNode(Parser parser) {
        ReturnNode returnNode = new ReturnNode();
        if(parser.checkCurrentLexicalType(LexicalType.RETURN)) {
            parser.consume(LexicalType.RETURN);
            returnNode.assignReturnNode(ExprNode.checkNode(parser));
            parser.consume(LexicalType.SEMI_COLON);
            return returnNode;
        } else {
            throw new IllegalStateException("Return node is expected.");
        }
    }

    @Override
    public Optional<Value> value() {
        return Optional.empty();
    }

    @Override
    public Optional<Value> eval(Map<String, Value> symbolTable, Map<String, Node> functionTable) {
        return node.eval(symbolTable, functionTable);
    }
}
