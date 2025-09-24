import java.util.Arrays;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        int[] map = new int[26];
        Arrays.fill(map, -1);
        for (int i = 0; i < s.length(); i++) {
            if (map[s.charAt(i) - 'a'] == -1) {
                answer[i] = -1;
                map[s.charAt(i) - 'a'] = i;
            } else {
                answer[i] = i - map[s.charAt(i) - 'a'];
                map[s.charAt(i) - 'a'] = i;
            }
        }
        return answer;
    }
}