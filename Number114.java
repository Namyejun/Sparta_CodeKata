import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {

    public int solution(int N, int[][] road, int K) {
        Map<Integer, List<int[]>> map = new HashMap<>();

        for (int[] edge : road) {
            int node1 = edge[0], node2 = edge[1], dist = edge[2];
            map.computeIfAbsent(node1, k -> new ArrayList<>()).add(new int[] { node2, dist });
            map.computeIfAbsent(node2, k -> new ArrayList<>()).add(new int[] { node1, dist });
        }

        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(i -> i[1]));
        pq.add(new int[] { 1, 0 });

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0], dist = cur[1];

            if (dist > distance[node])
                continue;

            for (int[] next : map.get(node)) {
                int nextNode = next[0], nextDist = distance[node] + next[1];
                if (nextDist < distance[nextNode]) {
                    distance[nextNode] = nextDist;
                    pq.add(new int[] { nextNode, nextDist });
                }
            }
        }

        return (int) Arrays.stream(distance)
                .filter(i -> i <= K)
                .count();
    }

}