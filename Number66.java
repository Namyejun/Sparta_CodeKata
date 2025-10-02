import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        List<Integer> answer = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        for (String key : keymap) {
            for (int i = 0; i < key.length(); i++) {
                if (map.containsKey(key.charAt(i))) {
                    map.replace(key.charAt(i), Math.min(map.get(key.charAt(i)), i + 1));
                } else {
                    map.put(key.charAt(i), i + 1);
                }
            }
        }

        for (String target : targets) {
            int temp = 0;
            for (int i = 0; i < target.length(); i++) {
                if (map.containsKey(target.charAt(i))) {
                    temp += map.get(target.charAt(i));
                } else {
                    temp = -1;
                    break;
                }
            }
            answer.add(temp);
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}