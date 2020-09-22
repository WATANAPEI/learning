package lexer;

import java.util.EnumSet;
import java.util.Optional;

class WordToken extends Token {
    public WordToken(String image) {
        this.image = image;
        this.type = TokenType.WORD;
        Optional<LexicalType> reserved = EnumSet.allOf(LexicalType.class).stream()
                .filter(e -> e.getImage().equals(image))
                .findAny();
        this.lexicalType = reserved.orElse(null);
    }
}
