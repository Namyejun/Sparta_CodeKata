class Solution {
    public int solution(int angle) {
        int answer = 0;

        if (0 < angle && angle < 90) {
            answer = 1;
        } else if (angle == 90) {
            answer = 2;
        } else if (90 < angle && angle < 180) {
            answer = 3;
        } else if (angle == 180) {
            answer = 4;
        } else {
            new IllegalArgumentException("입력된 값이 맘에 안듭니다.");
        }
        
        return answer;
    }
}