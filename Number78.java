class Solution {
    public int solution(int n) {
        int[] fibb = new int[100001];
        fibb[0] = 0;
        fibb[1] = 1;
        for (int i = 2; i < 100001; i++) {
            fibb[i] = (fibb[i - 2] + fibb[i - 1]) % 1234567;
        }

        return fibb[n];
    }
}