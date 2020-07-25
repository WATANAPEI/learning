package Syntax;

import Core.Environment;
import Core.LexicalType;
import Core.LexicalUnit;
import Core.Value;
import LibFunc.Function;

public class FunctionCallNode extends Node{

    private LexicalUnit name;
    private ExprListNode exprList;

    public FunctionCallNode(NodeType type, Environment env) {
        super(type, env);
    }

    public static Node isMatch(Environment env, LexicalUnit first) {
        // there is no first collection specification
        if(first.getType() != LexicalType.NAME) {
            return null;
        }

        //function call is LL(2)
        env.getInput().get();
        LexicalUnit second = env.getInput().get();
        env.getInput().unget(first);
        env.getInput().unget(second);

        // second LexicalUnit needs to be differ from EQ(AssignStatement)
        if(second.getType() == LexicalType.EQ) {
            return null;
        }
        return new FunctionCallNode(NodeType.FUNCTION_CALL, env);
    }

    @Override
    public boolean parse() {
        name = env.getInput().get();
        if(peekLexicalUnit().getType() == LexicalType.RP) {
            return callSubHandle();
        }
        return callFuncHandle();
    }

    private boolean callSubHandle() {
        if(!skipExpectNode(LexicalType.RP)) {
            return false;
        }
        if(!callFuncHandle()) {
            return false;
        }
        if(!skipExpectNode(LexicalType.LP)) {
            return false;
        }
        return true;
    }

    private boolean callFuncHandle() {
        exprList = (ExprListNode) ExprListNode.isMatch(env, peekLexicalUnit());
        exprList.fcCallEnv = true;
        if(exprList == null || !exprList.parse()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s[%s]", name.getValue().getSValue(), exprList.toString());
    }

    @Override
    public Value eval() {
        Function function = env.getFuncion(name);
        return function.eval(exprList);
    }
}
