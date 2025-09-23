class Solution {
    public String solution(String s, int n) {
        String answer = "";
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if ('a' <= ch && ch <= 'z') {
                answer += (char) ('a' + (((int) (ch - 'a') + n) % 26));
            } else if ('A' <= ch && ch <= 'Z') {
                answer += (char) ('A' + (((int) (ch - 'A') + n) % 26));
            } else {
                answer += ch;
            }
        }
        return answer;
    }
}