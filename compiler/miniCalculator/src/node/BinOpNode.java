package node;

import lexer.Token;
import parser.NumValue;
import parser.Value;

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
        int lvalue = lhs.value().orElseThrow().getIValue().orElseThrow();
        int rvalue = rhs.value().orElseThrow().getIValue().orElseThrow();
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
    public Optional<Value> eval(Map<String, Value> symbolTable, Map<String, Node> functionTable) {
        int lvalue = lhs.eval(symbolTable, functionTable).orElseThrow().getIValue().orElseThrow();
        int rvalue = rhs.eval(symbolTable, functionTable).orElseThrow().getIValue().orElseThrow();
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
            default:
                return Optional.empty();
        }
    }
}
