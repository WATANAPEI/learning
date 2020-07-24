package Lexer;

import Core.LexicalType;
import Core.LexicalUnit;
import Core.ValueImpl;

import java.util.EnumSet;

public class Token {
    private TokenType type;
    private String value;

    Token(TokenType type, String value) {
        this.type = type;
        this.value =  value;
    }

    public String getValue() {
        return value;
    }

    public TokenType getType() {
        return type;
    }

    public LexicalUnit parseLexicalUnit() {
        LexicalUnit res = null;
        switch (type) {
            case NUMBER:
                res = parseNumberToken();
                break;
            case WORD:
                res = parseWordToken();
                break;
            case LITERAL:
                res = parseLiteralToken();
                break;
            case SINGLE_OPERATOR:
            case MULTY_OPERATOR:
                res = parseOperatorToken();
                break;
        }

        return res;
    }

    private LexicalUnit parseWordToken() {

        Optional<ReservedWord> reservedWordOpt = EnumSet.allOf(ReservedWord.class)
                .stream()
                .filter(t -> t.isMatch(value))
                .findFirst();

        if(reservedWordOpt.isPresent()) {
            LexicalType type = reservedWordOpt.get().getType();
            return new LexicalUnit(type);
        }

        return new LexicalUnit(LexicalType.NAME, new ValueImpl(value));
    }

    private LexicalUnit parseNumberToken() {

        if(value.contains(".")) {
            return new LexicalUnit(LexicalType.DOUBLEVAL, new ValueImpl(value));
        }
        return new LexicalUnit(LexicalType.INTVAL, new ValueImpl(value));
    }

    private LexicalUnit parseLiteralToken() {
        String literalVal = value.replaceAll("\"", "");
        return new LexicalUnit(LexicalType.LITERAL, new ValueImpl(value));
    }

    private LexicalUnit parseOperatorToken() {

        Optional<Operator> operatorOpt = EnumSet.allOf(Operator.class)
                .stream()
                .filter(t -> t.isMatch(value))
                .findFirst();
        if(operatorOpt.isPresent()) {
            LexicalType type = operatorOpt.get().getType();
            return new LexicalUnit(type);
        }

        return null;
    }
}
