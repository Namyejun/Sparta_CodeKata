import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        List<int[]> chickenList = new ArrayList<>();
        List<int[]> homeList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                
                if (map[i][j] == 1) homeList.add(new int[] {i, j});
                if (map[i][j] == 2) chickenList.add(new int[] {i, j});
            }
        }

        int[][] dist = new int[homeList.size()][chickenList.size()];
        
        int H = homeList.size();
        int C = chickenList.size();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < C; j++) {
                int[] home = homeList.get(i);
                int[] chicken = chickenList.get(j);
                dist[i][j] = Math.abs(home[0] - chicken[0]) + Math.abs(home[1] - chicken[1]);
            }
        }

        combi(chickenList.size(), 0, 0, new ArrayList<Integer>(), M, dist);

        System.out.println(answer);
    }

    public static void combi(int N, int depth, int start, List<Integer> temp, int M, int[][] dist) {
        if (depth == M) {
            int total = 0;
            for (int i = 0; i < dist.length; i++) {
                int min = Integer.MAX_VALUE;
                for (int j : temp) {
                    
                    min = Math.min(min, dist[i][j]);
                }
                total += min;
                if (total >= answer) return;
            }
            answer = Math.min(answer, total);
            return;
        }

        for (int i = start; i < N; i++) {
            temp.add(i);
            combi(N, depth + 1, i + 1, temp, M, dist);
            temp.remove(temp.size() - 1);
        }
    }
}
