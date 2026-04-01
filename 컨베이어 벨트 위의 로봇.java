import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());

        int[] A = new int[2 * N];
        int[] belt = new int[2 * N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int t = 0;
        while (fourthRule(N, K, A, belt)) {
            t++;
            firstRule(N, K, A, belt);
            secondRule(N, K, A, belt, t);
            thirdRule(N, K, A, belt, t);
        }
        System.out.println(t);
    }

    public static void firstRule(int N, int K, int[] A, int[] belt) {
        int temp = A[2*N-1];
        for (int i = 2*N-1; i > 0; i--) {
            A[i] = A[i-1];
        }
        A[0] = temp;

        temp = belt[2*N-1];
        for (int i = 2*N-1; i > 0; i--) {
            belt[i] = belt[i-1];
        }
        belt[0] = temp;

        belt[N - 1] = 0;
    }

    public static void secondRule(int N, int K, int[] A, int[] belt, int t) {
        for (int i = N - 2; i >= 0; i--) {
            if (belt[i] != 0) {
                if (belt[i + 1] == 0 && A[i + 1] != 0) {
                    belt[i + 1] = belt[i];
                    A[i + 1] -= 1;
                    belt[i] = 0;
                }
            }
        }
        belt[N - 1] = 0;
    }

    public static void thirdRule(int N, int K, int[] A, int[] belt, int t) {
        if (A[0] != 0) {
            belt[0] = t;
            A[0] -= 1;
        }
    }

    public static boolean fourthRule(int N, int K, int[] A, int[] belt) {
        int cnt = 0;
        for (int i = 0; i < 2 * N; i++) {
            if (A[i] == 0) cnt++;
        }
        if (cnt >= K) return false;
        else return true;
    }
}
