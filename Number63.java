class Solution {
    public String solution(String X, String Y) {
        String answer = "";
        int[] arrX = {0,0,0,0,0,0,0,0,0,0};
        int[] arrY = {0,0,0,0,0,0,0,0,0,0};
        for (int i = 0; i < X.length(); i++) {
            arrX[X.charAt(i) - '0']++;
        }
        for (int i = 0; i < Y.length(); i++) {
            arrY[Y.charAt(i) - '0']++;
        }
        for (int i = 9; i >= 0; i--) {
            answer += String.valueOf(i).repeat(arrX[i] < arrY[i] ? arrX[i] : arrY[i]);
        }
        if (answer.equals("")) answer = "-1";
        if (answer.startsWith("0")) answer = "0";
        return answer;
    }
}