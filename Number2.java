class Solution {
    public int solution(int num1, int num2) {
        int answer = num1 * num2;
        return answer;
    }
}

class Number2 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(1, 2));
    }
}