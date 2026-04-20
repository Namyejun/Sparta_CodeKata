import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());
        long[] A = new long[N];
        for (int i = 0; i < N; i++) {
            A[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(A);
        int j = 0;
        long min = Long.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            while (j < N && A[j] - A[i] < M) {
                j++;
            }
            if (j == N) break;
            min = Math.min(min, A[j] - A[i]);
        }
        System.out.println(min);
    }
}
