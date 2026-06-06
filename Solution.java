import java.io.*;
import java.util.*;

class Solution {

    static int D, W, K;
    static int answer;
    static int[][] film;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            st = new StringTokenizer(br.readLine());

            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            film = new int[D][W];

            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < W; j++) {
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            if (check()) {
                answer = 0;
            } else {
                answer = K;

                for (int i = 1; i <= K; i++) {
                    if (selectRows(0, 0, i, new int[i])) {
                        answer = i;
                        break;
                    }
                }
            }

            System.out.println("#" + tc + " " + answer);
        }
    }

    static boolean selectRows(int start, int depth, int injectCnt, int[] rows) {
        if (depth == injectCnt) {
            return assignDrug(rows, 0);
        }

        for (int i = start; i < D; i++) {
            rows[depth] = i;

            if (selectRows(i + 1, depth + 1, injectCnt, rows)) {
                return true;
            }
        }

        return false;
    }

    static boolean assignDrug(int[] rows, int idx) {

        if (idx == rows.length) {
            return check();
        }

        int row = rows[idx];
        int[] backup = film[row].clone();

        Arrays.fill(film[row], 0);
        if (assignDrug(rows, idx + 1)) {
            return true;
        }

        Arrays.fill(film[row], 1);
        if (assignDrug(rows, idx + 1)) {
            return true;
        }

        film[row] = backup;

        return false;
    }

    static boolean check() {

        if (K == 1) return true;

        for (int col = 0; col < W; col++) {

            int cnt = 1;
            boolean pass = false;

            for (int row = 1; row < D; row++) {

                if (film[row][col] == film[row - 1][col]) {
                    cnt++;
                } else {
                    cnt = 1;
                }

                if (cnt >= K) {
                    pass = true;
                    break;
                }
            }

            if (!pass) {
                return false;
            }
        }

        return true;
    }
}