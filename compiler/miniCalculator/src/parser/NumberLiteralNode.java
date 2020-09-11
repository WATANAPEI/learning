package parser;

import lexer.NullToken;
import lexer.Token;
import lexer.TokenType;

import java.util.Map;
import java.util.Optional;

class NumberLiteralNode extends Node {
    Value val;

    public NumberLiteralNode() {

    }

    public NumberLiteralNode(Token token) {
        val = new NumValue(token.getImage());
    }

    public Optional<Node> checkNode(Parser parser) {
        Token token = parser.peekNext().orElse(new NullToken());
        if(token.tokenType() == TokenType.NUMBER) {
            token = parser.getNext().orElseThrow();
            return Optional.of(new NumberLiteralNode(token));
        }else {
            return Optional.empty();
        }

    }

    @Override
    public Optional<Value> value() {
        return Optional.of(this.val);
    }

    @Override
    public void eval(Map symbolTable) {
        System.out.println(this.val.getIValue().orElse(null));

    }
}
