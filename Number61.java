import java.util.HashSet;
import java.util.Set;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] lst = {6, 6, 5, 4, 3, 2, 1};
        int zeroCount = 0;
        Set<Integer> own = new HashSet<>();
        Set<Integer> win = new HashSet<>();
        for (int i = 0; i < 6; i++) {
            if (lottos[i] == 0) zeroCount++;
            else {
                own.add(lottos[i]);
            }
            win.add(win_nums[i]);
        }
        own.retainAll(win);
        int inter = own.size();
        return new int[] {(inter + zeroCount > 6) ? lst[6] : lst[inter + zeroCount], lst[inter]};
    }
}