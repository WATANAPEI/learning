/**
 * case sensitive
 */
public class StringPermutationChecker {

    public static boolean check(String a, String b) {
        String aSorted = sort(a);
        String bSorted = sort(b);
        return a.contentEquals(b);
    }

    /**
     * sort string along with natural order (ascending)
     * @param str
     * @return
     */
    public static String sort(String str) {
        char[] chars = str.toCharArray();
        for(int i = 0; i < chars.length-1; i++) {
            for(int j = i+1; j < chars.length; j++) {
                if(chars[i] > chars[j]) {
                    swap(chars, i, j);
                }
            }
        }
        return String.valueOf(chars);
    }
    private static void swap(char[] chars, int i, int j) {
        char tmp;
        tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }
}


