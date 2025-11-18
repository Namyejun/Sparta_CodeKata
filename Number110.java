import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        Deque<Long> q1 = new ArrayDeque<>();
        Deque<Long> q2 = new ArrayDeque<>();

        for (int i = 0; i < queue1.length; i++) {
            q1.addLast(Long.valueOf(queue1[i]));
            q2.addLast(Long.valueOf(queue2[i]));
        }

        int l = q1.size() * 4;
        long sum1 = Arrays.stream(queue1).sum();
        long sum2 = Arrays.stream(queue2).sum();

        if ((sum1 + sum2) % 2 != 0) {
            return -1;
        }
        while (true) {
            if (sum1 > sum2) {
                long out = q1.removeFirst();
                q2.addLast(out);

                sum1 -= out;
                sum2 += out;
                answer += 1;
            } else if (sum1 < sum2) {
                long out = q2.removeFirst();
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