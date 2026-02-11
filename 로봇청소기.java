
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    int r = Integer.parseInt(st.nextToken());
    int c = Integer.parseInt(st.nextToken());
    int d = Integer.parseInt(st.nextToken());

    int[][] arr = new int[N][M];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int[][] move = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    int count = 0;

    while (true) {
      if (arr[r][c] == 0) {
        arr[r][c] = -1;
        count++;
      }

      boolean checkFour = true;

      for (int k = 0; k < 4; k++) { // 주변 체크
        int i = (d + 3 - k + 4) % 4;

        int nr = r + move[i][0];
        int nc = c + move[i][1];

        if (nr >= 0 && nr < N && nc >= 0 && nc < M && arr[nr][nc] == 0) {
          checkFour = false;
          r = nr;
          c = nc;
          d = i;
          break;
        }
      }

      if (checkFour) { // 주변 벽 다 청소됐을 때.
        int nr = r - move[d][0];
        int nc = c - move[d][1];

        if (nr >= 0 && nr < N && nc >= 0 && nc < M && arr[nr][nc] != 1) {
          r = nr;
          c = nc;
        } else {
          break;
        }
      }
    }
    System.out.println(count);
  }
}
