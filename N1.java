
import java.util.Stack;

public class N1 {
  public static void main(String[] args) {

  }

  public boolean solution(int[] arr) {
    int t = 1;
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] >= t) {
        while (t <= arr[i]) {
          stack.push(t);
          t++;
        }
      }
      if (stack.isEmpty() || stack.pop() != arr[i])
        return false;
    }
    return true;
  }
}
