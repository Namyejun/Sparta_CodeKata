import java.util.HashMap;
import java.util.Map;

class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        int[] score = {0, -3, -2, -1, 0, 1, 2, 3};
        Map<String, Integer> map = new HashMap<>();
        map.put("RT", 0);
        map.put("CF", 0);
        map.put("JM", 0);
        map.put("AN", 0);

        for (int i = 0; i < choices.length; i++) {
            String key;
            int val;
            if (survey[i].charAt(0) < survey[i].charAt(1)) {
                key = String.join("", String.valueOf(survey[i].charAt(0)), String.valueOf(survey[i].charAt(1)));
                val = score[choices[i]];
            } else {
                key = String.join("", String.valueOf(survey[i].charAt(1)), String.valueOf(survey[i].charAt(0)));
                val = -score[choices[i]];
            }
            map.replace(key, map.get(key) + val);
        }
        
        map.forEach((k,v) -> System.out.println(k + ", " + v));

        answer += map.get("RT") <= 0 ? "R" : "T";
        answer += map.get("CF") <= 0 ? "C" : "F";
        answer += map.get("JM") <= 0 ? "J" : "M";
        answer += map.get("AN") <= 0 ? "A" : "N";

        return answer;
    }
}