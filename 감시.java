// 이차원 배열 clone은 그냥 clone 하면 안 됨.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        int[][] arr = new int[N][M];
        List<int[]> cctvs = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (arr[i][j] >= 1 && arr[i][j] <= 5) {
                    cctvs.add(new int[] {i, j, arr[i][j]});
                }
            }
        }

        dfs(arr, cctvs, 0);
        int min = 99999;
        for (int i : lst) {
            min = Math.min(i, min);
        }
        System.out.println(min);
    }

    static int[][][] first = {{{-1, 0}}, {{0, 1}}, {{1, 0}}, {{0, -1}}};
    static int[][][] second = {{{-1, 0}, {1, 0}}, {{0, -1}, {0, 1}}};
    static int[][][] third = {{{-1, 0}, {0, 1}},{{0, 1}, {1, 0}},{{1, 0}, {0, -1}},{{0, -1}, {-1, 0}}};
    static int[][][] fourth = {{{-1, 0}, {0, 1}, {1, 0}}, {{0, 1}, {1, 0}, {0, -1}}, {{1, 0}, {0, -1}, {-1, 0}}, {{0, -1}, {-1, 0}, {0, 1}}};
    static int[][][] fifth = {{{-1, 0}, {0, 1}, {1, 0}, {0, -1}}};
    static List<Integer> lst = new ArrayList<>();

    public static int[][][] getCCTV(int number) {
        switch (number) {
            case 1:
                return first;
            case 2:
                return second;
            case 3:
                return third;
            case 4:
                return fourth;
            case 5:
                return fifth;
            default:
                return null;
        }
    }

    public static int getZerosCount(int[][] arr) { // 사각지대 개수 구하기
        int c = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 0) c++;
            }
        }
        return c;
    }

    public static void dfs(int[][] arr, List<int[]> cctvs, int idx) {
        if (idx >= cctvs.size()) { // cctv 다 돌면 lst에 추가
            lst.add(getZerosCount(arr));
            return;
        }

        int[][][] cctvCandidate = getCCTV(cctvs.get(idx)[2]);
        for (int i = 0; i < cctvCandidate.length; i++) {
            int[][] cctv = cctvCandidate[i];

            int[][] newArr = new int[arr.length][arr[0].length];
            for (int k = 0; k < arr.length; k++) {
                newArr[k] = arr[k].clone();
            }
            checkVisible(newArr, cctvs.get(idx)[0], cctvs.get(idx)[1], cctv);
            dfs(newArr, cctvs, idx + 1);
        }
    }

    public static int[][] checkVisible(int[][] arr, int cctvX, int cctvY, int[][] cctv) { // 시야 확보되는 곳 체크
        for (int i = 0; i < cctv.length; i++) {
            int cx = cctvX, cy = cctvY;
            int dx = cctv[i][0], dy = cctv[i][1];
            while (isAvailable(cx + dx, cy + dy, arr)) {
                cx += dx;
                cy += dy;
                arr[cx][cy] = -1;
            }
        }
        return arr;
    }

    public static boolean isAvailable(int x, int y, int[][] arr) { // 움직일 수 있는 곳인지 체크
        if (x >= 0 && x < arr.length && y >= 0 && y < arr[0].length && arr[x][y] != 6) return true;
        return false;
    }
}