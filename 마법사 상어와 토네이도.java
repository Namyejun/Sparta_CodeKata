import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0; // 나간 모래 개수 체크용

        int mid = N / 2; // 가운데 좌표 용도

        int[][] dir = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}}; // 이동 방향
        int[][][] postion = { // 모래 이동 비율
            {{0, 0, 2, 0, 0}, {0, 10, 7, 1, 0}, {5, -1, 0, 0, 0}, {0, 10, 7, 1, 0}, {0, 0, 2, 0, 0}}, 
            {{0, 0, 0, 0, 0}, {0, 1, 0, 1, 0}, {2, 7, 0, 7, 2}, {0, 10, -1, 10, 0}, {0, 0, 5, 0, 0}}, 
            {{0, 0, 2, 0, 0}, {0, 1, 7, 10, 0}, {0, 0, 0, -1, 5}, {0, 1, 7, 10, 0}, {0, 0, 2, 0, 0}}, 
            {{0, 0, 5, 0, 0}, {0, 10, -1, 10, 0}, {2, 7, 0, 7, 2}, {0, 1, 0, 1, 0}, {0, 0, 0, 0, 0}}
        };

        int[][] visited = new int[N][N]; // 방문 배열 표

        int r = mid, c = mid;
        int d = 0;
        visited[r][c] = 1;


        while (!(r == 0 && c == 0)) { // 0, 0 만나면 탈출
            // 이동, r,c와 함께 모래도 이동
            int nr = r + dir[d][0];
            int nc = c + dir[d][1];
            visited[nr][nc] = 1;

            int originSand = arr[nr][nc];
            int tempSand = 0; // alpha 계산을 위한 체크 변수
            int ar = 0, ac = 0; // alpha 위치 찾기용
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    int p = postion[d][i][j];
                    if (p == -1) { // alpha 위치 찾음
                        ar = nr + (i - 2);
                        ac = nc + (j - 2);
                        continue;
                    }

                    // 밖에 나갔는지 체크
                    int cr = nr + (i - 2);
                    int cc = nc + (j - 2);
                    
                    if (0 <= cr && cr < N && 0 <= cc && cc < N) {
                        arr[cr][cc] += originSand * p / 100;
                        tempSand += originSand * p / 100;
                    } else {
                        answer += originSand * p / 100;
                        tempSand += originSand * p / 100;
                    }
                }
            }
            arr[nr][nc] = 0;
            if (0 <= ar && ar < N && 0 <= ac && ac < N) {
                arr[ar][ac] += originSand - tempSand;
            } else {
                answer += originSand - tempSand;
            }

            // for (int i = 0; i < N; i++) {
            //     for (int j = 0; j < N; j++) {
            //         System.out.print(arr[i][j] + " ");
            //     }
            //     System.out.println();
            // }
            // System.out.println();
            r = nr;
            c = nc;

            // 다음 행선지 방문했는지 체크
            // 방문 안했으면 d = (d + 1) % 4
            // 했으면 그대로
            int nd = (d + 1) % 4;
            int tr = r + dir[nd][0];
            int tc = c + dir[nd][1];
            if (0 <= tr && tr < N && 0 <= tc && tc < N && visited[tr][tc] == 0) {
                d = nd;
            }
        }

        System.out.println(answer);

    }
}
