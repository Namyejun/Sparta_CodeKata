import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        Map<Integer, Integer> l = new HashMap<>();
        Map<Integer, Integer> r = new HashMap<>();
        for (int i = 0; i < topping.length; i++) {
            if (r.containsKey(topping[i])) r.replace(topping[i], r.get(topping[i]) + 1);
            else r.put(topping[i], 1);
        }
        for (int i = 0; i < topping.length; i++) {
            if (l.containsKey(topping[i])) l.replace(topping[i], l.get(topping[i]) + 1);
            else l.put(topping[i], 1);

            r.replace(topping[i], r.get(topping[i]) - 1);
            if (r.get(topping[i]) == 0) r.remove(topping[i]);

            if (l.size() == r.size()) {
                answer += 1;
                // l.forEach((k, v) -> System.out.println("l : " + k));
                // r.forEach((k, v) -> System.out.println("r : " + k));
            }
        }
        return answer;
    }
}