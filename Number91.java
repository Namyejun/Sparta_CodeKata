import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> temp = new ArrayList<>();

        PriorityQueue<Integer> stack = new PriorityQueue<>();

        for (int i = 0; i < progresses.length; i++) {
            Integer deadline = getDeadline(progresses[i], speeds[i]);

            if (stack.isEmpty()) {
                stack.add(-deadline);
            } else {
                if (-stack.peek() >= deadline) {
                    stack.add(-deadline);
                } else {
                    temp.add(stack.size());
                    stack.clear();
                    stack.add(-deadline);
                }
            }
        }

        if (!stack.isEmpty()) temp.add(stack.size());

        return temp.stream().mapToInt(i -> i).toArray();
    }

    public Integer getDeadline(int a, int b) {
       Double val = Math.ceil((double) (100 - a) / b);
       return val.intValue();
    }
}