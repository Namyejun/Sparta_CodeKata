import java.util.PriorityQueue;

class Solution {
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < score.length; i++) {
            if (k > queue.size()) {
                queue.add(score[i]);
                answer[i] = queue.remove();
                queue.add(answer[i]);
            } else {
                int last = queue.remove();
                if (score[i] > last) {
                    queue.add(score[i]);
                    answer[i] = queue.remove();
                    queue.add(answer[i]);
                } else {
                    queue.add(last);
                    answer[i] = queue.remove();
                    queue.add(answer[i]);
                }
            }
        }
        return answer;
    }
}