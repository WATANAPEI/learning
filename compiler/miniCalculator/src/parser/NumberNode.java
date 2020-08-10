package parser;

import lexer.Token;

import java.util.Iterator;

class NumberNode extends Node {
    Value val;

    public NumberNode(Token token, Iterator<Token> itr) {
        val = new NumValue(token.getImage());
    }

    @Override
    public Value value() {
        return this.val;
    }

    @Override
    public Value eval() {
        return this.val;
    }
}
