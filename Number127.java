import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {
  public int solution(String[] board) {
    int n = board.length;
    int m = board[0].length();

    int[] r = new int[2];
    int[] g = new int[2];

    // R, G 위치 찾기
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (board[i].charAt(j) == 'R') {
          r[0] = i;
          r[1] = j;
        }
        if (board[i].charAt(j) == 'G') {
          g[0] = i;
          g[1] = j;
        }
      }
    }

    int[][] visit = new int[n][m];
    for (int i = 0; i < n; i++) {
      Arrays.fill(visit[i], 99999);
    }

    int[][] move = {
        { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }
    };

    Deque<int[]> q = new ArrayDeque<>();
    q.add(r);
    visit[r[0]][r[1]] = 0;

    while (!q.isEmpty()) {
      int[] cur = q.pollLast(); // pop()
      int sx = cur[0];
      int sy = cur[1];

      for (int[] d : move) {
        int dx = d[0];
        int dy = d[1];

        int nx = sx;
        int ny = sy;

        // 벽(D)이나 범위 밖 만나기 전까지 이동
        while (!oob(nx + dx, ny + dy, board)) {
          nx += dx;
          ny += dy;
        }

        if (visit[nx][ny] > visit[sx][sy] + 1) {
          visit[nx][ny] = visit[sx][sy] + 1;
          q.add(new int[] { nx, ny });
        }
      }
    }

    return visit[g[0]][g[1]] == 99999 ? -1 : visit[g[0]][g[1]];
  }

  private boolean oob(int x, int y, String[] board) {
    int n = board.length;
    int m = board[0].length();
    return !(0 <= x && x < n && 0 <= y && y < m && board[x].charAt(y) != 'D');
  }
}