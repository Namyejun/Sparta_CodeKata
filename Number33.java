class Solution {
    public int solution(int num) {
        int i = 0;
        long t = num;
        while (t != 1) {
            if (i >= 500) {
                i = -1;
                break;
            }
            t = (t % 2 == 0) ? t / 2 : (t * 3) + 1;
            i++;
        }
        return i;
    }
}