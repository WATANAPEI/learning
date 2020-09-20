package parser;

import lexer.LexicalType;
import lexer.Token;

import java.util.Map;
import java.util.Optional;

class TermNode extends Node {
    Node node;

    private TermNode() {
    }

    public void addChildNode(Node node) {
        this.node = node;
    }

    public static Optional<Node> checkNode(Parser parser) {
        TermNode termNode = new TermNode();
        Node lhsNode = FactorNode.checkNode(parser).orElseThrow();
        while(parser.checkCurrentLexicalType(LexicalType.MUL) || parser.checkCurrentLexicalType(LexicalType.DIV)) {
            Token opToken = parser.getCurrent().orElseThrow();
            parser.getNext();
            termNode.addChildNode(new BinOpNode(opToken, lhsNode, FactorNode.checkNode(parser).orElseThrow()));
            return Optional.of(termNode);
        }
        termNode.addChildNode(lhsNode);
        return Optional.of(termNode);
    }

    @Override
    public Optional<Value> value() {
        return Optional.ofNullable(node.value().orElse(null));
    }

    @Override
    public Optional<Value> eval(Map<String, Value> symbolTable) {
        return node.eval(symbolTable);
    }
}
