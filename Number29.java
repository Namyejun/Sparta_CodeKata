class Solution {
    public long solution(long n) {
        double sqrt = Math.sqrt(n);
        if (sqrt - (long) sqrt == 0) {
            return ((long) sqrt + 1L) * ((long) sqrt + 1L);
        } else {
            return -1;
        }
    }
}