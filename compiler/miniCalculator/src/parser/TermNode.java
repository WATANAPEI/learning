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
    Node node;

    private TermNode() {
    }

    public void addChildNode(Node node) {
        this.node = node;
    }

    public static Optional<Node> checkNode(Parser parser) {
        TermNode termNode = new TermNode();
        Token token = parser.peekNext().orElse(new NullToken());
        if(token.tokenType() == TokenType.NUMBER) {
            Node lhsNode = FactorNode.checkNode(parser).orElseThrow();
            while(parser.checkLexicalType(LexicalType.MUL) || parser.checkLexicalType(LexicalType.DIV)) {
                termNode.addChildNode(new BinOpNode(parser.getNext().orElseThrow(), lhsNode, FactorNode.checkNode(parser).orElseThrow()));
                return Optional.of(termNode);
            }
            termNode.addChildNode(lhsNode);
            return Optional.of(termNode);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Value> value() {
        return Optional.ofNullable(node.value().orElse(null));
    }

    @Override
    public Optional<String> eval(Map<String, Value> symbolTable) {
        return node.eval(symbolTable);
    }
}
