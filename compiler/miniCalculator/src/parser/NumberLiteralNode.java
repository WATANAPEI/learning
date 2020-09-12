package parser;

import lexer.NullToken;
import lexer.Token;
import lexer.TokenType;

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
    public void eval(Map<String, Value> symbolTable) {
        System.out.println(this.val.getIValue().orElse(null));

    }
}
