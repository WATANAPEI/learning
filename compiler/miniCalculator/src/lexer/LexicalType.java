package lexer;

public enum LexicalType {
    ADD("+"),
    SUB("-"),
    MUL("*"),
    DIV("/"),
    COMMA(","),
    SEMI_COLON(";");

    String image;

    LexicalType(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}
