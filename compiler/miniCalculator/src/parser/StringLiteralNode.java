package parser;

import lexer.Token;

class StringLiteralNode extends Node {
    Value val;

    public StringLiteralNode(Token token) {
        val = new StringValue(token.getImage());
    }

    /**
     * Node: Value wraps optional values(String, Integer, Boolean)
     * @return
     */
    @Override
    public Value value() {
        return this.val;
    }

    @Override
    public void eval() {
        System.out.println(this.val.getSValue()
                .orElse("This node has no value: " + this));
    }
}
