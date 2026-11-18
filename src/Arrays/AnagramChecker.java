package Arrays;

import java.util.Arrays;

public class AnagramChecker {
    public static void main(String[] args) {
        char[] word1 = {'r','e','s','t','f','u','l'};
        char[] word2 = {'f','l','u','s','t','e','r'};
        char[] word3 = {'f','a','u','l','t','e','r'};

        AnagramChecker checkAnagram = new AnagramChecker();

        System.out.println(checkAnagram.solve(word1, word2));
    }

    public boolean solve(char[] s1, char[] s2){

        if(s1.length != s2.length){
            return false;
        }

        //sort letters of strings, O(NlogN)
        Arrays.sort(s1);
        Arrays.sort(s2);

        //Compare these letter one by one O(N)
        for (int i = 0; i < s1.length; i++) {
            if(s1[i] != s2[i]){
                return false;
            }
        }

        return true;
    }
}
