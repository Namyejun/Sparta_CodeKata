import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int solution(int[] cards) {
        int n = cards.length;
        boolean[] visited = new boolean[n];
        List<Integer> cycleSizes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int count = 0;
                int cur = i;

                while (!visited[cur]) {
                    visited[cur] = true;
                    cur = cards[cur] - 1;
                    count++;
                }

                if (count > 0) {
                    cycleSizes.add(count);
                }
            }
        }

        if (cycleSizes.size() < 2)
            return 0;

        cycleSizes.sort(Collections.reverseOrder());
        return cycleSizes.get(0) * cycleSizes.get(1);
    }
}