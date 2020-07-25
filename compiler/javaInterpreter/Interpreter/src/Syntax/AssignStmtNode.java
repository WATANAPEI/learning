package Syntax;

import Core.Environment;
import Core.LexicalType;
import Core.LexicalUnit;
import Core.Value;

public class AssignStmtNode extends Node{
    LexicalUnit name;
    Node exprNode;

    public AssignStmtNode(NodeType type, Environment env) {
        super(type, env);
    }

    public static Node isMatch(Environment env, LexicalUnit first) {
        if (first.getType() != LexicalType.NAME) {
            return null;
        }
        env.getInput().get();
        LexicalUnit second = env.getInput().get();

        env.getInput().unget(first);
        env.getInput().unget(second);
        if(second.getType() != LexicalType.EQ) {
            return null;
        }
        return new AssignStmtNode(NodeType.ASSIGN_STMT, env);

    }

    @Override
    public boolean parse() {
        name = env.getInput().get();
        // skip eq
        if(!skipExpectNode(LexicalType.EQ)) {
            return false;
        }

        //expect expr node
        exprNode = ExprNode.isMatch(env, peekLexicalUnit());
        if(exprNode != null) {
            return exprNode.parse();
        }
        return false;
    }

    @Override
    public Value eval() {
        Value value = exprNode.eval();
        env.setVarValue(name, value);
        return null;
    }

    @Override
    public String toString() {
        return String.format("%s[%s]", name.getValue().getSValue(), exprNode.toString());
    }
}
