package parser;

import lexer.LexicalType;

import java.util.Map;
import java.util.Optional;

class ExprNode extends Node {
    Node node;

    private ExprNode() {
    }

    public void addChildNode(Node node) {
        this.node = node;
    }

    public static Optional<Node> checkNode(Parser parser) {
        ExprNode exprNode = new ExprNode();
        //TODO: delegate BinOpNode.checkNode
        Node lhsNode = TermNode.checkNode(parser).orElse(null);
        if(lhsNode != null) {
            while(parser.checkLexicalType(LexicalType.ADD) || parser.checkLexicalType(LexicalType.SUB)) {
                exprNode.addChildNode(new BinOpNode(parser.getNext().orElseThrow(), lhsNode, TermNode.checkNode(parser).orElseThrow()));
                return Optional.of(exprNode);
            }
            exprNode.addChildNode(lhsNode);
            return Optional.of(exprNode);
        }
        return Optional.empty();
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
