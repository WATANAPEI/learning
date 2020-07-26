package Syntax;

import Core.Environment;
import Core.LexicalType;
import Core.LexicalUnit;
import Core.Value;

public class LoopBlockNode extends Node{
    // DO while statement or not which means if it runs once or not
    private boolean isDo;
    //while statement or not if until statement this variable is false
    private boolean isWhile;
    //loop condition node
    private CondNode condNode;
    //loop block
    private StmtListNode stmtListNode;

    public LoopBlockNode(NodeType type, Environment env) {
        super(type, env);
    }

    static FirstCollection fc = new FirstCollection(
            LexicalType.WHILE,
            LexicalType.DO
    );

    public static Node isMatch(Environment env, LexicalUnit first) {
        if(fc.contains(first)) {
            return new LoopBlockNode(NodeType.LOOP_BLOCK, env);
        }
        return null;
    }

    @Override
    public boolean parse() {
        LexicalUnit first = env.getInput().get();
        //DO
        if(first.getType() == LexicalType.DO) {
            return doParse();
        }
        //WHILE
        if(first.getType() == LexicalType.WHILE) {
            return whileParse();
        }
        return false;
    }

    private boolean doParse() {
        isDo = true;

        LexicalUnit next = env.getInput().get();
        switch (next.getType()) {
            case WHILE:
            case UNTIL:
                // do - (while or until) - cond - nl - stmtlist - loop nl
                untilOrWhileHandle(next);
                if(!condHandle()) {
                    return false;
                }
                if(!skipExpectNode(LexicalType.NL)) {
                    return false;
                }
                if(!stmtListHandle()) {
                    return false;
                }
                if(!skipExpectNode(LexicalType.LOOP, LexicalType.NL)) {
                    return false;
                }
                return true;
            case NL:
                // do - nl - stmtlist - loop - (while or until) - cond - nl
                if(!stmtListHandle()) {
                    return false;
                }
                if(!skipExpectNode(LexicalType.LOOP)) {
                    return false;
                }
                if(!untilOrWhileHandle(env.getInput().get()))  {
                    return false;
                }
                if(!condHandle()) {
                    return false;
                }
                if(!skipExpectNode(LexicalType.NL)) {
                    return false;
                }
                return true;
            default:
                return false;
        }
    }

    //parse while
    private boolean whileParse() {
        isDo = false;
        isWhile = true;
        //while -> cond -> nl -> stmtlist -> loop -> nl
        if(!condHandle()) {
            return false;
        }
        if(!skipExpectNode(LexicalType.NL)) {
            return false;
        }
        if(!stmtListHandle()) {
            return false;
        }
        if(!skipExpectNode(LexicalType.LOOP, LexicalType.NL)) {
            return false;
        }
        return true;
    }

    //condition expression analyzer in loop block
    private boolean condHandle() {
        condNode = (CondNode) CondNode.isMatch(env, peekLexicalUnit());
        if(condNode == null || condNode.parse()) {
            return false;
        }
        return true;
    }

    //statement part
    private boolean stmtListHandle() {
        stmtListNode = (StmtListNode) StmtListNode.isMatch(env, peekLexicalUnit());
        if(stmtListNode == null || stmtListNode.parse()) {
            return false;
        }
        return true;
    }

    //check if until statement or while statement
    private boolean untilOrWhileHandle(LexicalUnit unit) {
        switch (unit.getType()) {
            case WHILE:
                isWhile = true;
                return true;
            case UNTIL:
                isWhile = false;
                return true;
        }
        return false;
    }

    @Override
    public Value eval() {
        if(isDo) {
            stmtListNode.eval();
        }
        while(isContinue()) {
            stmtListNode.eval();
        }
        return null;
    }

    private boolean isContinue() {
        boolean cond = condNode.eval().getBValue();
        if(!isWhile) {
            cond = !cond;
        }
        return cond;
    }

    @Override
    public String toString() {
        return "LOOP[" + condNode + "[" + stmtListNode + "]]";
    }
}
