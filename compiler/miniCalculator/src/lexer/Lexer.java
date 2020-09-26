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
        int currentPointer = 1;
        List<String> result = new ArrayList();
        EnumSet<TokenType> tokenTypes = EnumSet.allOf(TokenType.class);
        while(currentPointer <= str.length()) {
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
                while(currentPointer < str.length() &&
                        (str.charAt(currentPointer) == ' '
                        || str.charAt(currentPointer) == '\t'
                        || str.charAt(currentPointer) == '\n')) {
                    currentPointer++;
                }
                if(currentPointer == str.length()) {
                    return result;
                }
                currentTail = currentPointer;
                currentPointer++;
            }
        }
        //TODO: deal with last character
        currentPointer--;
        result.add(str.substring(currentTail, currentPointer));
        return result;
    }

    public List<Token> analyze(String str) {
        List<String> strList = splitString(str);
        for(String s : strList) {
            if(s.matches(TokenType.NUMBER.getPattern())) {
                tokenList.add(new NumToken(s));
            } else if(s.matches((TokenType.STRING.getPattern()))){
                // strip double quotation
                tokenList.add(new StringToken(s.replace("\"", "")));
            } else if(s.matches(TokenType.WORD.getPattern())){
                tokenList.add(new WordToken(s));
            } else if(s.matches(TokenType.SINGLE_SYMBOL.getPattern())){
                tokenList.add(new SingleSymbolToken(s));
            } else if(s.matches(TokenType.MULTI_SYMBOL.getPattern())){
                tokenList.add(new MultiSymbolToken(s));
            } else {
                return null;
            }
        }
        return tokenList;
    }

}
