import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken()), T = Integer.parseInt(st.nextToken());

        int[][] arr = new int[R][C];
        List<int[]> airPurifier = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == -1) airPurifier.add(new int[] {i, j});
            }
        }

        while (T != 0) {
            // 1. 미세먼지의 확산
            List<int[]> dusts = getDusts(R, C, arr);
            diffusion(R, C, arr, dusts);

            // 2. 공기청정기의 가동
            runAirPurifier1(R, C, arr, airPurifier.get(0)[0], airPurifier.get(0)[1]);
            runAirPurifier2(R, C, arr, airPurifier.get(1)[0], airPurifier.get(1)[1]);
            T--;
        }
        int answer = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] != -1) {
                    answer += arr[i][j];
                }
            }
        }

        System.out.println(answer);
    }

    static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static List<int[]> getDusts(int R, int C, int[][] arr) {
        List<int[]> dusts = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] > 0) {
                    dusts.add(new int[] {i, j});
                }
            }
        }
        return dusts;
    }

    public static void diffusion(int R, int C, int[][] arr, List<int[]> dusts) {
        Map<String, Integer> changed = new HashMap<>();

        for (int[] dust: dusts) {
            int x = dust[0], y = dust[1];
            int curDust = arr[x][y];
            int tempTotal = 0;
            for (int i = 0; i < 4; i++) {
                int nx = x + move[i][0];
                int ny = y + move[i][1];

                if (0 <= nx && nx < R && 0 <= ny && ny < C && arr[nx][ny] != -1) {
                    
                    changed.put(nx + " " + ny, changed.getOrDefault(nx + " " + ny, 0) + curDust / 5);
                    tempTotal += curDust / 5;
                }
            }
            changed.put(x + " " + y, changed.getOrDefault(x + " " + y, 0) - tempTotal);
        }
        
        changed.forEach((k, v) -> {
            String[] keyString = k.split(" ");
            int x = Integer.parseInt(keyString[0]);
            int y = Integer.parseInt(keyString[1]);

            arr[x][y] += v;
        });
    }

    public static void runAirPurifier1(int R, int C, int[][] arr, int sx, int sy) {
        int[][] dir = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
        int i = 0;

        int nx = sx;
        int ny = sy + 1;

        int before = arr[nx][ny];
        arr[nx][ny] = 0;

        while (true) {
            int tx = nx + dir[i][0];
            int ty = ny + dir[i][1];

            if (tx < 0 || tx > sx || ty < 0 || ty >= C) {
                i++;
                continue;
            }

            if (tx == sx && ty == sy) {
                arr[sx][sy] = -1;
                break;
            }

            int temp = arr[tx][ty];
            arr[tx][ty] = before;
            before = temp;

            nx = tx;
            ny = ty;
        }
    }

    public static void runAirPurifier2(int R, int C, int[][] arr, int sx, int sy) {
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int i = 0;

        int nx = sx;
        int ny = sy + 1;

        int before = arr[nx][ny];
        arr[nx][ny] = 0;

        while (true) {
            int tx = nx + dir[i][0];
            int ty = ny + dir[i][1];

            if (tx < sx || tx >= R || ty < 0 || ty >= C) {
                i++;
                continue;
            }

            if (tx == sx && ty == sy) {
                arr[sx][sy] = -1;
                break;
            }

            int temp = arr[tx][ty];
            arr[tx][ty] = before;
            before = temp;

            nx = tx;
            ny = ty;
        }
    }
}