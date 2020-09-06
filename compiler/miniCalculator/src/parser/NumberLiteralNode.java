package parser;

import lexer.Token;

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
    public void eval() {
        System.out.println(this.val.getIValue().orElse(null));

    }
}
