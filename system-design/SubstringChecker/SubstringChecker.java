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
     * @param s1 : string to be checked
     * @param s2 : substring pattern
     * @return boolean
     */
    public static boolean is_substring(String s1, String s2) {
        return bubbleImpl(s1, s2);
    }

    private static boolean bubbleImpl(String s1, String s2) {
        Set charSetS1 = new HashSet();
        Set charSetS2 = new HashSet();
        for(char c: removeDuplicate(s1).toCharArray()) {
            charSetS1.add(c);
        }
        for(char c: removeDuplicate(s2).toCharArray()) {
            charSetS2.add(c);
        }
        if(!charSetS1.equals(charSetS2)) {
            return false;
        }
        List<Character> listS1 = new ArrayList(charSetS1);
        Collections.sort(listS1);
        List<Character> listS2 = new ArrayList(charSetS2);
        Collections.sort(listS2);
        if(listS1.equals(listS2)) {
            return true;
        } else {
            return false;
        }
    }


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
