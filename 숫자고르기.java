import java.io.*;
import java.util.*;

public class 숫자고르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Set<Integer> result = new HashSet<>();
        boolean[] visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;

            Map<Integer, Integer> order = new HashMap<>();
            Deque<Integer> q = new ArrayDeque<>();

            int curr = i; 
            int idx = 0;

            while (true) {
                if (visited[curr]) break;

                visited[curr] = true;
                order.put(curr, idx++);
                q.offerLast(curr);

                curr = arr[curr];

                if (order.containsKey(curr)) {
                    int start = order.get(curr);

                    int count = 0;
                    for (int v : q) {
                        if (count >= start) {
                            result.add(v);
                        }
                        count++;
                    }
                    break;
                }
            }
        }

        List<Integer> answer = new ArrayList<>(result);
        Collections.sort(answer);

        System.out.println(answer.size());
        for (int v : answer) {
            System.out.println(v);
        }
    }
}