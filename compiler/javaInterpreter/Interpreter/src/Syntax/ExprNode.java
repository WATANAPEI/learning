package Syntax;

import Core.*;

import javax.swing.undo.UndoableEditSupport;
import java.util.*;

public class ExprNode extends Node{

    //the end of expression
    private ExprEnd expression;

    //operator expression
    //don't necessarily exist both children(left and right)
    private ExprEnd ope;
    private ExprNode left, right;

    public ExprNode(NodeType type, Environment env) {
        super(type, env);
    }

    public ExprNode(Environment env) {
        super(NodeType.EXPR, env);
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
            Node node = new ExprNode(NodeType.EXPR, env);
            return node;
        }
        return null;
    }

    private static final Set<LexicalType> allowSet = new HashSet<>() {
        {
            add(LexicalType.NAME);
            add(LexicalType.LP);
            add(LexicalType.RP);
            add(LexicalType.INTVAL);
            add(LexicalType.DOUBLEVAL);
            add(LexicalType.LITERAL);
            add(LexicalType.ADD);
            add(LexicalType.SUB);
            add(LexicalType.MUL);
            add(LexicalType.DIV);
        }
    };

    //value to determine if closing paren is the end of expression or the end of function call.
    boolean fcCallEnv = false;
    private int nest = 0;

    @Override
    public boolean parse() {
        // result container
        Deque<ExprEnd> output = new ArrayDeque();

        //checker for uniop
        boolean expectValue = true;
        //buffer for uniop
        Deque<ExprEnd> singleOpeEnd = new ArrayDeque();

        //operator stack
        Deque<LexicalUnit> opeStack = new ArrayDeque();
        while(true) {
            final LexicalUnit in = peekLexicalUnit();
            final LexicalType inType = in.getType();

            if (!allowSet.contains(inType)) {
                break;
            }
            env.getInput().get();

            //CALL SUB
            if (inType == LexicalType.NAME) {
                // NAME -> RP is CALL SUB
                LexicalUnit test = env.getInput().get();
                if (test.getType() == LexicalType.RP) {
                    // brig back lexical unit to parse FCN
                    env.getInput().unget( int);
                    env.getInput().unget(test);
                    //CALL SUB always returns one value and can be handled in the same way of NUM
                    FunctionCallNode fcN = new FnctionCallNode(NodeType.FUNCTION_CALL, env);
                    if (!fcN.parse()) {
                        return false;
                    }

                    //output the whole CALL SUB
                    output.add(new ExprEnd(fcN, env));
                    expectValue = false;
                    continue;
                }
                env.getInput().unget(test);

            }

            if (inType == LexicalType.RP) {
                // RP ( into opeStack
                opeStack.push(in);
                nest++;
                continue;
            }

            if (inType == LexicalType.LP) {
                // keep outputting until RP come out from opeStack when LP comes
                if (fcCallEnv && nest == 0) {
                    env.getInput().unget(in);
                    break;
                }
                LexicalUnit tmp;
                if (opeStack.size() == 0) {
                    return false;
                }
                while ((tmp = opeStack.pop()).getType() != LexicalType.RP) {
                    output.add(new ExprEnd(tmp, env));
                    // combination of () is wrong then occur syntax error
                    if (opeStack.size() == 0) {
                        return false;
                    }
                }
                nest--;
                continue;
            }

            final int priority = getPriority(inType);
            if (priority != 0) {
                //when unioperator appears
                if (expectValue) {
                    if (inType == LexicalType.SUB) {
                        // priority of uni operator is highest
                        ExprEnd subEnd = new ExprEnd(in, env);
                        subEnd.isSingleOpe = true;
                        singleOpeEnd.add(subEnd);
                    }
                    continue;
                }

                // output all unioperator with the highest priority
                while (singleOpeEnd.size() != 0) {
                    output.add(singleOpeEnd.poll());
                }

                //not only unioperator but also all operators
                while (opeStack.size() != 0 && getPriority(opeStack.peekFirst().getType()) >= priority) {
                    output.add(new ExprEnd(opeStack.pop(), env));
                }
                expectValue = true;
                opeStack.push(in);
            } else {
                output.add(new ExprEnd(in, env));
                expectValue = false;
            }
        }
        while(singleOpeEnd.size() != 0) {
            output.add(singleOpeEnd.poll());
        }
        while(opeStack.size() != 0) {
            output.add(new ExprEnd(opeStack.pop(), env));
        }
        // -----------------------
        // Until here pushed into output in the form of Reverse Polish Notation
        // -----------------------

        // make output tree
        Deque<ExprNode> nodeStack = new ArrayDeque();
        while(output.size() != 0) {
            ExprNode in = output.pop();

            if(in instanceof ExprEnd) {
                ExprEnd endElm = (ExprEnd) in;
                if(endElm.isOpe) {

                    //unioperator
                    if(endElm.isSingleOpe == true) {
                        if(nodeStack.size() < 1) {
                            return false;
                        }
                        ExprNode expr = new ExprNode(env);
                        expr.left = nodeStack.pop();
                        expr.ope = endElm;
                        nodeStack.push(expr);
                        continue;
                    }

                    //bioperator
                    if(nodeStack.size() < 2) {
                        return false;
                    }
                    ExprNode expr = new ExprNode(env);
                    expr.right = nodeStack.pop();
                    expr.left = nodeStack.pop();
                    expr.ope = endElm;

                    nodeStack.push(expr);
                    continue;

                }

                nodeStack.push(endElm);
            }
        }
        ExprNode expr = nodeStack.pop();
        if (expr.isEnd()) {
            this.expression = (ExprEnd) expr;
        } else {
            this.left = expr.left;
            this.right = expr.right;
            this.ope = expr.ope;
        }
        return true;
    }

    private int getPriority(LexicalType type) {
        // 0: number
        switch(type) {
            case MUL:
            case DIV:
                return 3;
            case SUB:
            case ADD:
                return 2;
            case RP:
            case LP:
                return -1;

            case INTVAL:
            case DOUBLEVAL:
            case LITERAL:
            case NAME:
                return 0;

            default:
                throw new IllegalArgumentException("No priority set:" + type.name());

        }
    }

    /**
     * true when +, -, *, /
     * @param type
     * @return
     */
    public boolean isOpe(LexicalType type) {
        Set<LexicalType> opeSet = new HashSet() {{
            add(LexicalType.ADD);
            add(LexicalType.SUB);
            add(LexicalType.MUL);
            add(LexicalType.DIV);
        }};
        return opeSet.contains(type);
    }

    /**
     * check if this Expr is the end
     * @return
     */
    protected boolean isEnd() {
        if(left == null) {
            return true;
        }
        return false;
    }

    @Override
    public Value eval() {
        // expression end
        if(isEnd()) {
            return expression.eval();
        }
        //uni term expression
        if(isSingle()) {
            return evalSingleExpr();
        }
        // bi term expression
        return evalBinaryExpr();
    }

    private boolean isSingle() {
        if (left != null && right == null) {
            return true;
        }
        return false;
    }

    //evaluate uni term expression
    private Value evalSingleExpr() {
        LexicalType opeType = ope.val.getType();
        Value leftValue = left.eval();

        // unioperator(now only -)
        ValueType evalType = leftValue.getType();

        switch(evalType) {
            case INTEGER:
                return new ValueImpl(-leftValue.getIValue());
            case DOUBLE:
                return new ValueImpl(-leftValue.getDValue());
        }
        throw new UnsupportedOperationException("only - operator is allowed for now");
    }

    //evaluate biterm expression
    private Value evalBinaryExpr() {
        LexicalType opeType = ope.val.getType();

        Value rightValue = right.eval();
        Value leftValue = left.eval();

        ValueType evalType = evalValueType(rightValue, leftValue);

        switch (opeType) {
            case ADD:
                if (evalType == ValueType.STRING) {
                    return new ValueImpl(leftValue.getSValue() + rightValue.getSValue());
                }
                if (evalType == ValueType.DOUBLE) {
                    return new ValueImpl(leftValue.getDValue() + rightValue.getDValue());
                }
                if (evalType == ValueType.INTEGER) {
                    return new ValueImpl(leftValue.getIValue() + rightValue.getIValue());
                }
                break;

            case SUB:
                if (evalType == ValueType.STRING) {
                    throw new UnsupportedOperationException("String is not supported for subtract");
                }
                if (evalType == ValueType.DOUBLE) {
                    return new ValueImpl(leftValue.getDValue() - rightValue.getDValue());
                }
                if (evalType == ValueType.INTEGER) {
                    return new ValueImpl(leftValue.getIValue() - rightValue.getIValue());
                }
                break;

            case MUL:
                if (evalType == ValueType.STRING) {
                    throw new UnsupportedOperationException("String is not supported for multiplication");
                }
                if (evalType == ValueType.DOUBLE) {
                    return new ValueImpl(leftValue.getDValue() * rightValue.getDValue());
                }
                if (evalType == ValueType.INTEGER) {
                    return new ValueImpl(leftValue.getIValue() * rightValue.getIValue());
                }
                break;

            case DIV:
                if (evalType == ValueType.STRING) {
                    throw new UnsupportedOperationException("String is not supported for division");
                }
                if (evalType == ValueType.DOUBLE) {
                    return new ValueImpl(leftValue.getDValue() / rightValue.getDValue());
                }
                if (evalType == ValueType.INTEGER) {
                    return new ValueImpl(leftValue.getIValue() / rightValue.getIValue());
                }
                break;

            default:
                break;

        }
        throw new UnsupportedOperationException("Unknown operation");
    }

    //return the value type when rvalue and lvalue is different
    private ValueType evalValueType(Value right, Value left) {
        List<ValueType> list = new ArrayList();
        list.add(right.getType());
        list.add(left.getType());

        if (list.contains(ValueType.STRING)) {
            return ValueType.STRING;
        }
        if(list.contains(ValueType.DOUBLE)) {
            return ValueType.DOUBLE;
        }
        if(list.contains(ValueType.INTEGER)) {
            return ValueType.INTEGER;
        }
        if(list.contains(ValueType.BOOL)) {
            return ValueType.BOOL;
        }
        return ValueType.VOID;
    }

    @Override
    public String toString() {
        //end
        if(isEnd()) {
            return expression.toString();
        }
        //uniop
        if(this.right == null) {
            return String.format("%s[%s]", ope.toString(), left.toString());
        }
        //biope
        return String.format("%s[%s, %s]", ope.toString(), left.toString(), right.toString());
    }
}

/**
 * End Node of Expr
 */
class ExprEnd extends ExprNode {
    boolean isOpe;
    boolean isSingleOpe;
    LexicalUnit val;
    boolean isFcval;
    FunctionCallNode fcval;

    public ExprEnd(LexicalUnit val, Environment env) {
        super(env);
        this.val = val;
        this.isOpe = isOpe(val.getType());
    }

    public ExprEnd(FunctionCallNode fcN, Environment env) {
        super(env);
        this.fcval = fcN;
        this.isFcval = true;
        isOpe = false;
    }

    @Override
    public String toString() {
        if(isFcval) {
            return fcval.toString();
        }
        if(isOpe) {
            return val.getType().name();
        }
        return val.getValue().getSValue();
    }

    @Override
    protected boolean isEnd() {
        return true;
    }

    @Override
    public Value eval() {
        if(isFcval) {
            return fcval.eval();
        }
        if(val.getType() == LexicalType.NAME) {
            Value res = env.getVarValue(val);
            return res;
        }
        return val.getValue();

    }
}