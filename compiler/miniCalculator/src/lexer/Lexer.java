package lexer;

import java.util.*;
import java.util.stream.Collectors;

// TODO: Use Regex to separate tokens
public class Lexer {
    Scanner sc;
    List<Token> tokenList;

    public Lexer(String str) {
        sc = new Scanner(str);
        sc.useDelimiter("[\\p{javaWhitespace}]+");
        tokenList = new ArrayList();
    }

    public Lexer() {
        tokenList = new ArrayList();
    }

    public List<String> splitString(String str) {
        int currentTail = 0;
        int currentPointer = 0;
        List<String> result = new ArrayList();
        EnumSet<TokenType> tokenTypes = EnumSet.allOf(TokenType.class);
        while(currentPointer < str.length()) {
            if(str.charAt(currentPointer) == ' ' || str.charAt(currentPointer) == '\t'
                    || str.charAt(currentPointer) == '\n') {
                currentTail++;
                currentPointer++;
                continue;
            }
            String subStr = str.substring(currentTail, currentPointer);
            List<TokenType> matchedToken = tokenTypes.stream()
                    .filter(t -> subStr.matches(t.getPattern()))
                    .collect(Collectors.toList());
            if(matchedToken.size() != 0) {
                currentPointer++;
                continue;
            } else {
                currentPointer--;
                result.add(str.substring(currentTail, currentPointer));
                currentTail = currentPointer;
            }
        }
        return result;
    }

    public List<Token> analyze() {
        String next;
        while(sc.hasNext()) {
            next = sc.next();
            if(next.matches(TokenType.NUMBER.getPattern())) {
                tokenList.add(new NumToken(next));
            } else if(next.matches((TokenType.STRING.getPattern()))){
                // strip double quotation
                tokenList.add(new StringToken(next.replace("\"", "")));
            } else if(next.matches(TokenType.WORD.getPattern())){
                tokenList.add(new WordToken(next));
            } else if(next.matches(TokenType.SINGLE_SYMBOL.getPattern())){
                tokenList.add(new SingleSymbolToken(next));
            } else if(next.matches(TokenType.MULTI_SYMBOL.getPattern())){
                tokenList.add(new MultiSymbolToken(next));
            } else {
                return null;
            }
        }
        return tokenList;
    }

}
