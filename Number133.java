
import java.util.Arrays;

class Solution {
  public int solution(String name) {
    int answer = 0;
    int minMove = name.length() - 1;
    for (int i = 0; i < name.length(); i++) {
      answer += getDiff(name.charAt(i));

      int t = i + 1;
      while (t < name.length() && name.charAt(t) == 'A') {
        t++;
      }

      minMove = Arrays.stream(new int[] { minMove, 2 * i + (name.length() - t), 2 * (name.length() - t) + i }).min()
          .getAsInt();
    }
    answer += minMove;
    return answer;
  }

  public int getDiff(char from) {
    return Math.min(26 - from + 'A', from - 'A');
  }
}