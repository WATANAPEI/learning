package node;

import lexer.Token;
import parser.NumValue;
import parser.Value;

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
    public Optional<Value> eval(Map<String, Value> symbolTable, Map<String, Node> functionTable) {
        return Optional.of(this.val);
    }
}
