import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        int temp = location;
        Queue<Integer> q = Arrays.stream(priorities).boxed().collect(Collectors.toCollection(LinkedList::new));
        
        Arrays.sort(priorities);
        int len = priorities.length - 1;

        while (!q.isEmpty()) {
            Integer i = q.poll();
            if (i == priorities[len - answer]) {
                answer++;
                temp--;
                if (temp < 0) break;
            } else {
                q.add(i);
                temp--;
                if (temp < 0) temp = q.size() - 1;
            }
        }

        return answer;
    }
}