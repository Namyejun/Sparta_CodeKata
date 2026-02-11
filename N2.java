
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class N2 {
  public static void main(String[] args) {

  }

  public int[] solution(int[] arr) {
    int[] sorted = Arrays.stream(arr)
        .distinct()
        .sorted()
        .toArray();

    int bottom = 0;
    int top = arr.length - 1;

    int t = 0;
    Deque<Integer> q = new ArrayDeque<>();
    if (sorted.length % 2 == 0)
      t++;
    while (bottom <= top) {
      if (t % 2 == 0) {
        q.addFirst(arr[bottom++]);
      } else {
        q.addFirst(arr[top--]);
      }
      t++;
    }
    int[] answer = q.stream().mapToInt(Integer::intValue).toArray();
    return answer;
  }
}
