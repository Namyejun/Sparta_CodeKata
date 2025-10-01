class Solution {
    public int solution(String s) {
        int answer = 0;
        char first = ' ';
        int cntA = 0;
        int cntB = 0;
        for (int i = 0; i < s.length(); i++) {
            if (first == ' ') {
                first = s.charAt(i);
                cntA++;
                continue;
            }
            if (first == s.charAt(i)) {
                cntA++;
                continue;
            } else {
                cntB++;
                if (cntA == cntB) {
                    answer++;
                    cntA = 0;
                    cntB = 0;
                    first = ' ';
                }
                continue;
            }
        }
        if (cntA != cntB) answer++;
        return answer;
    }
}