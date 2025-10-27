import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, Integer> map = new HashMap<>();
        for (String[] cloth: clothes) {
            String a = cloth[0], b = cloth[1];
            if (map.containsKey(b)) {
                map.replace(b, map.get(b) + 1);
            } else {
                map.put(b, 1);
            }
        }

        Iterator<Map.Entry<String, Integer>> iter = map.entrySet().iterator();
        
        while (iter.hasNext()) {
            Map.Entry<String, Integer> entry = iter.next();
            Integer val = entry.getValue();
            answer *= (val + 1);
        }

        return answer - 1;
    }
}