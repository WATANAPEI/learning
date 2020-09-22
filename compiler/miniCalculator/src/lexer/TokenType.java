package lexer;

public enum TokenType {
    NUMBER("[0-9]+"),
    STRING("^\"[^\"]*\"?"),
    WORD("^[a-zA-Z]\\w*"),
    SINGLE_SYMBOL("[\\.\\+\\-\\*\\/\\)\\(,=<>]"),
    MULTI_SYMBOL("[<>]=|!=|=="),
    NULL_TOKEN("");

    private String pattern;

    TokenType(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return this.pattern;
    }
}
