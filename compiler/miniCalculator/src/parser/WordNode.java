package parser;

import lexer.Token;
import lexer.TokenType;

import java.util.Map;
import java.util.Optional;

/**
 * <Root> := {<Stmt>}
 * <Stmt> := <Expr> | <String> | <Assign>
 * <Assign> := <Word> <=> <Expr>
 * <Expr> := <Term> { <+|-> <Term>}
 * <Term> := <Factor> { <*|/> <Factor>}
 * <Factor> := <Number>
 * @return
 */

class WordNode extends Node {
    Value val;

    public WordNode(Token token) {
        val = new StringValue(token.getImage());
    }

    public static Optional<Node> checkNode(Parser parser) {
        Token token = parser.getNext().orElseThrow();
        if(token.tokenType() == TokenType.WORD) {
            return Optional.of(new WordNode(token));
        } else {
            return Optional.empty();
        }
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
    public void eval(Map<String, Value> symbolTable) {
        Value value = symbolTable.get(value().orElseThrow().getSValue().orElse(null));
        System.out.println(value.getSValue()
                .orElse("This variable doesn't have value."));

    }
}
