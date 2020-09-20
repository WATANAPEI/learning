package parser;

import lexer.LexicalType;
import lexer.NullToken;
import lexer.Token;
import lexer.TokenType;

import java.util.Map;
import java.util.Optional;

class AssignNode extends Node {
    Node lhs;
    Node rhs;

    private AssignNode() {
    }

    private AssignNode(Node lhs, Node rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public void assignVarNode(Node node) {
        this.lhs = node;
    }

    public void assignValueNode(Node node) {
        this.rhs = node;
    }

    public static Optional<Node> checkNode(Parser parser) {
        AssignNode assignNode = new AssignNode();
        Token token = parser.getCurrent().orElse(new NullToken());
        if(token.tokenType() == TokenType.WORD) {
            Node varNode = WordNode.checkNode(parser).orElseThrow();
            if(parser.checkNextLexicalType(LexicalType.ASSIGN)) {
                assignNode.assignVarNode(varNode);
                parser.getNext(); // proceed a token
                parser.consume(LexicalType.ASSIGN);
                assignNode.assignValueNode(ExprNode.checkNode(parser).orElseThrow());
                return Optional.ofNullable(assignNode);
            } else {
                WordNode wordNode = new WordNode(token);
                return Optional.ofNullable(wordNode);
            }
        } else {
            throw new IllegalStateException("Parsing error: AssignNode");
        }
    }

    @Override
    public Optional<Value> value() {
        return Optional.empty();
    }

    @Override
    public Optional<Value> eval(Map<String, Value> symbolTable) {
        symbolTable.put(lhs.value().orElseThrow().getSValue().orElseThrow(),
                rhs.eval(symbolTable).orElse(null));
        return Optional.empty();
    }
}
