class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            long n = numbers[i];
            if ((n & 1) == 0) {
                answer[i] = n + 1;
            } else {
                long bit = (~n) & (n + 1);
                answer[i] = n + bit - (bit >> 1);
            }
        }
        return answer;
    }
}