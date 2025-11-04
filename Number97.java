import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        int[][] nums = new int[numbers.length][];
        for (int i = 0; i < numbers.length; i++) {
            nums[i] = new int[] {i, numbers[i]};
        }

        List<int[]> stack = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (stack.isEmpty()) stack.add(new int[] {i, numbers[i]});

            while (!stack.isEmpty() && stack.get(stack.size() - 1)[1] < numbers[i]) {
                answer[stack.remove(stack.size() - 1)[0]] = numbers[i];
            }
            stack.add(new int[] {i, numbers[i]});
        }

        for (int i = 0; i < stack.size(); i++) {
            answer[stack.get(i)[0]] = -1;
        }

        return answer;
    }
}