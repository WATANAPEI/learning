package lexer;

public enum TokenType {
    Number("\\d+"),
    String("\\w+"),
    Symbol("[^a-zA-Z1-9]+");

    private String pattern;

    TokenType(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return this.pattern;
    }
}
