import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        Map<String, Integer> toIndex = new HashMap<>();
        Map<String, Integer> reportMap = new HashMap<>();

        for (int i = 0; i < id_list.length; i++) {
            toIndex.put(id_list[i], i);
            reportMap.put(id_list[i], 0);
        }

        Set<String> set = new HashSet<>(Arrays.stream(report).collect(Collectors.toList()));

        int[][] arr = new int[id_list.length][id_list.length];

        for (String r : set) {
            String[] t =r.split(" ");
            String a = t[0], b = t[1];

            reportMap.replace(b, reportMap.get(b) + 1);
            arr[toIndex.get(a)][toIndex.get(b)] += 1;
        }

        reportMap.forEach((key, value) -> {
            if (value >= k) {
                for (int t = 0; t < id_list.length; t++) {
                    if (arr[t][toIndex.get(key)] > 0) {
                        answer[t] += 1;
                    }
                }
            }
        });

        return answer;
    }
}