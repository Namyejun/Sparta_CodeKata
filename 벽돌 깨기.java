import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {
    static int minBlockCnt;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());

            int[][] arr = new int[H][W];
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            minBlockCnt = Integer.MAX_VALUE;

            dfs(arr, N, H, W, 0);

            System.out.println("#" + test_case + " " + minBlockCnt);
        }
    }

    public static int[][] copyMap(int[][] arr, int H, int W) {
        int[][] temp = new int[H][W];
        for (int i = 0; i < H; i++) {
            System.arraycopy(arr[i], 0, temp[i], 0, W);
        }
        return temp;
    }

    public static void dfs(int[][] arr, int N, int H, int W, int level) {
        int currentBlocks = countBlocks(arr, H, W);
        if (currentBlocks == 0) {
            minBlockCnt = 0;
            return;
        }

        if (level == N) {
            minBlockCnt = Math.min(currentBlocks, minBlockCnt);
            return;
        }

        for (int i = 0; i < W; i++) {
            int[][] backup = copyMap(arr, H, W);

            int[][] nextMap = shot(backup, N, H, W, i);

            dfs(nextMap, N, H, W, level + 1);
        }
    }

    public static int countBlocks(int[][] arr, int H, int W) {
        int cnt = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (arr[i][j] != 0) cnt++;
            }
        }
        return cnt;
    }

    public static int[][] shot(int[][] arr, int N, int H, int W, int y) {
        Queue<int[]> q = new LinkedList<>();
        int[][] visited = new int[H][W];
        int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int x = -1;
        for (int i = 0; i < H; i++) {
            if (arr[i][y] != 0) {
                x = i;
                break;
            }
        }

        if (x == -1) {
            return arr;
        }

        q.offer(new int[] {x, y});
        visited[x][y] = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0], cy = cur[1];

            for (int i = 0; i < 4; i++) {
                for (int k = 1; k < arr[cx][cy]; k++) { 
                    int nx = cx + k * move[i][0];
                    int ny = cy + k * move[i][1];
                    
                    if (0 <= nx && nx < H && 0 <= ny && ny < W) {
                        if (visited[nx][ny] == 0 && arr[nx][ny] != 0) {
                            q.offer(new int[] {nx, ny});
                            visited[nx][ny] = 1;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (visited[i][j] == 1) {
                    arr[i][j] = 0;
                }
            }
        }

        return shrink(arr, H, W);
    }

    public static int[][] shrink(int[][] arr, int H, int W) {
        for (int j = 0; j < W; j++) {
            for (int i = H - 1; i >= 0; i--) {
                if (arr[i][j] == 0) {
                    for (int k = i - 1; k >= 0; k--) {
                        if (arr[k][j] != 0) {
                            arr[i][j] = arr[k][j];
                            arr[k][j] = 0;
                            break;
                        }
                    }
                }
            }
        }
        return arr;
    }
}