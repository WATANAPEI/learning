package Syntax;

import Core.Environment;
import Core.LexicalType;
import Core.LexicalUnit;
import Core.Value;

public class EndNode extends Node {

    public EndNode(NodeType type, Environment env) {
        super(type, env);
    }

    public static Node isMatch(Environment env, LexicalUnit first) {
        FirstCollection fc = new FirstCollection(LexicalType.END);
        if(fc.contains(first)) {
            return new EndNode(NodeType.END, env);
        }
        return null;
    }

    @Override
    public boolean parse() {
        return true;
    }

    @Override
    public Value eval() {
        return null;
    }

    @Override
    public String toString() {
        return "END";
    }
}
