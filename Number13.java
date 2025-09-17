class Solution {
    public int solution(int left, int right) {
        int answer = 0;
        init();
        for (int i = left; i <= right; i++) {
            answer = arr[i] % 2 == 0 ? answer + i : answer - i;
        }
        return answer;
    }

    private int[] arr = new int[1001];

    public void init() {
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 2;
        for (int i = 3; i <= 1000; i++) {
            arr[i] = countDivisors(i);
        }
    }

    private int countDivisors(int n) {
        int val = 2;

        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) val++;
        }

        return val;
    }
}