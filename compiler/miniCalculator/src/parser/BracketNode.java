package parser;

import lexer.LexicalType;
import lexer.NullToken;
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

    public void addChild(Node node) {
        this.child = node;
    }

    public static Optional<Node> checkNode(Parser parser) {
        BracketNode bracketNode = new BracketNode();
        if(parser.checkLexicalType(LexicalType.OPEN_BRA)) {
            parser.consume(LexicalType.OPEN_BRA);
            Node node = ExprNode.checkNode(parser).orElse(null);
            bracketNode.addChild(node);
            if(parser.checkLexicalType(LexicalType.CLOSE_BRA)) {
                parser.consume(LexicalType.CLOSE_BRA);
                return Optional.ofNullable(bracketNode);
            } else {
                throw new IllegalStateException("No closing bracket.");
            }
        }
        Node node = ExprNode.checkNode(parser).orElse(null);
        bracketNode.addChild(node);
        return Optional.ofNullable(bracketNode);
    }

    @Override
    public Optional<Value> value() {
        return child.value();
    }

    @Override
    public Optional<String> eval(Map<String, Value> symbolTable) {
        return child.eval(symbolTable);
    }
}
