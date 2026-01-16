class Solution {
  public int solution(String[] board) {
    int[] count = getCounts(board);
    int o = count[0], x = count[1];

    // 1. 기본 개수 조건
    if (o < x || o > x + 1)
      return 0;

    boolean oWin = isWin(board, 'O');
    boolean xWin = isWin(board, 'X');

    // 2. 동시에 이기는 경우
    if (oWin && xWin)
      return 0;

    // 3. O가 이긴 경우
    if (oWin && o != x + 1)
      return 0;

    // 4. X가 이긴 경우
    if (xWin && o != x)
      return 0;

    return 1;
  }

  // 승리 여부만 체크 (빈 칸 제외)
  public boolean isWin(String[] board, char c) {
    for (int i = 0; i < 3; i++) {
      if (board[i].charAt(0) == c &&
          board[i].charAt(1) == c &&
          board[i].charAt(2) == c)
        return true;

      if (board[0].charAt(i) == c &&
          board[1].charAt(i) == c &&
          board[2].charAt(i) == c)
        return true;
    }

    if (board[0].charAt(0) == c &&
        board[1].charAt(1) == c &&
        board[2].charAt(2) == c)
      return true;

    if (board[0].charAt(2) == c &&
        board[1].charAt(1) == c &&
        board[2].charAt(0) == c)
      return true;

    return false;
  }

  public int[] getCounts(String[] board) {
    int o = 0, x = 0;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board[i].charAt(j) == 'O')
          o++;
        else if (board[i].charAt(j) == 'X')
          x++;
      }
    }
    return new int[] { o, x };
  }
}