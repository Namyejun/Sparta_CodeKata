import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int t = arr[1] - arr[0];
        for (int i = 2; i < N; i++) {
            t = gcd(t, arr[i] - arr[i - 1]);
        }

        String answer = "";
        for (int i = 2; i <= t; i++) {
            if (t % i == 0) answer += i + " ";  
        }
        
        System.out.println(answer);
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
