class Solution {
    public int[] solution(int n) {
        int[] answer = new int[n*(n + 1)/2];
        int[][] arr = new int[n][n];
        int dx = -1, dy = 0;
        int t = 1;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i % 3 == 0) {
                    dx++;
                } else if (i % 3 == 1) {
                    dy++;
                } else if (i % 3 == 2) {
                    dx--;
                    dy--;
                }
                arr[dx][dy] = t;
                t++;
            }
        }

        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 0) break;
                answer[k++] = arr[i][j];
            }
        }
        return answer;
    }
}