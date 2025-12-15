import java.util.*;

class Solution {

  static int answer = Integer.MAX_VALUE;

  // 피로도 테이블
  static int[][] fatigue = {
      { 1, 1, 1 }, // 다이아 곡괭이
      { 5, 1, 1 }, // 철 곡괭이
      { 25, 5, 1 } // 돌 곡괭이
  };

  static Map<String, Integer> mineralIndex = new HashMap<>();

  public int solution(int[] picks, String[] minerals) {

    mineralIndex.put("diamond", 0);
    mineralIndex.put("iron", 1);
    mineralIndex.put("stone", 2);

    dfs(0, picks, Arrays.asList(minerals));
    return answer;
  }

  static void dfs(int score, int[] picks, List<String> minerals) {

    int sum = picks[0] + picks[1] + picks[2];
    if (sum == 0 || minerals.isEmpty()) {
      answer = Math.min(answer, score);
      return;
    }

    for (int i = 0; i < 3; i++) {
      if (picks[i] == 0)
        continue;

      int[] nextPicks = picks.clone();
      nextPicks[i]--;

      int tempScore = 0;
      int limit = Math.min(5, minerals.size());

      for (int j = 0; j < limit; j++) {
        int idx = mineralIndex.get(minerals.get(j));
        tempScore += fatigue[i][idx];
      }

      if (minerals.size() <= 5) {
        dfs(score + tempScore, nextPicks, Collections.emptyList());
      } else {
        dfs(score + tempScore, nextPicks, minerals.subList(5, minerals.size()));
      }
    }
  }
}