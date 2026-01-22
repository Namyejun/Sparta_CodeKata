import java.util.HashSet;
import java.util.Set;

class Solution {

  public String[] solution(int[][] line) {
    Set<String> points = new HashSet<>();

    for (int i = 0; i < line.length; i++) {
      for (int j = i + 1; j < line.length; j++) {
        long a1 = line[i][0], b1 = line[i][1], c1 = line[i][2];
        long a2 = line[j][0], b2 = line[j][1], c2 = line[j][2];

        long det = a1 * b2 - a2 * b1;
        if (det == 0)
          continue;

        long xNum = b1 * c2 - b2 * c1;
        long yNum = a2 * c1 - a1 * c2;

        if (xNum % det == 0 && yNum % det == 0) {
          long x = xNum / det;
          long y = yNum / det;
          points.add(x + "," + y);
        }
      }
    }

    if (points.isEmpty())
      return new String[0];

    long minX = Long.MAX_VALUE, minY = Long.MAX_VALUE;
    long maxX = Long.MIN_VALUE, maxY = Long.MIN_VALUE;

    for (String p : points) {
      String[] split = p.split(",");
      long x = Long.parseLong(split[0]);
      long y = Long.parseLong(split[1]);

      minX = Math.min(minX, x);
      minY = Math.min(minY, y);
      maxX = Math.max(maxX, x);
      maxY = Math.max(maxY, y);
    }

    int width = (int) (maxX - minX + 1);
    int height = (int) (maxY - minY + 1);

    StringBuilder[] rows = new StringBuilder[height];
    for (int i = 0; i < height; i++) {
      rows[i] = new StringBuilder();
      for (int j = 0; j < width; j++) {
        rows[i].append('.');
      }
    }

    for (String p : points) {
      String[] split = p.split(",");
      long x = Long.parseLong(split[0]);
      long y = Long.parseLong(split[1]);

      int col = (int) (x - minX);
      int row = (int) (maxY - y);
      rows[row].setCharAt(col, '*');
    }

    String[] answer = new String[height];
    for (int i = 0; i < height; i++) {
      answer[i] = rows[i].toString();
    }

    return answer;
  }
}