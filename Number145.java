
import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
  class Node {
    String word;
    int depth;

    public Node(String word, int depth) {
      this.word = word;
      this.depth = depth;
    }

  }

  public int solution(String begin, String target, String[] words) {

    boolean hasTarget = false;
    for (String w : words) {
      if (w.equals(target)) {
        hasTarget = true;
        break;
      }
    }
    if (!hasTarget)
      return 0;

    Queue<Node> q = new ArrayDeque<>();
    boolean[] visited = new boolean[words.length];
    q.add(new Node(begin, 0));
    while (!q.isEmpty()) {
      Node node = q.poll();
      String cur = node.word;
      int depth = node.depth;

      for (int i = 0; i < words.length; i++) {
        if (!visited[i] && getDiff(cur, words[i]) == 1) {
          if (words[i].equals(target)) {
            return depth + 1;
          }

          visited[i] = true;
          q.add(new Node(words[i], depth + 1));
        }
      }
    }
    return 0;
  }

  public int getDiff(String originString, String newString) {
    int diff = 0;
    for (int i = 0; i < originString.length(); i++) {
      if (originString.charAt(i) != newString.charAt(i)) {
        diff++;
      }
    }
    return diff;
  }
}