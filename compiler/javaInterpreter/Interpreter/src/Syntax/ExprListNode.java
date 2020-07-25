package Syntax;

import Core.Environment;
import Core.LexicalType;
import Core.LexicalUnit;
import Core.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExprListNode extends Node {

    List<Node> childNodes = new ArrayList<>();
    boolean fcCallEnv = false;

    public ExprListNode(NodeType type, Environment env) {
        super(type, env);
    }

    public static ExprListNode isMatch(Environment env, LexicalUnit first) {
        FirstCollection fc = new FirstCollection(
                LexicalType.SUB,
                LexicalType.RP,
                LexicalType.NAME,
                LexicalType.INTVAL,
                LexicalType.DOUBLEVAL,
                LexicalType.LITERAL
        );
        if(fc.contains(first)) {
            return new ExprListNode(NodeType.EXPR_LIST, env);
        }
        return null;
    }

    @Override
    public boolean parse() {
        ExprNode expr = (ExprNode) ExprNode.isMatch(env, peekLexicalUnit());
        expr.fcCallEnv = this.fcCallEnv;
        if(expr == null || !expr.parse()) {
            return false;
        }
        childNodes.add(expr);

        //expression will come again with comma, or OK
        while(skipExpectNode(LexicalType.COMMA)) {
            ExprNode nextExpr = (ExprNode) ExprNode.isMatch(env, peekLexicalUnit());
            nextExpr.fcCallEnv = this.fcCallEnv;
            if(nextExpr == null || !nextExpr.parse()) {
                return false;
            }
            childNodes.add(nextExpr);
        }
        return true;
    }

    @Override
    public String toString() {
        return childNodes.stream()
                .map(Node::toString)
                .collect(Collectors.joining(","));
    }

    public Value getValue(int i) {
        return childNodes.get(i).eval();
    }

    @Override
    public Value eval() {
        throw new UnsupportedOperationException("ExprList needs to be got by getValue(int n) method.");
    }
}
