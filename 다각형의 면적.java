import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] points = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            points[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }
        long sum = 0;

        for (int i = 0; i < N; i++) {
            int j = (i + 1) % N;
            sum += (long) points[i][0] * points[j][1];
            sum -= (long) points[j][0] * points[i][1];
        }

        double answer = Math.abs(sum) / 2.0;
        System.out.printf("%.1f\n", answer);
    }
}
