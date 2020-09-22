package node;

import lexer.Token;
import lexer.TokenType;
import parser.Parser;
import parser.StringValue;
import parser.Value;

import java.util.Map;
import java.util.Optional;

class WordNode extends Node {
    Value val;

    public WordNode(Token token) {
        val = new StringValue(token.getImage());
    }

    public static Optional<Node> checkNode(Parser parser) {
        Token token = parser.getCurrent().orElseThrow();
        if(token.tokenType() == TokenType.WORD) {
            return Optional.of(new WordNode(token));
        } else {
            return Optional.empty();
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
    public Optional<Value> eval(Map<String, Value> symbolTable) {
        Value value = symbolTable.get(this.val.getSValue().orElseThrow());
        return Optional.ofNullable(value);
    }
}
