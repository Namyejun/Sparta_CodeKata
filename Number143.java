
import java.util.PriorityQueue;

class Solution {
  public int[] solution(String[] operations) {
    PriorityQueue<Integer> minQueue = new PriorityQueue<>();
    PriorityQueue<Integer> maxQueue = new PriorityQueue<>();

    for (String operation : operations) {
      String[] s = operation.split(" ");
      String o = s[0];
      int i = Integer.parseInt(s[1]);

      switch (o) {
        case "I":
          minQueue.add(i);
          maxQueue.add(-i);
          break; // switch 문에서 필수
        case "D":
          if (minQueue.isEmpty() || maxQueue.isEmpty())
            continue;
          else {
            if (i == 1) {
              int max = maxQueue.poll();
              minQueue.remove(-max);
            } else if (i == -1) {
              int min = minQueue.poll();
              maxQueue.remove(-min);
            }
          }
          break;
        default:
          break;
      }
    }
    if (minQueue.isEmpty() || maxQueue.isEmpty())
      return new int[] { 0, 0 };
    return new int[] { -maxQueue.peek(), minQueue.peek() };
  }
}