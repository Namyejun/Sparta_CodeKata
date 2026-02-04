
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 장르 순위를 집계해야 하고, 장르 별로 순위를 집계해야 함. 장르는 몇 개가 있고 뭐가 있는지 모름
class Solution {
  public int[] solution(String[] genres, int[] plays) {

    Map<String, Integer> genreMap = new HashMap<>();
    Map<String, List<int[]>> playMap = new HashMap<>();
    for (int i = 0; i < genres.length; i++) {
      String genre = genres[i];
      int play = plays[i];

      genreMap.merge(genre, play, Integer::sum);

      if (playMap.containsKey(genre)) {
        playMap.get(genre).add(new int[] { play, i });
      } else {
        playMap.put(genre, new ArrayList<>());
        playMap.get(genre).add(new int[] { play, i });
      }
    }
    List<Integer> answer = new ArrayList<>();

    List<Map.Entry<String, Integer>> entryList = new ArrayList<>(genreMap.entrySet());

    entryList.sort((a, b) -> b.getValue() - a.getValue());

    for (Map.Entry<String, Integer> e : entryList) {
      String genre = e.getKey();
      List<int[]> playList = playMap.get(genre);
      playList.sort((a, b) -> {
        return b[0] - a[0];
      });

      answer.add(playList.remove(0)[1]);
      if (!playList.isEmpty())
        answer.add(playList.remove(0)[1]);
    }

    return answer.stream().mapToInt(Integer::intValue).toArray();
  }
}