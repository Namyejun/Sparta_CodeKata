import java.util.HashMap;
import java.util.Map;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = new String[players.length];
        Map<String, Integer> nameToRank = new HashMap<>();
        Map<Integer, String> rankToName = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            nameToRank.put(players[i], i + 1);
            rankToName.put(i + 1, players[i]);
        }

        for (String call : callings) {
            int currentRank = nameToRank.get(call);
            String front = rankToName.get(currentRank - 1);
            rankToName.replace(currentRank - 1, call);
            rankToName.replace(currentRank, front);
            nameToRank.replace(call, currentRank - 1);
            nameToRank.replace(front, currentRank);
        }
        rankToName.forEach((k, v) -> answer[k - 1] = v);
        return answer;
    }
}