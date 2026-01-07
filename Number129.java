
import java.util.ArrayList;
import java.util.List;

class Solution {
  public double[] solution(double k, int[][] ranges) {
    double[] answer = new double[ranges.length];
    List<Double> graph = new ArrayList<>();
    graph.add(k);
    while (k != 1) {
      if (k % 2 == 0)
        k /= 2;
      else
        k = 3 * k + 1;
      graph.add(k);
    }

    List<Double> cum = new ArrayList<>();
    cum.add(0d);
    for (int i = 1; i < graph.size(); i++) {
      cum.add(cum.get(i - 1) + (graph.get(i) + graph.get(i - 1)) / 2);
    }
    int l = cum.size() - 1;

    for (int i = 0; i < ranges.length; i++) {
      int a = ranges[i][0], b = ranges[i][1];
      if (a > l + b)
        answer[i] = -1.0;
      else
        answer[i] = cum.get(l + b) - cum.get(a);
    }

    return answer;
  }
}