import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

        Map<Integer, String> chingho = new HashMap<>();
        int[] lst = new int[N];

        int t = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            int n = Integer.parseInt(st.nextToken());
            if (t >= 1 && lst[t - 1] == n) continue;
            else {
                chingho.put(t, s);
                lst[t] = n;
            }
            t++;
        }
        String answer = "";
        StringBuilder sb = new StringBuilder(answer);
        for (int i = 0; i < M; i++) {
            int k = Integer.parseInt(br.readLine());
            int temp = Arrays.binarySearch(lst, 0, t, k);
            int insertionPoint = -1;

            if (temp < 0) {
                insertionPoint = ((-1) * temp)  - 1;
            } else {
                insertionPoint = temp;
            }

            if (insertionPoint >= 1 && lst[insertionPoint - 1] == k) {
                insertionPoint--;
            }

            sb.append((chingho.get(insertionPoint)) + "\n");
        }

        System.out.println(sb.toString());
    }
}