class Solution {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();
        int lenAnswer = number.length() - k;
        int startIndex = 0;
        while (startIndex < number.length() && answer.length() != lenAnswer){
            int endNum = k + answer.length() ;
            int maxNum = 0;

            for (int i = startIndex; i <= endNum; i++){
                if (maxNum < number.charAt(i) - '0') { 
                    maxNum = number.charAt(i) - '0';

                    startIndex = i + 1;
                }
            }
            answer.append(Integer.toString(maxNum));
        }
        return answer.toString();
    }
}