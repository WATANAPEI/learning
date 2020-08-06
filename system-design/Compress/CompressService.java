public class CompressService {

    public CompressService() {

    }

    public String compress(String str) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for(int i = 0; i < str.length(); i++) {
            if (i == 0 || i == str.length() - 1) {

            } else {
                if(str.charAt(i-1) == str.charAt(i)) {
                    count++;
                }else {
                    sb.append(str.charAt(i-1));
                    if(count != 1){
                        sb.append(count);
                    }
                    count = 1;
                }
            }
        }
        count++;
        sb.append(str.charAt(str.length()-1));
        if(count != 1){
            sb.append(count);
        }
        return sb.toString();
    }
}
