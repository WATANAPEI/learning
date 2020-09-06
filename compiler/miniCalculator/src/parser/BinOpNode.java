package parser;

import lexer.Token;

import java.util.Optional;

public class BinOpNode extends Node{
    Token op;
    Node lhs;
    Node rhs;
    public BinOpNode(Token op, Node lhs, Node rhs) {
        this.op = op;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public Optional<Value> value() {
        switch(op.lexicalType()) {
            case ADD:
                Integer lvalue = lhs.value().orElseThrow().getIValue().orElseThrow();
                Integer rvalue = rhs.value().orElseThrow().getIValue().orElseThrow();
                return Optional.of(new NumValue(rvalue + lvalue));
            default:
                return Optional.empty();
        }
    }

    @Override
    public void eval() {

    }
}
