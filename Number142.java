
import java.util.ArrayList;
import java.util.List;

class Solution {
  List<Integer> lst = new ArrayList<>();
  int[] dx = { -1, 0, 1, 0 };
  int[] dy = { 0, 1, 0, -1 };

  public int[] solution(String[] grid) {
    int H = grid.length;
    int W = grid[0].length();

    boolean[][][] visited = new boolean[H][W][4];
    List<Integer> result = new ArrayList<>();

    for (int i = 0; i < H; i++) {
      for (int j = 0; j < W; j++) {
        for (int d = 0; d < 4; d++) {
          if (!visited[i][j][d]) {
            result.add(simulate(i, j, d, grid, visited));
          }
        }
      }
    }
    return result.stream().sorted().mapToInt(Integer::intValue).toArray();
  }

  public int simulate(int x, int y, int dir, String[] grid, boolean[][][] visited) {
    int H = grid.length;
    int W = grid[0].length();
    int count = 0;

    while (!visited[x][y][dir]) {
      visited[x][y][dir] = true;
      count++;

      char c = grid[x].charAt(y);

      if (c == 'L') {
        dir = (dir + 3) % 4; // 좌회전
      } else if (c == 'R') {
        dir = (dir + 1) % 4; // 우회전
      }
      // S면 그대로

      x = (x + dx[dir] + H) % H;
      y = (y + dy[dir] + W) % W;
    }

    return count;
  }
}