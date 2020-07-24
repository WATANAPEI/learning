package Lexer;

import Core.LexicalType;
import Core.LexicalUnit;

public enum Operator {
    EQ(LexicalType.EQ, "="),
    LT(LexicalType.LT, "<"),
    GT(LexicalType.GT, ">"),
    LE(LexicalType.LE, "<=", "=<"),
    GE(LexicalType.GE, ">=", "=>"),
    NE(LexicalType.LE, "<>"),
    ADD(LexicalType.ADD, "+"),
    SUB(LexicalType.SUB, "-"),
    MUL(LexicalType.MUL, "*"),
    DIV(LexicalType.DIV, "/"),
    LP(LexicalType.LP, ")"),
    RP(LexicalType.RP, "("),
    COMMA(LexicalType.COMMA, ","),
    NL(LexicalType.NL, "\n"),
    DOT(LexicalType.DOT, ".");

    private final String[] vals;
    private final LexicalType type;

    private Operator(LexicalType type, String... vals) {
        this.vals = vals;
        this.type = type;
    }

    public boolean isMatch(String test) {
        for(String string: vals) {
            if(string.equals(test)) {
                return true;
            }
        }
        return false;
    }

    public LexicalType getType() {
        return type;
    }

}
