import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution {

  static List<int[]> ryonList = new ArrayList<>();

  public int[] solution(int n, int[] info) {

    int[] minInv = new int[11];
    for (int i = 0; i < 11; i++) {
      minInv[i] = info[i] + 1;
    }

    int[] lst = new int[11];
    dfs(0, n, lst, minInv);

    List<int[]> results = new ArrayList<>();

    for (int[] r : ryonList) {
      results.add(new int[] { calc(info, r), Arrays.hashCode(r) });
    }

    // 점수 차 기준 정렬
    results.sort((a, b) -> b[0] - a[0]);

    if (results.get(0)[0] <= 0) {
      return new int[] { -1 };
    }

    int maxScore = results.get(0)[0];
    List<int[]> candidates = new ArrayList<>();

    for (int i = 0; i < results.size(); i++) {
      if (results.get(i)[0] != maxScore)
        break;

      int[] r = findByHash(results.get(i)[1]);

      int minPoint = -1;
      for (int j = 10; j >= 0; j--) {
        if (r[j] != 0) {
          minPoint = 10 - j;
          break;
        }
      }
      candidates.add(new int[] { minPoint, Arrays.hashCode(r) });
    }

    // 낮은 점수 더 많이 맞힌 배열 선택
    candidates.sort(Comparator.comparingInt(a -> a[0]));
    int[] answer = findByHash(candidates.get(0)[1]);

    int sum = Arrays.stream(answer).sum();
    if (sum != n) {
      answer[10] += (n - sum);
    }

    return answer;
  }

  static void dfs(int i, int k, int[] lst, int[] minInv) {
    if (i == 11 || k == 0) {
      ryonList.add(lst.clone());
      return;
    }

    // 안 쏘는 경우
    dfs(i + 1, k, lst.clone(), minInv);

    // 이길 수 있는 경우
    if (k - minInv[i] >= 0) {
      int[] newLst = lst.clone();
      newLst[i] += minInv[i];
      dfs(i + 1, k - minInv[i], newLst, minInv);
    }
  }

  static int calc(int[] apeach, int[] ryon) {
    int a = 0, r = 0;

    for (int i = 0; i < 11; i++) {
      if (apeach[i] == 0 && ryon[i] == 0)
        continue;

      if (apeach[i] >= ryon[i]) {
        a += (10 - i);
      } else {
        r += (10 - i);
      }
    }
    return r - a;
  }

  static int[] findByHash(int hash) {
    for (int[] r : ryonList) {
      if (Arrays.hashCode(r) == hash) {
        return r.clone();
      }
    }
    return null;
  }
}