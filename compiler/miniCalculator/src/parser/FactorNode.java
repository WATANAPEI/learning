package parser;

import lexer.LexicalType;
import lexer.NullToken;
import lexer.Token;
import lexer.TokenType;

import java.util.Map;
import java.util.Optional;

class FactorNode extends Node {
    Node node;

    private FactorNode() {
    }

    public void addChildNode(Node node) {
        this.node = node;
    }

    public static Optional<Node> checkNode(Parser parser) {
        FactorNode factorNode = new FactorNode();
        Token token = parser.peekNext().orElse(new NullToken());
        if(token.tokenType() == TokenType.NUMBER) {
            token = parser.getNext().orElseThrow();
            factorNode.addChildNode(new NumberLiteralNode(token));
            return Optional.of(factorNode);
        }else if(token.tokenType() == TokenType.WORD){
            factorNode.addChildNode(new WordNode(token));
            return Optional.of(factorNode);
        }else {
            return Optional.empty();
        }
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
