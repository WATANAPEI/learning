package parser;

import lexer.LexicalType;
import lexer.NullToken;
import lexer.Token;
import lexer.TokenType;

import java.util.Map;
import java.util.Optional;

/**
 * <Root> := {<Stmt>}
 * <Stmt> := <Term> { <+|-> <Term>} | <String>
 * <Term> := <Factor> { <*|/> <Factor>}
 * <Factor> := <Number>
 * @return
 */
class FactorNode extends Node {

    public FactorNode() {
    }

    public Optional<Node> checkNode(Parser parser) {
        return new NumberLiteralNode().checkNode(parser);
    }

    @Override
    public Optional<Value> value() {
        return Optional.empty();
    }

    @Override
    public void eval(Map symbolTable) {

    }
}
