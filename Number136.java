
import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public int solution(int[][] targets) {
    int answer = 0;
    Arrays.sort(targets, Comparator.comparingInt((int[] i) -> i[1]));
    double before = -1;
    for (int[] target : targets) {
      if (before < target[0]) {
        answer++;
        before = target[1] - 0.5;
      }
    }
    return answer;
  }
}