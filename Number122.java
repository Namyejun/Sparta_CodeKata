
import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        int c = col - 1;
        int rb = row_begin - 1;
        int re = row_end - 1;
        Arrays.sort(data, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[c] != o2[c] ? o1[c] - o2[c] : o2[0] - o1[0];
            }
        });
        int[] s = new int[re - rb + 1];
        for (int i = rb; i <= re; i++) {
            int temp = 0;
            for (int t : data[i])
                temp += t % (i + 1);
            s[i - rb] = temp;
        }

        for (int i = 0; i < s.length; i++) {
            answer ^= s[i];
        }

        return answer;
    }
}