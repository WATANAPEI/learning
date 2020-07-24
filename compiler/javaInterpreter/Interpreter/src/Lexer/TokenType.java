package Lexer;

import java.util.regex.Pattern;

public enum TokenType {
    NUMBER("[0-9.]+"),
    WORD("^[a-zA-Z]\\w*"),
    LITERAL("^\"[^\"]*\"?"),
    SINGLE_OPERATOR("[\\.\n\\+\\-\\*\\/\\)\\(,]"),
    MULTY_OPERATOR("[><=]|=[><]|[><]=|<>");

    private final Pattern pattern;

    private TokenType(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public boolean isMatch(String test) {
        return pattern.matcher(test).matches();
    }
}
