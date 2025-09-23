class Solution {
    public int solution(int[][] sizes) {
        int maxL = 0, maxR = 0;
        for (int i = 0; i < sizes.length; i++) {
            int l = sizes[i][0];
            int r = sizes[i][1];
            if (l > r){
                maxL = Math.max(maxL, l);
                maxR = Math.max(maxR, r);
            } else {
                maxR = Math.max(maxR, l);
                maxL = Math.max(maxL, r);
            }
        }
        return maxL * maxR;
    }
}