package parser;

import lexer.LexicalType;
import lexer.NullToken;
import lexer.Token;
import lexer.TokenType;

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
        while(parser.checkLexicalType(LexicalType.MUL) || parser.checkLexicalType(LexicalType.DIV)) {
            termNode.addChildNode(new BinOpNode(parser.getNext().orElseThrow(), lhsNode, FactorNode.checkNode(parser).orElseThrow()));
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
