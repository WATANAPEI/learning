package node;

import lexer.Token;
import lexer.TokenType;
import parser.Parser;
import parser.StringValue;
import parser.Value;

import java.util.Map;
import java.util.Optional;

class WordNode extends Node {
    Value id;

    public WordNode(Token token) {
        id = new StringValue(token.getImage());
    }

    public static Node checkNode(Parser parser) {
        Token token = parser.getCurrent()
                .orElseThrow(()-> new IllegalStateException("No Token.")); // just eat token
        if(token.checkTokenType(TokenType.WORD)) {
            return new WordNode(token);
        } else {
            throw new IllegalStateException("WordToken is expected.");
        }
    }

    /**
     * Node: Value wraps optional values(String, Integer, Boolean)
     * @return
     */
    @Override
    public Optional<Value> value() {

        return Optional.of(this.id);
    }

    @Override
    public Optional<Value> eval(Map<String, Value> symbolTable, Map<String, Node> functionTable) {
        Value value = symbolTable.get(this.id.getSValue().orElseThrow());
        return Optional.ofNullable(value);
    }
}
