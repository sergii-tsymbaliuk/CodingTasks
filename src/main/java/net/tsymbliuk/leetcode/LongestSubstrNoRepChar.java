import java.util.HashSet;

/**
 * Created by stsym on 12/5/2016.
 */
public class LongestSubstrNoRepChar {
    public class Solution {
        public int lengthOfLongestSubstring(String s) {
            if (s.length() < 2)
                return s.length();

            String maximum = s.substring(0,1);

            for (int i=0; i<s.length(); i++){
                HashSet<Character> chars = new HashSet<>();
                chars.add(s.charAt(i));

                for (int j=i+1; j<s.length() && !chars.contains(s.charAt(j)); j++){
                    chars.add(s.charAt(j));
                    if (j-i+1 > maximum.length()){
                        maximum = s.substring(i, j+1);
                    }
                }

            }

            return maximum.length();
        }
    }

    public static void main(String [] args){

    }
}
