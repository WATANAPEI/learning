package parser;

import lexer.Token;

import java.util.Map;
import java.util.Optional;

class NumberLiteralNode extends Node {
    Value val;


    public NumberLiteralNode(Token token) {
        val = new NumValue(token.getImage());
    }

    @Override
    public Optional<Value> value() {
        return Optional.of(this.val);
    }

    @Override
    public Optional<Value> eval(Map<String, Value> symbolTable) {
        return Optional.of(this.val);
    }
}
