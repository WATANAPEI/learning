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
    public Optional<Value> eval(Map<String, Value> symbolTable) {
        Integer lvalue = lhs.eval(symbolTable).orElseThrow().getIValue().orElseThrow();
        Integer rvalue = rhs.eval(symbolTable).orElseThrow().getIValue().orElseThrow();
        switch(op.lexicalType()) {
            case ADD:
                return Optional.of(new NumValue(lvalue + rvalue));
            case SUB:
                return Optional.of(new NumValue(lvalue - rvalue));
            case MUL:
                return Optional.of(new NumValue(lvalue * rvalue));
            case DIV:
                if (rvalue == 0) {
                    throw new ArithmeticException("0 division occur.");
                }
                return Optional.of(new NumValue(lvalue / rvalue));
            case GT:
                return Optional.of(new BooleanValue(lvalue > rvalue));
            case LT:
                return Optional.of(new BooleanValue(lvalue < rvalue));
            case EQ:
                return Optional.of(new BooleanValue(lvalue == rvalue));
            case NE:
                return Optional.of(new BooleanValue(lvalue != rvalue));
            case GE:
                return Optional.of(new BooleanValue(lvalue >= rvalue));
            case LE:
                return Optional.of(new BooleanValue(lvalue <= rvalue));
            default:
                return Optional.empty();
        }
    }
}
