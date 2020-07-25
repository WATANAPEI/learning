package Syntax;

import Core.LexicalType;
import Core.LexicalUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <stmt_list> :=
 *      <stmt>
 *     | <stmt_list> <NL> <stmt>
 *     | <block>
 *     | <block> <stmt_list>
 */
public class StmtListNode extends Node {
    private List<Node> childNodes = new ArrayList<>();

    public StmtListNode(NodeType type, Environment env) {
        super(type, env);
    }

    static FirstCollection fc = new FirstCollection(
            StmtNode.fc,
            BlockNode.fc
    );

    public static Node isMatch(Environment env, LexicalUnit first) {
        if(fc.contains(first)) {
            return new StmtListNode(NodeType.STMT_LIST, env);
        }
        return null;
    }

    @Override
    public boolean parse() {
        while(true) {
            // consume NL
            if(peekLexicalUnit().getType() == LexicalType.NL) {
                env.getInput().get();
                continue;
            }

            NextNodeList nextNodeList = new NextNodeList(StmtNode.class, BlockNode.class);
            Node child = nextNodeList.nextNode(env, peekLexicalUnit());
            if(child != null) {
                if(!child.parse()) {
                    return false;
                }
                // NL comes after Stmt
                if(child instanceof StmtNode) {
                    env.getInput().get();
                }
                childNodes.add(child);
                continue;
            }
            return true;
        }
    }

    @Override
    public Value eval() {
        for(Node node : childNodes) {
            node.eval();
        }
        return null;
    }

    @Override
    public String toString() {
        return childNodes
                .stream()
                .map(Node::toString)
                .collect(Collectors.joining(";"));
    }
}
