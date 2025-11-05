import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(String word) {
        int answer = word.length();
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 0);
        map.put('E', 1);
        map.put('I', 2);
        map.put('O', 3);
        map.put('U',4);
        int[] lst = {781, 156, 31, 6, 1};
        for (int i = 0; i < word.length(); i++) {
            answer += map.get(word.charAt(i)) * lst[i];
        }
        return answer;
    }
}
// 1 + 5 + 25 + 125 + 625 = 781