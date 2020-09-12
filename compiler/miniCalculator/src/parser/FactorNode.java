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

    private FactorNode() {
    }

    public static Optional<Node> checkNode(Parser parser) {
        Token token = parser.peekNext().orElse(new NullToken());
        if(token.tokenType() == TokenType.NUMBER) {
            token = parser.getNext().orElseThrow();
            return Optional.of(new NumberLiteralNode(token));
        }else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Value> value() {
        return Optional.empty();
    }

    @Override
    public void eval(Map symbolTable) {

    }
}
