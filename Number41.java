class Solution {
    public String solution(String s) {
        String answer = "";
        int index = 0;
        for (String c : s.split("")) {
            if (c.equals(" ")) {
                answer += " ";
                index = 0;
            } else {
                answer += (index % 2 == 0) ? c.toUpperCase() : c.toLowerCase();
                index++;
            }
        }
        return answer;
    }
}