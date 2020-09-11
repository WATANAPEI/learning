package parser;

import lexer.LexicalType;
import lexer.NullToken;
import lexer.Token;
import lexer.TokenType;

import java.util.Map;
import java.util.Optional;

/**
 * <Root> := {<Stmt>}
 * <Stmt> := <Expr> | <String> | <Word> <=> <Expr>
 * <Expr> := <Term> { <+|-> <Term>}
 * <Term> := <Factor> { <*|/> <Factor>}
 * <Factor> := <Number>
 * @return
 */

class TermNode extends Node {

    public TermNode() {
    }

    public Optional<Node> checkNode(Parser parser) {
        Token token = parser.peekNext().orElse(new NullToken());
        if(token.tokenType() == TokenType.NUMBER) {
            Node lhsNode = new FactorNode().checkNode(parser).orElseThrow();
            while(parser.checkLexicalType(LexicalType.MUL) || parser.checkLexicalType(LexicalType.DIV)) {
                return Optional.of(new BinOpNode(parser.getNext().orElseThrow(), lhsNode, new FactorNode().checkNode(parser).orElseThrow()));
            }
            return Optional.of(lhsNode);
        } else {
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
