package parser;

import lexer.Token;

class NumberLiteralNode extends Node {
    Value val;

    public NumberLiteralNode(Token token) {
        val = new NumValue(token.getImage());
    }

    @Override
    public Value value() {
        return this.val;
    }

    @Override
    public void eval() {
        System.out.println(this.val.getIValue().orElse(null));

    }
}
