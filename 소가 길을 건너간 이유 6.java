import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()), 
            K = Integer.parseInt(st.nextToken()), 
            R = Integer.parseInt(st.nextToken());


        int[][] move = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // r2 - r1, c2 - c1
        int[][][] roads = new int[N][N][4];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken()) - 1, 
                c1 = Integer.parseInt(st.nextToken()) - 1, 
                r2 = Integer.parseInt(st.nextToken()) - 1, 
                c2 = Integer.parseInt(st.nextToken()) - 1;

            int dir;

            if (r2 - r1 == 0 && c2 - c1 == 1) {
                dir = 0;
            } else if (r2 - r1 == 1 && c2 - c1 == 0) {
                dir = 1;
            } else if (r2 - r1 == 0 && c2 - c1 == -1) {
                dir = 2;
            } else {
                dir = 3;
            }

            roads[r1][c1][dir] = 1;
            roads[r2][c2][(dir + 2) % 4] = 1;
        }

        
        int[][] visited = new int[N][N];
        int chkGrp = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] == 0) {
                    Deque<int[]> q = new ArrayDeque<>();
                    
                    q.offerLast(new int[] {i, j});
                    visited[i][j] = chkGrp;

                    while (!q.isEmpty()) {
                        int[] cur = q.pollFirst();
                        int curX = cur[0], curY = cur[1];

                        for (int k = 0; k < 4; k++) {
                            int newX = curX + move[k][0];
                            int newY = curY + move[k][1];
                            if (0 <= newX && newX < N && 0 <= newY && newY < N && visited[newX][newY] == 0 && roads[curX][curY][k] == 0) {
                                q.offerLast(new int[] {newX, newY});
                                visited[newX][newY] = chkGrp;
                            }
                        }
                    }
                    chkGrp++;
                }
            }
        }


        Map<Integer, Integer> map = new HashMap<>(); 
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken()) - 1, 
                c = Integer.parseInt(st.nextToken()) - 1;

            map.put(visited[r][c], map.getOrDefault(visited[r][c], 0) + 1);
        }

        // for (int i = 0; i < N; i++) {
        //     for (int j = 0; j < N; j++) {
        //         System.out.print(visited[i][j]);
        //     }
        //     System.out.println();
        // }

        int answer = 0;
        Integer[] arr = map.values().toArray(new Integer[0]);

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                answer += arr[i] * arr[j];
            }
        }
        System.out.println(answer);
    }
}
