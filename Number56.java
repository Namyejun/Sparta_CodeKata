import java.util.Arrays;

class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        int[] sorted = Arrays.stream(score).sorted().toArray();
        for (int i = sorted.length - m; i >= 0; i -= m) {
            int[] copied = Arrays.copyOfRange(sorted, i, i + m);
            answer += copied[0] * copied.length;
        }
        return answer;
    }
}