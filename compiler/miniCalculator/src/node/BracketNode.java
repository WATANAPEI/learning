package node;

import lexer.LexicalType;
import lexer.NullToken;
import lexer.Token;
import lexer.TokenType;
import parser.Parser;
import parser.Value;

import java.util.Map;
import java.util.Optional;

/**
 * <Root> := {<Stmt>}
 * <Stmt> := <Expr> | <String> | <Assign>
 * <Assign> := <Word> <=> <Expr>
 * <Expr> := <Term> { <+|-> <Term>}
 * <Term> := <Factor> { <*|/> <Factor>}
 * <Factor> := <(> <Number> | <Word>  | <Expression> <)>
 * @return
 */
class BracketNode extends Node {
    Node child;

    private BracketNode() {
    }

    private BracketNode(Node child) {
        this.child = child;
    }

    public void addChildNode(Node node) {
        this.child = node;
    }

    public static Optional<Node> checkNode(Parser parser) {
        BracketNode bracketNode = new BracketNode();
        Token token = parser.getCurrent().orElse(new NullToken());
        if(token.tokenType() == TokenType.SINGLE_SYMBOL
                && parser.checkCurrentLexicalType(LexicalType.OPEN_BRA)) {
            parser.consume(LexicalType.OPEN_BRA);
            ExprNode.checkNode(parser).ifPresent(bracketNode::addChildNode);
            if(parser.checkCurrentLexicalType(LexicalType.CLOSE_BRA)) {
                parser.consume(LexicalType.CLOSE_BRA);
                return Optional.ofNullable(bracketNode);
            } else {
                throw new IllegalStateException("No closing bracket.");
            }
        }
        throw new IllegalStateException("Parsing error: BracketNode");
    }

    @Override
    public Optional<Value> value() {
        return child.value();
    }

    @Override
    public Optional<Value> eval(Map<String, Value> symbolTable) {
        return child.eval(symbolTable);
    }
}
