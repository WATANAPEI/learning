package node;

import lexer.LexicalType;
import lexer.NullToken;
import lexer.Token;
import parser.Parser;
import parser.Value;

import java.util.Map;
import java.util.Optional;

class TermNode extends Node {
    Node node;

    private TermNode() {
    }

    public void addChildNode(Node node) {
        this.node = node;
    }

    public static Node checkNode(Parser parser) {
        TermNode termNode = new TermNode();
        Node lhsNode = FactorNode.checkNode(parser);
        while(parser.checkCurrentLexicalType(LexicalType.MUL) || parser.checkCurrentLexicalType(LexicalType.DIV)) {
            Token opToken = parser.getCurrent().orElse(new NullToken());
            parser.getNext();
            termNode.addChildNode(new BinOpNode(opToken, lhsNode, FactorNode.checkNode(parser)));
            return termNode;
        }
        termNode.addChildNode(lhsNode);
        return termNode;
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
