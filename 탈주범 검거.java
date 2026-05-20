import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
*/

class Solution
{
	public static void main(String args[]) throws Exception
	{
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
		//System.setIn(new FileInputStream("res/input.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T = Integer.parseInt(br.readLine());
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

		

		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			/////////////////////////////////////////////////////////////////////////////////////////////
			/*
				 이 부분에 여러분의 알고리즘 구현이 들어갑니다.
			 */
			StringTokenizer st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken()), 
				M = Integer.parseInt(st.nextToken()), 
				R = Integer.parseInt(st.nextToken()), 
				C = Integer.parseInt(st.nextToken()),
				L = Integer.parseInt(st.nextToken());
			
			int[][] arr = new int[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int[][][] pipe = {
				{}, 
				{{0, 1}, {1, 0}, {0, -1}, {-1, 0}},  // 우하좌상
				{{1, 0}, {-1, 0}}, // 상하
				{{0, 1}, {0, -1}}, // 좌우
				{{0, 1}, {-1, 0}}, // 상우
				{{0, 1}, {1, 0}}, // 하우
				{{1, 0}, {0, -1}}, // 하좌
				{{0, -1}, {-1, 0}} // 상좌
			};
			
			Deque<int[]> q = new ArrayDeque<>();
			int[][] visited = new int[N][M];

			q.offerFirst(new int[] {R, C});
			visited[R][C] = 1;

			while (!q.isEmpty()) {
				int[] cur = q.pollLast();
				int cx = cur[0], cy = cur[1];
				int cp = arr[cx][cy];

				for (int i = 0; i < pipe[cp].length; i++) {
					int dx = pipe[cp][i][0], dy = pipe[cp][i][1];
					int nx = cx + dx, ny = cy + dy;

					if (0 <= nx && nx < N && 0 <= ny && ny < M) { // 맵 밖으로 나가는지
						if (visited[nx][ny] == 0) { // 방문 안 한 곳인지.
							int np = arr[nx][ny]; // 새로 가는 파이프
							if (chkValid(pipe[np], dx, dy)) { // 파이프 연결 됐는지 확인
								if (visited[cx][cy] + 1 <= L) { // 시간적으로 문제 없는지 확인
									q.offerFirst(new int[] {nx, ny});
									visited[nx][ny] = visited[cx][cy] + 1;
								}
							}
						}
					}
				}
			}

			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (visited[i][j] != 0) cnt++;
				}
			}

			System.out.printf("#%d %d\n", test_case, cnt);
			/////////////////////////////////////////////////////////////////////////////////////////////

		}
	}

	public static boolean chkValid(int[][] np, int dx, int dy) {
		for (int i = 0; i < np.length; i++) {
			if (np[i][0] == -dx && np[i][1] == -dy) {
				return true;
			}
		}
		return false;
	}
}