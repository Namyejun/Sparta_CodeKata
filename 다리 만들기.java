import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Deque<int[]> q = new ArrayDeque<>();
        int[][] visited = new int[N][N];
        int[][] move = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int t = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 1 && visited[i][j] == 0) {
                    q.offerLast(new int[]{i, j});
                    visited[i][j] = t;

                    while (!q.isEmpty()) {
                        int[] cur = q.pollFirst();
                        int cx = cur[0], cy = cur[1];

                        for (int k = 0; k < 4; k++) {
                            int nx = cx + move[k][0], ny = cy + move[k][1];

                            if (0 <= nx && nx < N && 0 <= ny && ny < N && visited[nx][ny] == 0 && arr[nx][ny] == 1) {
                                q.offerLast(new int[] {nx, ny});
                                visited[nx][ny] = t;
                            }
                        }
                    }
                    t++;
                }
            }
        }

        // for (int i = 0; i < N; i++) {
        //     for (int j = 0; j < N; j++) {
        //         System.out.print(visited[i][j]);
        //     }
        //     System.out.println();
        // }

        Deque<int[]> q2 = new ArrayDeque<>();
        int[][] dist = new int[N][N];
        int[][] checkOwn = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] != 0) {
                    q2.offerLast(new int[] {i, j});
                    dist[i][j] = 0;
                    checkOwn[i][j] = visited[i][j];
                }
            }
        }
        
        int answer = Integer.MAX_VALUE;

        while (!q2.isEmpty()) {
            int[] cur = q2.pollFirst();
            int cx = cur[0], cy = cur[1];

            for (int k = 0; k < 4; k++) {
                int nx = cx + move[k][0], ny = cy + move[k][1];

                if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                    if (checkOwn[nx][ny] == 0) {
                        q2.offerLast(new int[] {nx, ny});
                        checkOwn[nx][ny] = checkOwn[cx][cy];
                        dist[nx][ny] = dist[cx][cy] + 1;
                    } else if (checkOwn[nx][ny] != checkOwn[cx][cy]) {
                        answer = Math.min(answer, dist[nx][ny] + dist[cx][cy]);
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
