package Syntax;

import Core.LexicalUnit;
import Core.Value;

public class ProgramNode extends Node{
    private Node child;

    public ProgramNode(NodeType type, Environment env) {
        super(type, env);
    }

    static FirstCollection fc = new FirstCollection(
            StmtListNode.fc,
            BlockNode.fc
    );
    public static Node isMatch(Environment env, LexicalUnit first) {
        //check if LexicalUnit belongs to the first set
        if(fc.contains(first)) {
            return new ProgramNode(NodeType.PROGRAM, env);
        }
        return null;
    }

    @Override
    public boolean parse() {
        NextNodeList nextNodeList = new NextNodeList(StmtListNode.class);
        Node child = nextNodeList.nextNode(env, peekLexicalUnit());
        if(child != null) {
            this.child = child;
            return child.parse();
        }
        return false;
    }

    @Override
    public Value eval() {
        return child.eval();
    }

    @Override
    public String toString() {
        return child.toString();
    }
}
