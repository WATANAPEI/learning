package Syntax;

import Core.*;

import java.util.ArrayList;
import java.util.List;

public class CondNode extends Node {
    ExprNode left;
    LexicalUnit operator;
    ExprNode right;

    public CondNode(NodeType type, Environment env) {
        super(type, env);
    }

    public static Node isMatch(Environment env, LexicalUnit first) {
        FirstCollection fc = new FirstCollection(
                LexicalType.SUB,
                LexicalType.RP,
                LexicalType.NAME,
                LexicalType.INTVAL,
                LexicalType.DOUBLEVAL,
                LexicalType.LITERAL
        );

        if(fc.contains(first)) {
            Node node = new CondNode(NodeType.COND, env);
        }
        return null;
    }

    private static List<LexicalType> allowOperator = new ArrayList() {{
        add(LexicalType.EQ);
        add(LexicalType.GT);
        add(LexicalType.LT);
        add(LexicalType.GE);
        add(LexicalType.LE);
    }};

    @Override
    public boolean parse() {
        //parse left
        this.left = (ExprNode) ExprNode.isMatch(env, peekLexicalUnit());
        if(this.left == null || !this.left.parse()) {
            return false;
        }

        //parse operator
        LexicalUnit ope = env.getInput().get();
        if(!allowOperator.contains(ope.getType())) {
            return false;
        }
        this.operator = ope;

        //parse right
        this.right = (ExprNode)ExprNode.isMatch(env, peekLexicalUnit());
        if(this.right == null || !this.right.parse()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s[%s:%s]", operator, left, right);

    }

    @Override
    public Value eval() {
        Value leftValue = left.eval();
        Value rightValue = right.eval();

        boolean res = false;
        switch (operator.getType()) {
            case EQ:
                res = getEvalStr(leftValue).equals(getEvalStr(rightValue));
                break;
            case LT:
                res = leftValue.getDValue() < rightValue.getDValue();
                break;
            case GT:
                res = leftValue.getDValue() > rightValue.getDValue();
                break;
            case GE:
                res = leftValue.getDValue() >= rightValue.getDValue();
                break;
            case LE:
                res = leftValue.getDValue() <= rightValue.getDValue();
                break;
        }
        return new ValueImpl(res);

    }

    private String getEvalStr(Value val) {
        if(val.getType() == ValueType.STRING) {
            return val.getSValue();
        }
        return val.getDValue() + "";
    }
}
