
import java.util.TreeMap;

class Solution {
  public int[] solution(String[] operations) {
    TreeMap<Integer, Integer> map = new TreeMap<>();

    for (String operation : operations) {
      String[] s = operation.split(" ");
      String o = s[0];
      int i = Integer.parseInt(s[1]);

      if (o.equals("I")) {
        map.merge(i, 1, Integer::sum);
      } else if (o.equals("D")) {
        if (!map.isEmpty()) {
          int key;
          if (i == 1) {
            key = map.lastKey();
          } else {
            key = map.firstKey();
          }
          map.merge(key, -1, Integer::sum);
          if (map.get(key) == 0)
            map.remove(key);
        }
      }
    }

    return map.isEmpty() ? new int[] { 0, 0 } : new int[] { map.lastKey(), map.firstKey() };
  }
}