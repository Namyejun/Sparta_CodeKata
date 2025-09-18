class Solution {
    public int solution(int n) {
        return getDecimal(getTrinary(n));
    }

    public static String getTrinary(int n) {
        int t = n;
        String val = "";
        while (t != 0) {
            val = (t % 3) + val;
            t = t / 3;
        }
        return val;
    }

    public static int getDecimal(String s) {
        int t = 1;
        int answer = 0;
        for (int i = 0; i < s.length(); i++) {
            answer += t * ((int) s.charAt(i) - (int) '0');
            t *= 3;
        }
        return answer;
    }
}