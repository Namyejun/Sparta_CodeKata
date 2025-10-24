public class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int) (right - left) + 1];
        int t = 0;
        for (long i = left; i < right + 1; i++) {
            int r = (int)(i / n) + 1;
            int c = (int)(i % n) + 1;
            answer[t] = (r > c) ? r : c;
            t += 1;
        }
        return answer;
    }
}