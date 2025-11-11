import java.util.HashSet;
import java.util.Iterator;

class Solution {
    public int solution(String numbers) {
        HashSet<Integer> set = new HashSet<>();
        permutation("", numbers, set);
        int answer = 0;
        Iterator<Integer> iter = set.iterator();
        while (iter.hasNext()) {
            int x = iter.next();
            if (x == 2) answer ++;
            if (x % 2 != 0 && isPrime(x)) answer ++;
        }
        return answer;
    }

    public static boolean isPrime(int x) {
        if (x == 0 || x == 1) return false;
        for (int i = 3; i <= (int) Math.sqrt(x); i += 2) {
            if (x % i == 0) return false;
        }
        return true;
    }

    public void permutation(String prefix, String str, HashSet<Integer> set) {
        int n = str.length();
        if (!prefix.equals("")) set.add(Integer.valueOf(prefix));
        for (int i = 0; i < n; i++) {
            permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), set);
        }
    }
}