import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int[][] arr) {
        List<int[]> res = new ArrayList<>();
        check(arr, 0, 0, arr.length - 1, arr.length - 1, res);
        int x = 0;
        int y = 0;
        for (int i = 0; i < res.size(); i++) {
            int[] temp = res.get(i);
            x += temp[0];
            y += temp[1];
        }
        return new int[] {x, y};
    }

    public void check(int[][] arr, int sx,  int sy, int ex, int ey, List<int[]> res) {
        int total = 0;
        for (int i = sx; i <= ex; i++) {
            for (int j = sy; j <= ey; j++) {
                total += arr[i][j];
            }
        }
        if (total == 0) {
            res.add(new int[] {1, 0});
        } else if (total == (ex - sx + 1) * (ey - sy + 1)) {
            res.add(new int[] {0, 1});
        } else {
            int d = (ex - sx + 1) / 2;
            check(arr, sx, sy, sx + d - 1, sy + d - 1, res);
            check(arr, sx, sy + d, sx + d - 1, ey, res);
            check(arr, sx + d, sy, ex, sy + d - 1, res);
            check(arr, sx + d, sy + d, ex, ey, res);
        }
    }
}