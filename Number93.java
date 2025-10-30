import java.util.ArrayList;
import java.util.List;

class Solution {
    public int solution(int k, int[][] dungeons) {
        int answer = -1;

        int[] arr = new int[dungeons.length];
        int len = dungeons.length;
        for (int i = 0; i < len; i++) arr[i] = i;

        List<int[]> permutations = getPermutations(arr, len);

        for (int[] permutation : permutations) {
            int pirodo = k;
            int cnt = 0;
            for (int i = 0; i < len; i++) {
                int filyo = dungeons[permutation[i]][0];
                int somo = dungeons[permutation[i]][1];

                if (pirodo >= filyo) {
                    pirodo -= somo;
                    cnt++;
                } else {
                    if (cnt == 0) {
                        cnt = -1;
                        break;
                    }
                }
            }
            answer = Math.max(cnt, answer);
        }

        return answer;
    }

    public static List<int[]> getPermutations(int[] arr, int r) {
        List<int[]> permutations = new ArrayList<>();
        boolean[] visited = new boolean[arr.length];
        makePermutations(arr, new int[r], visited, 0, r, permutations);
        return permutations;
    }

    public static void makePermutations(int[] arr, int[] selected, boolean[] visited, int depth, int r, List<int[]> permutations) {
        if (depth == r) {
            permutations.add(selected.clone());
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                selected[depth] = arr[i];
                makePermutations(arr, selected, visited, depth + 1, r, permutations);
                visited[i] = false;
            }
        }
    }
}