package parser;

import lexer.Token;

import java.util.Map;
import java.util.Optional;

class StringLiteralNode extends Node {
    Value val;

    public StringLiteralNode(Token token) {
        val = new StringValue(token.getImage());
    }

    /**
     * Node: Value wraps optional values(String, Integer, Boolean)
     * @return
     */
    @Override
    public Optional<Value> value() {
        return Optional.of(this.val);
    }

    @Override
    public Optional<String> eval(Map<String, Value> symbolTable) {
        return this.val.getSValue();
    }
}
