package Lexer;

import Core.LexicalType;

public enum ReservedWord {
    IF("IF", LexicalType.IF),
    THEN("THEN", LexicalType.THEN),
    ELSE("ELSE", LexicalType.ELSE),
    ELSEIF("ELSEIF", LexicalType.ELSEIF),
    ENDIF("ENDIF", LexicalType.ENDIF),
    FOR("FOR", LexicalType.FOR),
    FORALL("FORALL", LexicalType.FORALL),
    NEXT("NEXT", LexicalType.NEXT),
    FUNC("FUNC", LexicalType.FUNC),
    DIM("DIM", LexicalType.DIM),
    AS("AS", LexicalType.AS),
    END("END", LexicalType.END),
    WHILE("WHILE", LexicalType.WHILE),
    DO("DO", LexicalType.DO),
    UNTIL("UNTIL", LexicalType.UNTIL),
    LOOP("LOOP", LexicalType.LOOP),
    TO("TO", LexicalType.TO),
    WEND("WEND", LexicalType.WEND),

    ;

    private final String val;
    private final LexicalType type;

    private ReservedWord(String val, LexicalType type) {
        this.val = val;
        this.type = type;
    }

    public boolean isMatch(String test) {
        return test.equals(this.val);
    }

    public LexicalType getType() {
        return this.type;
    }
}
