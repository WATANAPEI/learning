package parser;

import lexer.Token;

import java.util.Map;
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
        Integer lvalue = lhs.value().orElseThrow().getIValue().orElseThrow();
        Integer rvalue = rhs.value().orElseThrow().getIValue().orElseThrow();
        switch(op.lexicalType()) {
            case ADD:
                return Optional.of(new NumValue(rvalue + lvalue));
            case SUB:
                return Optional.of(new NumValue(rvalue - lvalue));
            case MUL:
                return Optional.of(new NumValue(rvalue * lvalue));
            case DIV:
                if (rvalue == 0) {
                    throw new ArithmeticException("0 division occur.");
                }
                return Optional.of(new NumValue(rvalue - lvalue));
            default:
                return Optional.empty();
        }
    }

    @Override
    public void eval(Map symbolTable) {
        value().ifPresent(e -> System.out.println(e.getIValue().orElseThrow()));

    }
}