package node;

import lexer.LexicalType;
import lexer.NullToken;
import lexer.Token;
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

    public static Node checkNode(Parser parser) {
        ExprNode exprNode = new ExprNode();
        Node lhsNode = TermNode.checkNode(parser);
        while(parser.checkCurrentLexicalType(
                LexicalType.ADD,
                LexicalType.SUB
        )) {
            Token opToken = parser.getCurrent()
                    .orElseThrow(() -> new IllegalStateException("No Token."));
            parser.getNext(); // proceed a token
            exprNode.addChildNode(new BinOpNode(opToken, lhsNode, TermNode.checkNode(parser)));
            return exprNode;
        }
        if(parser.checkCurrentLexicalType(
                LexicalType.GT,
                LexicalType.LT,
                LexicalType.GE,
                LexicalType.LE,
                LexicalType.NE,
                LexicalType.EQ
        )) {
            Token opToken = parser.getCurrent()
                    .orElseThrow(() -> new IllegalStateException("No Token."));
            parser.getNext(); // proceed a token
            exprNode.addChildNode(new BinOpNode(opToken,lhsNode, TermNode.checkNode(parser)));
            return exprNode;
        }
        exprNode.addChildNode(lhsNode);
        //parser.getNext(); // proceed a token
        return exprNode;
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
