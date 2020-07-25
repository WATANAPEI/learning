package Syntax;

import Core.Environment;
import Core.LexicalType;
import Core.LexicalUnit;
import Core.Value;

import java.util.ArrayList;
import java.util.List;

public class IFBlockNode extends Node{
    //IF condition node
    private CondNode cond;

    //stmt node
    private StmtListNode ifStmtList, elseStmtListNode;

    //stmt node
    private StmtNode ifStmtNode, elseStmtNode;

    // if block following this if stmt
    private final List<IFBlockNode> followIFBlockNodes = new ArrayList();

    public IFBlockNode(NodeType type, Environment env) {
        super(type, env);
    }

    static FirstCollection fc = new FirstCollection(LexicalType.IF);

    public static Node isMatch(Environment env, LexicalUnit first) {
        if(fc.contains(first)) {
            return new IFBlockNode(NodeType.IF_BLOCK, env);
        }
        return null;
    }

    @Override
    public boolean parse() {
        //if-cond-then
        if(!skipExpectNode(LexicalType.IF)) {
            return false;
        }

        cond = (CondNode) CondNode.isMatch(env, peekLexicalUnit());
        if(cond == null || !cond.parse()) {
            return false;
        }
        if(!skipExpectNode(LexicalType.THEN)) {
            return false;
        }

        //nl and stmt : when nl comes then stmtList comes next
        if(skipExpectNode(LexicalType.NL)) {
            //stmtList
            ifStmtList = stmtListHandle();
            if(ifStmtList == null) {
                return false;
            }

            //else block
            if(!elseBlockHandle()) {
                return false;
            }

            //end if
            if(!skipExpectNode(LexicalType.ENDIF)) {
                return false;
            }
        } else {
            // stmt
            ifStmtNode = stmtHandle();
            if(ifStmtNode == null) {
                return false;
            }

            //else comes then stmt comes
            // if not finish
            if(skipExpectNode(LexicalType.ELSE)) {
                elseStmtNode = stmtHandle();
                if (elseStmtNode == null) {
                    return false;
                }
            }
        }

        //last nl
        if(!skipExpectNode(LexicalType.NL)) {
            return false;
        }
        return true;
    }

    private StmtNode stmtHandle() {
        StmtNode ret = (StmtNode) StmtNode.isMatch(env, peekLexicalUnit());
        if(ret == null || !ret.parse()) {
            return null;
        }
        return ret;
    }

    private StmtListNode stmtListHandle() {
        StmtListNode ret = (StmtListNode) StmtListNode.isMatch(env, peekLexicalUnit());
        if(ret == null || !ret.parse()) {
            return null;
        }
        return ret;
    }

    private boolean elseBlockHandle() {
        //else-if-block's first collection is ELSEIF
        while(skipExpectNode(LexicalType.ELSEIF)) {
            IFBlockNode node = new IFBlockNode(NodeType.IF_BLOCK, env);
            if(!node.elseIfBlockParser()) {
                return false;
            }
            followIFBlockNodes.add(node);
        }

        if(skipExpectNode(LexicalType.ELSE, LexicalType.NL)) {
            //else
            elseStmtListNode = stmtListHandle();
            return elseStmtListNode != null;
        }
        return true;
    }

    private boolean elseIfBlockParser() {
        cond = (CondNode) CondNode.isMatch(env, peekLexicalUnit());
        if(cond == null || !cond.parse()) {
            return false;
        }
        if(!skipExpectNode(LexicalType.THEN, LexicalType.NL)) {
            return false;
        }
        this.ifStmtList = stmtListHandle();
        if(ifStmtList == null) {
            return false;
        }
        return true;
    }

    //eval block when condition is true
    public Node getThenNode() {
        if(ifStmtNode == null) {
            return ifStmtList;
        }
        return ifStmtNode;
    }

    //eval block when condition is false
    public Node getElseNode() {
        if(elseStmtNode == null) {
            return elseStmtListNode;
        }
        return elseStmtNode;
    }

    @Override
    public Value eval() {
        Value condValue = cond.eval();
        if(condValue.getBValue()) {
            getThenNode().eval();
        } else {
            //eval continuous if statement
            int n = 0;
            while(evalElseIfNode(n)) {
                n++;
            }

            // body ELSE
            if(getElseNode() != null) {
                getElseNode().eval();
            }
        }
        return null;
    }

    // any part runs then break with return value of false
    private boolean evalElseIfNode(int n) {
        if(n >= followIFBlockNodes.size()) {
            return false;
        }
        IFBlockNode node = followIFBlockNodes.get(n);
        if(node.cond.eval() .getBValue() == false) {
            return true;
        }
        node.eval();
        return false;

    }

    @Override
    public String toString() {
        String res = String.format("IF[%s THEN{%s} ", cond.toString(), getThenNode().toString());

        for(IFBlockNode ifBlockNode: followIFBlockNodes) {
            res += String.format("{ELSEIF {%s} }", ifBlockNode.toString());
        }

        if(getElseNode() != null) {
            res += String.format("ELSE{%s}", getElseNode().toString());
        }
        res += "]";

        return res;
    }
}
