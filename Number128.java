
import java.util.PriorityQueue;

class Solution {
  public int solution(int n, int k, int[] enemy) {
    int answer = 0;
    if (k == enemy.length)
      return k;

    PriorityQueue<Integer> q = new PriorityQueue<>();
    for (int e : enemy) {
      if (k > 0) {
        k--;
        q.offer(e);
      } else {
        int num = q.poll();
        if (num < e) { // q 바꿔치기
          q.offer(e);
          n -= num;
        } else { // q 안바꿔치기
          n -= e;
          q.offer(num);
        }
        if (n < 0)
          break;
      }
      answer++;
    }
    return answer;
  }
}