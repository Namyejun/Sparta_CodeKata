class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {};

        for (int i = 2; i < (brown + yellow) / 2; i++) {
            if ((brown + yellow) % i == 0) {
                int j = (brown + yellow) / i;
                if ((i - 2) * (j - 2) == yellow) {
                    answer = new int[] {i, j};
                }
            }
        }
        
        return answer;
    }
}