package parser;

public enum NodeType {
    NUMBER_LITERAL("[0-9]+"),
    STRING_LITERAL("^\"[^\"]*\"?"),
    WORD("^[a-zA-Z]\\w*"),
    Symbol("[^a-zA-Z1-9]+");

    private String pattern;

    NodeType(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return this.pattern;
    }
}
