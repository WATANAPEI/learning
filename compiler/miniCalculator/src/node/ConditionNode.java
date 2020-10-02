package node;

import lexer.LexicalType;
import lexer.Token;
import lexer.TokenType;
import parser.BooleanValue;
import parser.Parser;
import parser.Value;

import java.util.Map;
import java.util.Optional;

public class ConditionNode extends Node{
    Token op;
    Node lhs;
    Node rhs;

    public ConditionNode() {
    }

    public ConditionNode(Token op, Node lhs, Node rhs) {
        this.op = op;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public static Node checkNode(Parser parser) {
        Token currentToken = parser.getCurrent()
                .orElseThrow(() -> new IllegalStateException("No Token"));
        if(currentToken.checkTokenType(TokenType.NUMBER) ||
            parser.checkCurrentLexicalType(LexicalType.ID)) {
            if(parser.checkNextLexicalType(
                    LexicalType.GT,
                    LexicalType.LT,
                    LexicalType.GE,
                    LexicalType.LE,
                    LexicalType.NE,
                    LexicalType.EQ)) {
                Node lhs = ExprNode.checkNode(parser);
                Token opToken = parser.getCurrent()
                        .orElseThrow(() -> new IllegalStateException("No Token."));
                parser.getNext(); // proceed a token
                return new ConditionNode(opToken, lhs, ExprNode.checkNode(parser));
            } else {
                throw new IllegalStateException("Compare lexical type is expected.");
            }
        } else {
            throw new IllegalStateException("Number or Word Token type is expected.");
        }
    }

    @Override
    public Optional<Value> value() {
        return Optional.empty();
    }

    @Override
    public Optional<Value> eval(Map<String, Value> symbolTable, Map<String, Node> functionTable) {
        int lvalue = lhs.eval(symbolTable, functionTable).orElseThrow().getIValue().orElseThrow();
        int rvalue = rhs.eval(symbolTable, functionTable).orElseThrow().getIValue().orElseThrow();
        switch(op.lexicalType()) {
            case GT:
                return Optional.of(new BooleanValue(lvalue > rvalue));
            case LT:
                return Optional.of(new BooleanValue(lvalue < rvalue));
            case EQ:
                return Optional.of(new BooleanValue(lvalue == rvalue));
            case NE:
                return Optional.of(new BooleanValue(lvalue != rvalue));
            case GE:
                return Optional.of(new BooleanValue(lvalue >= rvalue));
            case LE:
                return Optional.of(new BooleanValue(lvalue <= rvalue));
            default:
                return Optional.empty();
        }
    }
}
