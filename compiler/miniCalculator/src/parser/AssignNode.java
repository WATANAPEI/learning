package parser;

import lexer.Token;

import java.util.Map;
import java.util.Optional;

class AssignNode extends Node {
    Node lhs;
    Node rhs;

    public AssignNode(Node lhs, Node rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public Optional<Value> value() {
        return Optional.empty();
    }

    @Override
    public void eval(Map symbolTable) {


    }
}
