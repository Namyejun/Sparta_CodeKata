class Solution {
    int[][] answer;
    int idx = 0;

    public int[][] solution(int n) {
        answer = new int[(int) Math.pow(2, n) - 1][2];
        hanoi(n, 1, 3);
        return answer;
    }

    void hanoi(int n, int from, int to) {
        if (n == 1) {
            answer[idx][0] = from;
            answer[idx][1] = to;
            idx++;
            return;
        }
        int stopover = 6 - from - to;
        hanoi(n - 1, from, stopover);
        hanoi(1, from, to);
        hanoi(n - 1, stopover, to);
    }
}

/*
 * 위에 쌓인 N - 1개의 1 -> 2로 이동
 * 1개의 가장 큰 원판을 1 -> 3로 이동
 * N - 1개를 2 -> 3으로 이동
 */