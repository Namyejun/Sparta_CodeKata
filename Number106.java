import java.util.Stack;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        int temp = 1;
        for (int i = 0; i < order.length; i++) {
            for (; temp < order[i]; temp++) {
                stack.push(temp);
            }
            if (temp == order[i]) {
                temp++;
            } else if (!stack.empty() && stack.peek() == order[i]) {
                stack.pop();
            } else {
                answer = i;
                break;
            }
        }
        if (stack.empty()) {
            answer = order.length;
        }
        return answer;
    }
}