class Solution {
  static int answer = 0;
  static int n;

  public int solution(int N) {
    n = N;
    answer = 0;
    dfs(new int[n], 0);
    return answer;
  }

  // row = 현재 퀸을 놓을 행
  static void dfs(int[] board, int row) {
    if (row == n) {
      answer++;
      return;
    }

    for (int col = 0; col < n; col++) {
      boolean possible = true;

      // 이전 행들과 충돌 체크
      for (int prev = 0; prev < row; prev++) {
        // 같은 열
        if (board[prev] == col) {
          possible = false;
          break;
        }
        // 대각선
        if (Math.abs(board[prev] - col) == row - prev) {
          possible = false;
          break;
        }
      }

      if (possible) {
        board[row] = col;
        dfs(board, row + 1);
      }
    }
  }
}