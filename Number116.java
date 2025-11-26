class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        long hypotenuse = (long) Math.pow(d, 2);
        for (int i = 0; i <= d; i += k) {
            long adjacent = (long) Math.pow(i, 2);
            long opposite = (long) Math.sqrt(hypotenuse - adjacent);
            answer += opposite / k + 1;
        }
        return answer;
    }
}