class Solution {
    public int solution(int storey) {
        int answer = 0;
        int len = String.valueOf(storey).length();
        for (int i = 0; i <= len; i++) {
            int chkDigit = (int) Math.pow(10, i);
            int current = (storey % (chkDigit * 10)) / chkDigit;
            if (current < 5) {
                answer += current;
                storey -= current * chkDigit;
            } else if (current > 5) {
                answer += 10 - current;
                storey += (10 - current) * chkDigit;
            } else {
                int next = (storey % (chkDigit * 100)) / (chkDigit * 10);
                if (next + 1 <= 5) {
                    answer += current;
                    storey -= current * chkDigit;
                } else {
                    answer += 10 - current;
                    storey += (10 - current) * chkDigit;
                }
            }
        }
        return answer;
    }
}