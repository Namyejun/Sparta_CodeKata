class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        for (String s : babbling) {
            if (isValid(s)) {
                answer += 1;
            }
        }
        return answer;
    }

    public boolean isValid(String s) {
        if (s.contains("ayaaya") || s.contains("yeye") || s.contains("woowoo") || s.contains("mama")) return false;
        s = s.replace("aya", " ");
        s = s.replace("ye", " ");
        s = s.replace("woo", " ");
        s = s.replace("ma", " ");
        s = s.replace(" ", "");
        if (s.length() == 0) return true;
        return false;
    }
}