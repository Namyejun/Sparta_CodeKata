class Solution {
    public long solution(int a, int b) {
        return (long) ((a + b) / 2.0 * ((a < b) ? b - a + 1 : a - b + 1));
    }
}