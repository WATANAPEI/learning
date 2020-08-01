package Syntax;

import Core.*;

/**
 * <FOR> <subst> <TO> <INTVAL> <NL> <stmt_list> <NEXT> <NAME>
 */
public class ForStmtNode extends Node{
    private AssignStmtNode assignStmtNode;
    private LexicalUnit to;
    private StmtListNode stmt;
    private LexicalUnit name;

    public ForStmtNode(NodeType type, Environment env) {
        super(type, env);
    }

    public static Node isMatch(Environment env, LexicalUnit first) {
        //check if FirstCollection contains fist
        FirstCollection fc = new FirstCollection(LexicalType.FOR);
        if(fc.contains(first)) {
            return new ForStmtNode(NodeType.FOR_STMT, env);
        }
        return null;

    }

    @Override
    public boolean parse() {
        // for assign-stmt to intval stmtlist next name
        if(!skipExpectNode(LexicalType.FOR)) {
            return false;
        }
        this.assignStmtNode = (AssignStmtNode) AssignStmtNode.isMatch(env, peekLexicalUnit());
        if(assignStmtNode == null || !assignStmtNode.parse()) {
            return false;
        }
        if(!skipExpectNode(LexicalType.TO)) {
            return false;
        }
        this.to = peekLexicalUnit();
        if (to.getType() != LexicalType.INTVAL) {
            return false;
        }
        env.getInput().get();

        if(!skipExpectNode(LexicalType.NL)) {
            return false;
        }

        this.stmt = (StmtListNode) StmtListNode.isMatch(env, peekLexicalUnit());
        if(stmt == null || !stmt.parse()) {
            return false;
        }
        if(!skipExpectNode(LexicalType.NEXT)) {
            return false;
        }

        this.name = peekLexicalUnit();
        if(name.getType() != LexicalType.NAME) {
            return false;
        }
        env.getInput().get();

        return true;

    }

    @Override
    public Value eval() {
        assignStmtNode.eval();
        while(isContinue()) {
            stmt.eval();
            // increment name
            double newval = env.getVarValue(name).getDValue() + 1;
            env.setVarValue(name, new ValueImpl(newval));

        }
        return null;
    }

    private boolean isContinue() {
        //check if name is less than to
        int toVal = this.to.getValue().getIValue();
        double nameVal = env.getVarValue(name).getDValue();

        return nameVal <= toVal;
    }

    @Override
    public String toString() {
        return String.format("FOR[%s TO %s {%s}]",
                assignStmtNode.toString(),
                to.getValue().getSValue(),
                stmt.toString());
    }
}