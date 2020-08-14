package uniqueString;

public class UniqueStringChecker {
    /**
     * case sensitive
     * all ascii
     *
     * @param str
     * @return
     */
    public static boolean check(String str) {
        int maxCharNum = 256;
        boolean[] charUsageChecker = new boolean[maxCharNum];

        if(str.isEmpty()) {
            return false;
        }

        char[] charArray  = str.toCharArray();
        int i = 0;
        while(true) {
            if(!charUsageChecker[charArray[i]]) {
                charUsageChecker[charArray[i]] = true;
            } else {
                return false;
            }
            if(i == str.length()-1) {
                return true;
            }
            i++;
        }
    }

    public static int checkAsciiNumber(char c) {
        return (int)c;
    }


}
