package Syntax;

import Core.Environment;
import Core.LexicalType;
import Core.LexicalUnit;
import Core.Value;

public abstract class Node {

    // type of node
    public final NodeType type;

    //Runtime environment
    public final Environment env;

    public Node(NodeType type, Environment env) {
        this.type = type;
        this.env = env;
    }

    public NodeType getType() {
        return type;
    }

    /**
     * syntax analyzer
     * @return
     */
    public abstract boolean parse();

    /**
     * evaluate Node
     * @return
     */
    public abstract Value eval();

    /**
     * must implement toString
     * @return
     */
    public abstract String toString();

    /**
     * peek next LexicalUnit
     */
    protected LexicalUnit peekLexicalUnit() {
        LexicalUnit unit = env.getInput().get();
        env.getInput().unget(unit);
        return unit;
    }

    /**
     * consume next LexicalUnit type
     * @param expectedType
     * @return
     */
    protected  boolean skipExpectNode(LexicalType... expectedType) {
        for(LexicalType expected: expectedType) {
            if(peekLexicalUnit().getType() == expected) {
                env.getInput().get();
                continue;
            }
            return false;
        }
        return true;
    }

}
