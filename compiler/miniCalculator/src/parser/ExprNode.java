package parser;

import lexer.LexicalType;
import lexer.Token;

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

class ExprNode extends Node {
    Node node;

    private ExprNode() {
    }

    public void addChildNode(Node node) {
        this.node = node;
    }

    public static Optional<Node> checkNode(Parser parser) {
        ExprNode exprNode = new ExprNode();
        Node lhsNode = TermNode.checkNode(parser).orElseThrow();
        while(parser.checkCurrentLexicalType(LexicalType.ADD)
                || parser.checkCurrentLexicalType(LexicalType.SUB)) {
            Token opToken = parser.getCurrent().orElseThrow();
            parser.getNext(); // proceed a token
            exprNode.addChildNode(new BinOpNode(opToken, lhsNode, TermNode.checkNode(parser).orElseThrow()));
            return Optional.of(exprNode);
        }
        if(parser.checkCurrentLexicalType(LexicalType.GT)
                || parser.checkCurrentLexicalType(LexicalType.LT)
                || parser.checkCurrentLexicalType(LexicalType.EQ)) {
            Token opToken = parser.getCurrent().orElseThrow();
            parser.getNext(); // proceed a token
            exprNode.addChildNode(new BinOpNode(opToken,lhsNode, TermNode.checkNode(parser).orElseThrow()));
            return Optional.of(exprNode);
        }
        exprNode.addChildNode(lhsNode);
        //parser.getNext(); // proceed a token
        return Optional.of(exprNode);
    }

    @Override
    public Optional<Value> value() {
        return Optional.ofNullable(node.value().orElse(null));
    }

    @Override
    public Optional<Value> eval(Map<String, Value> symbolTable) {
        return node.eval(symbolTable);
    }
}
