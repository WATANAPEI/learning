package node;

import lexer.Token;
import lexer.TokenType;
import parser.Parser;
import parser.StringValue;
import parser.Value;

import java.util.Map;
import java.util.Optional;

class StringLiteralNode extends Node {
    Value val;

    public StringLiteralNode(Token token) {
        val = new StringValue(token.getImage());
    }

    public static Node checkNode(Parser parser) {
        Token token = parser.getNext().orElseThrow();
        if (token.tokenType() == TokenType.STRING) {
            return new StringLiteralNode(token);
        } else {
            throw new IllegalStateException("StringToken is expected.");
        }
    }

        /**
         * Node: Value wraps optional values(String, Integer, Boolean)
     * @return
     */
    @Override
    public Optional<Value> value() {
        return Optional.of(this.val);
    }

    @Override
    public Optional<Value> eval(Map<String, Value> symbolTable, Map<String, Node> functionTable) {
        return Optional.of(this.val);
    }
}
