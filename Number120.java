class Solution {
    public long solution(long w, long h) {
        long answer = 0;
        double before = 0;
        for (int i = 1; i <= w; i++) {
            double y = ((double) h * i / (double) w);
            y = y > h ? h : y;
            answer += Math.ceil(y - before);
            before = Math.floor(y);
        }
        return w * h - answer;
    }
}