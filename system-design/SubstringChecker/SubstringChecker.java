import java.util.*;

public class SubstringChecker {

    private SubstringChecker() {
    }

    /**
     * example:
     * is_substring("abcabc", "abc") -> true
     * is_substring("aaaaaa", "abc") -> false
     * is_substring("abcabc", "bca") -> true
     *
     * @param str : string to be checked
     * @param pattern : substring pattern
     * @return boolean
     */
    public static boolean is_substring(String str, String pattern) {
        return bruteForceImpl(str, pattern);
    }

    private static boolean bruteForceImpl(String str, String pattern) {
        boolean foundMismatch;
        for(int i = 0; i < str.length(); i++){
            foundMismatch = false;
            for(int j = 0 ; j < pattern.length(); j++) {
                if(str.charAt(i+j) != pattern.charAt(j)) {
                    foundMismatch = true;
                    break;
                }
            }
            if(foundMismatch) {
                continue;
            }
            return true;
        }
        return false;
    }


//    private boolean kmpImpl(String str, String pattern) {
//        int locationInStr;
//
//
//    }


    public static String removeDuplicate(String str) {
        Set<Character> charSet = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for(Character c: str.toCharArray()) {
            if(!charSet.contains(c)) {
                charSet.add(c);
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
