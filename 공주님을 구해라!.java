import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), T = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] move = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Deque<int[]> q = new ArrayDeque<>();
        int[][][] visited = new int[N][M][2];
        visited[0][0][0] = 1;
        q.offerLast(new int[] {0, 0, 0});

        while (!q.isEmpty()) {
            int[] cur = q.pollFirst();
            int cx = cur[0], cy = cur[1], gram = cur[2];
            for (int i = 0; i < 4; i++) {
                int nx = cx + move[i][0], ny = cy + move[i][1];
                if (0 > nx || nx >= N || 0 > ny || ny >= M) continue;
                if (gram == 0 && arr[nx][ny] == 1) continue;
                int nextGram = gram;
                if (arr[nx][ny] == 2) nextGram = 1;
                if (visited[nx][ny][nextGram] != 0) continue;

                visited[nx][ny][nextGram] = visited[cx][cy][gram] + 1;
                q.offerLast(new int[] {nx, ny, nextGram});
            }
        }

        int answer = Integer.MAX_VALUE;

        if (visited[N - 1][M - 1][0] != 0)
            answer = Math.min(answer, visited[N - 1][M - 1][0] - 1);

        if (visited[N - 1][M - 1][1] != 0)
            answer = Math.min(answer, visited[N - 1][M - 1][1] - 1);

        if (answer == Integer.MAX_VALUE || answer > T)
            System.out.println("Fail");
        else
            System.out.println(answer);
    }
}
