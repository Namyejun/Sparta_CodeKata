
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class 톱니바퀴 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    List<ArrayList<Integer>> wheel = new ArrayList<>();

    for (int i = 0; i < 4; i++) {
      String line = br.readLine();
      wheel.add(new ArrayList<>());

      for (int j = 0; j < 8; j++) {
        wheel.get(i).add(line.charAt(j) - '0');
      }
    }

    StringTokenizer st = new StringTokenizer(br.readLine());
    int K = Integer.parseInt(st.nextToken());

    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      int num = Integer.parseInt(st.nextToken()) - 1;
      int dir = Integer.parseInt(st.nextToken());

      Deque<int[]> q = new ArrayDeque<>();
      int[] rotate = new int[4]; // 회전 방향 저장용
      rotate[num] = dir;

      q.add(new int[] { num, dir });

      while (!q.isEmpty()) {
        int[] cur = q.pollFirst();

        for (int k = -1; k <= 1; k += 2) {
          int next = cur[0] + k;

          if (next >= 0 && next < 4 && rotate[next] == 0) {

            if (k == -1) { // 왼쪽 체크
              int curLeft = wheel.get(cur[0]).get(6);
              int nextRight = wheel.get(next).get(2);

              if (curLeft != nextRight) {
                rotate[next] = -cur[1];
                q.add(new int[] { next, rotate[next] });
              }

            } else { // 오른쪽 체크
              int curRight = wheel.get(cur[0]).get(2);
              int nextLeft = wheel.get(next).get(6);

              if (curRight != nextLeft) {
                rotate[next] = -cur[1];
                q.add(new int[] { next, rotate[next] });
              }
            }
          }
        }
      }

      for (int j = 0; j < 4; j++) {
        if (rotate[j] == 1) { // 시계 방향
          int last = wheel.get(j).remove(7);
          wheel.get(j).add(0, last);
        } else if (rotate[j] == -1) { // 반시계 방향
          int first = wheel.get(j).remove(0);
          wheel.get(j).add(first);
        }
      }
    }

    int answer = 0;
    for (int i = 0; i < 4; i++) {
      if (wheel.get(i).get(0) == 1) {
        answer += (Math.pow(2, i));
      }
    }

    System.out.println(answer);
  }
}
