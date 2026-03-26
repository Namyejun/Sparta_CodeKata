import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int minimum = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][N];
        List<int[]> viruses = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 2) {
                    viruses.add(new int[]{i, j});
                }
            }
        }

        combination(viruses, M, 0, new ArrayList<int[]>(), arr, N);
        if (minimum == Integer.MAX_VALUE) minimum = -1;
        System.out.println(minimum);
    }

    public static void combination(List<int[]> viruses, int M, int start, List<int[]> temp, int[][] arr, int N) {
        if (temp.size() == M) {
            int[][] copyArr = new int[arr.length][];

            for (int i = 0; i < arr.length; i++) {
                copyArr[i] = arr[i].clone();
            }
            
            minimum = Math.min(minimum, bfs(copyArr, N, M, temp));
            return;
        }

        for (int i = start; i < viruses.size(); i++) {
            temp.add(viruses.get(i));
            combination(viruses, M, i + 1, temp, arr, N);
            temp.remove(temp.size() - 1);
        }
    }

    public static int bfs(int[][] arr, int N, int M, List<int[]> active) {
        Deque<int[]> q = new ArrayDeque<>();
        int[][] visited = new int[N][N];

        int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int[] virus : active) {
            visited[virus[0]][virus[1]] = 1;
            q.offerLast(virus);
        }

        while (!q.isEmpty()) {
            int[] tempVirus = q.pollFirst();

            for (int i = 0; i < 4; i++) {
                int nx = tempVirus[0] + move[i][0];
                int ny = tempVirus[1] + move[i][1];

                if (0 <= nx && nx < N && 0 <= ny && ny < N && arr[nx][ny] != 1 && visited[nx][ny] == 0) {
                    q.offerLast(new int[] {nx, ny});
                    visited[nx][ny] = visited[tempVirus[0]][tempVirus[1]] + 1;
                }
            }
        }

        int returnVal = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 0) {
                    if (visited[i][j] == 0) return Integer.MAX_VALUE;
                    returnVal = Math.max(returnVal, visited[i][j] - 1);
                }
            }
        }
        return returnVal;
    }
}
