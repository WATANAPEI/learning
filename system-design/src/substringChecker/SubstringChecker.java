package substringChecker;

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
        //return bruteForceImpl(str, pattern);
        return javaImpl(str, pattern);
    }

    private static boolean bruteForceImpl(String str, String pattern) {
        boolean foundMismatch;
        int processCount = 0;
        for(int i = 0; i < str.length()-pattern.length()+1; i++){
            foundMismatch = false;
            for(int j = 0 ; j < pattern.length(); j++) {
                processCount++;
                if(str.charAt(i+j) != pattern.charAt(j)) {
                    foundMismatch = true;
                    break;
                }
            }
            if(foundMismatch) {
                continue;
            }
            System.out.println("count: " + processCount);
            return true;
        }
        System.out.println("count: " + processCount);
        return false;
    }

    /**
     * java implementation(indexOf)
     * http://hg.openjdk.java.net/jdk7u/jdk7u6/jdk/file/8c2c5d63a17e/src/share/classes/java/lang/String.java#l1715
     * @param str
     * @param pattern
     * @return
     */
    private static boolean javaImpl(String str, String pattern) {
        char[] strArr = str.toCharArray();
        char[] ptnArr = pattern.toCharArray();
        char first = ptnArr[0];
        int max = 0 + str.length() - pattern.length();
        int processCount = 0;

        for(int i = 0; i <= max; i++) {
            // Look for first character
            if(strArr[i] != first) {
                while(++i <= max && strArr[i] != first) {
                    processCount++;
                };
            }

            //Found first character, now look at the rest of v2
            if(i <= max) {
                int j = i+1;
                int end = j + pattern.length() - 1;
                for( int k = 1; j < end && strArr[j] == ptnArr[k]; j++, k++) {
                    processCount++;
                };

                if(j==end) {
                    //Found whole string
                    System.out.println("count: " + processCount);
                    return true;
                }
            }
        }
        System.out.println("count: " + processCount);
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
