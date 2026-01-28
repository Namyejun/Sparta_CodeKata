
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Solution {
  public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
    int[] answer = new int[balls.length];
    int t = 0;
    for (int[] ball : balls) {
      answer[t] = getMinimumDistance(m, n, startX, startY, ball[0], ball[1]);
      t++;
    }
    return answer;
  }

  public int getMinimumDistance(int m, int n, int sx, int sy, int ex, int ey) {
    int tx, ty;
    List<Integer> lst = new ArrayList<>();

    if (!(sx == ex && sy < ey)) {
      // 1. 위쪽 벽
      tx = ex;
      ty = n + (n - ey);
      lst.add((tx - sx) * (tx - sx) + (ty - sy) * (ty - sy));
    }

    if (!(sx == ex && sy > ey)) {
      // 2. 아래쪽 벽
      tx = ex;
      ty = -ey;
      lst.add((tx - sx) * (tx - sx) + (ty - sy) * (ty - sy));
    }

    if (!(sy == ey && sx > ex)) {
      // 3. 왼쪽 벽
      tx = -ex;
      ty = ey;
      lst.add((tx - sx) * (tx - sx) + (ty - sy) * (ty - sy));
    }

    if (!(sy == ey && sx < ex)) {
      // 4. 오른쪽 벽
      tx = m + (m - ex);
      ty = ey;
      lst.add((tx - sx) * (tx - sx) + (ty - sy) * (ty - sy));
    }

    lst.sort(Comparator.naturalOrder());
    return lst.get(0);
  }
}