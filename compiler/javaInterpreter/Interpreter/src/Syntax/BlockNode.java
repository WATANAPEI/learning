package Syntax;

import Core.Environment;
import Core.LexicalUnit;
import Core.Value;

/**
 */
public class BlockNode extends Node{
    Node childNode;

    public BlockNode(NodeType type, Environment env) {
        super(type, env);
    }

    static FirstCollection fc = new FirstCollection(
            IFBlockNode.fc,
            LoopBlockNode.fc
    );

    public static Node isMatch(Environment env, LexicalUnit first) {
        if (fc.contains(first)) {
            return new BlockNode(NodeType.BLOCK, env);
        }
        return null;
    }

    @Override
    public boolean parse() {
        NextNodeList nextNodeList = new NextNodeList(LoopBlockNode.class, IFBlockNode.class);
        childNode = nextNodeList.nextNode(env, peekLexicalUnit());
        if(childNode != null) {
            return childNode.parse();
        }
        return false;
    }

    @Override
    public Value eval() {
        return childNode.eval();
    }

    @Override
    public String toString() {
        return childNode.toString();
    }
}
