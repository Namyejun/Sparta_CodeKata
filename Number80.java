public class Solution {
    public int solution(int n, int a, int b) {
        int answer = 1;
        a -= 1;
        b -= 1;

        while (a != b) {
            a = a / 2;
            b = b / 2;
            
            answer += 1;
        }
        return answer - 1;
    }
}