
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()), L = Integer.parseInt(st.nextToken()), R = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        while (true) { // => 2000
            int[][] visited = new int[N][N];
            boolean moved = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) { // => 2500
                    if (visited[i][j] == 0) {
                        if (bfs(arr, visited, i, j, N, L, R)) {
                            moved = true;
                        }
                    }
                }
            }

            if (moved) {
                answer += 1;
            } else {
                break;
            }
        }

        System.out.println(answer);
    }

    public static boolean bfs(int[][] arr, int[][] visited, int r, int c, int N, int L, int R) { // visited로 한 번 들어간 건 안 들어감.
        Deque<int[]> q = new ArrayDeque<>();
        q.addLast(new int[] {r, c});
        visited[r][c] = 1;

        List<int[]> union = new ArrayList<>();
        union.add(new int[] {r, c});
        int people = arr[r][c];

        int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        while (!q.isEmpty()) {
            int[] cur = q.removeFirst();
            int curPeople = arr[cur[0]][cur[1]];

            for (int i = 0; i < 4; i++) {
                int newR = cur[0] + move[i][0];
                int newC = cur[1] + move[i][1];

                if (0 <= newR && newR < N && 0 <= newC && newC < N) {
                    if (visited[newR][newC] != 1) {
                        if (L <= Math.abs(curPeople - arr[newR][newC]) && Math.abs(curPeople - arr[newR][newC]) <= R) {
                            q.addLast(new int[] {newR, newC});
                            visited[newR][newC] = 1;
                            union.add(new int[] {newR, newC});
                            people += arr[newR][newC];
                        }
                    } 
                }
            }
        }

        if (union.size() > 1) {
            for (int[] t : union) {
                arr[t[0]][t[1]] = people / union.size();
            }
            return true;
        } else {
            return false;
        }
    }
}
