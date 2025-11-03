import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        List<Integer> answer = new ArrayList<>();
        Map<String, String> table = new HashMap<>();
        Map<String, Integer> time = new HashMap<>();
        List<String> carNum = new ArrayList<>(); 
        for (String record : records) {
            String[] temp = record.split(" ");
            if (!carNum.contains(temp[1]))
                carNum.add(temp[1]);
            if (!table.containsKey(temp[1]) && temp[2].equals("IN")) {
                table.put(temp[1], temp[0]);
                if (!time.containsKey(temp[1])) {
                    time.put(temp[1], 0);
                }
            } else if (table.containsKey(temp[1]) && temp[2].equals("OUT")) {
                String comeTime = table.get(temp[1]);
                table.remove(temp[1]);
                time.put(temp[1], time.get(temp[1]) + calcTime(comeTime, temp[0]));
            }
        }

        table.forEach((k, v) -> {
            time.put(k, time.get(k) + calcTime(v, "23:59"));
        });

        carNum.sort(Comparator.naturalOrder());
        
        for (String key: carNum) {
            answer.add(calcFee(time.get(key), fees[0], fees[1], fees[2], fees[3]));
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int calcTime(String in, String out) {
        String[] inArr = in.split(":");
        String[] outArr = out.split(":");

        int inHour = Integer.parseInt(inArr[0]);
        int inMinute = Integer.parseInt(inArr[1]);
        int outHour = Integer.parseInt(outArr[0]);
        int outMinute = Integer.parseInt(outArr[1]);

        return (outHour*60 + outMinute) - (inHour*60 + inMinute);
    }

    public static int calcFee(int time, int defaultTime, int defaultFee, int unitTime, int unitFee) {
        if (defaultTime < time) {
            return defaultFee + (int) (Math.ceil((double)(time - defaultTime)/unitTime) * unitFee);
        } else {
            return defaultFee;
        }
    }
}