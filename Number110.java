import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -2;
        Deque<Integer> q1 = new ArrayDeque<>(Arrays.stream(queue1).boxed().toList());
        Deque<Integer> q2 = new ArrayDeque<>(Arrays.stream(queue2).boxed().toList());

        int l = q1.size() * 4;
        int sum1 = Arrays.stream(queue1).sum();
        int sum2 = Arrays.stream(queue2).sum();

        if ((sum1 + sum2) % 2 != 0) {
            return -1;
        }
        while (true) {
            if (sum1 > sum2) {
                int out = q1.removeFirst();
                q2.addLast(out);

                sum1 -= out;
                sum2 += out;
                answer += 1;
            } else if (sum1 < sum2) {
                int out = q2.removeFirst();
                q1.addLast(out);

                sum2 -= out;
                sum1 += out;
                answer += 1;
            } else {
                break;
            }

            if (answer == l) {
                answer = -1;
                break;
            }
        }

        return answer;
    }
}