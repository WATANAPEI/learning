package lexer;

public enum LexicalType {
    ADD("+"),
    SUB("-"),
    MUL("*"),
    DIV("/"),
    COMMA(","),
    SEMI_COLON(";"),
    ASSIGN("="),
    OPEN_BRA("("),
    CLOSE_BRA(")"),
    GT(">"),
    LT("<"),
    EQ("=="),
    GE(">="),
    LE("<="),
    NE("!="),
    IF("IF"),
    ELSE("ELSE");

    String image;

    LexicalType(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return this.image;
    }
}
