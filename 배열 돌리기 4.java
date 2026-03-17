import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] oper = new int[K][3];
        int[] lst = new int[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            oper[i][0] = Integer.parseInt(st.nextToken()) - 1;
            oper[i][1] = Integer.parseInt(st.nextToken()) - 1;
            oper[i][2] = Integer.parseInt(st.nextToken());
            lst[i] = i;
        }

        List<int[]> permutations = getPermutations(lst, K);
        int answer = 9999;
        for (int[] item : permutations) {
            int[][] copy = new int[N][M];

            for (int i = 0; i < N; i++) {
                System.arraycopy(arr[i], 0, copy[i], 0, M);
            }

            for (int t : item) {
                int r = oper[t][0], c = oper[t][1], s = oper[t][2];
                rotate(copy, r, c, s);
            }
            int min = 5001;
            for (int i = 0; i < N; i++) {
                int temp = 0;
                for (int j = 0; j < M; j++) {
                    temp += copy[i][j];
                }
                min = Math.min(min, temp);
            }
            answer = Math.min(min, answer);
        }

        System.out.println(answer);
    }

    public static void rotate(int[][] arr, int r, int c, int s) {
        for (int i = 1; i <= s; i++) {
            int top = r - i;
            int bottom = r + i;
            int left = c - i;
            int right = c + i;

            int temp = arr[top][left];

            for (int j = top; j < bottom; j++) { // 왼쪽 시계방향 덮어쓰기
                arr[j][left] = arr[j + 1][left];
            }
            for (int j = left; j < right; j++) { // 아래쪽 시계방향 덮어쓰기
                arr[bottom][j] = arr[bottom][j + 1];
            }
            for (int j = bottom; j > top; j--) { // 오른쪽 시계방향 덮어쓰기
                arr[j][right] = arr[j - 1][right];
            }
            for (int j = right; j > left + 1; j--) { // 위쪽 시계방향 덮어쓰기
                arr[top][j] = arr[top][j - 1];
            }

            arr[top][left + 1] = temp; // 마지막 채우기
        }
    }

    public static List<int[]> getPermutations(int[] arr, int r) {
        List<int[]> permutations = new ArrayList<>();
        boolean[] visited = new boolean[arr.length];
        makePermutations(arr, new int[r], visited, 0, r, permutations);
        return permutations;
    }

    public static void makePermutations(int[] arr, int[] selected, boolean[] visited, int depth, int r, List<int[]> permutations) {
        if (depth == r) {
            permutations.add(selected.clone());
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                selected[depth] = arr[i];
                makePermutations(arr, selected, visited, depth + 1, r, permutations);
                visited[i] = false;
            }
        }
    }
}
