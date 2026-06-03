import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
		for(int test_case = 1; test_case <= T; test_case++)
		{
            int K = Integer.parseInt(br.readLine());
            int[][] magnet = new int[4][8];
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 8; j++) {
                    magnet[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());

                int m = Integer.parseInt(st.nextToken()) - 1, d = Integer.parseInt(st.nextToken());
                rotate(magnet, m, d);
            }

            int answer = 0;
            for (int i = 0; i < 4; i++) {
                if (magnet[i][0] == 1) {
                    answer += Math.pow(2, i);
                }
            }

            System.out.printf("#%d %d\n", test_case, answer);
		}
	}

    public static void rotate(int[][] magnet, int m, int d) {
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[4];
        q.offer(new int[] {m, d});
        visited[m] = true;
        
        int[] result = new int[4];
        result[m] = d;

        while (!q.isEmpty()) {
            int[] c = q.poll();
            int cm = c[0], cd = c[1];

            for (int i = -1; i < 2; i += 2) { // -1, 1
                int nm = cm + i; // cm 양 옆에 애를 보겠지
                if (0 <= nm && nm < 4 && !visited[nm]) {
                    if (isNotEqual(magnet, cm, nm)) {
                        int nd = cd * -1;
                        result[nm] = nd;
                        q.offer(new int[] {nm, nd});
                        visited[nm] = true;
                    }
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            int[] rotatedMagent = rotateOne(magnet[i], result[i]);
            magnet[i] = rotatedMagent;
        }
    }

    public static int[] rotateOne(int[] ori, int d) {
        if (d == 1) {
            int[] rotateMagnet = new int[8];
            for (int i = 1; i < 8; i++) {
                rotateMagnet[i] = ori[i - 1];
            }
            rotateMagnet[0] = ori[7];
            return rotateMagnet;
        } else if (d == -1){
            int[] rotateMagnet = new int[8];
            for (int i = 0; i < 7; i++) {
                rotateMagnet[i] = ori[i + 1];
            }
            rotateMagnet[7] = ori[0];
            return rotateMagnet;
        } else {
            return ori;
        }
    }

    public static boolean isNotEqual(int[][] magnet, int a, int b) {
        if (a < b) {
            return magnet[a][2] != magnet[b][6];
        } else {
            return magnet[a][6] != magnet[b][2];
        }
    }
}