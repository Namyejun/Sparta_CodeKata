import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    static int row, col;
    static char[][] map;
    static int[][] time;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    public int solution(String[] maps) {
        row = maps.length;
        col = maps[0].length();
        map = new char[row][col];

        int sx = 0, sy = 0;
        int lx = 0, ly = 0;
        int ex = 0, ey = 0;

        for (int i = 0; i < row; i++) {
            map[i] = maps[i].toCharArray();
            for (int j = 0; j < col; j++) {
                if (map[i][j] == 'S') {
                    sx = i;
                    sy = j;
                } else if (map[i][j] == 'L') {
                    lx = i;
                    ly = j;
                } else if (map[i][j] == 'E') {
                    ex = i;
                    ey = j;
                }
            }
        }
        int toKeyTime = bfs(sx, sy, lx, ly);
        if (toKeyTime == -1)
            return -1;

        int toEndTime = bfs(lx, ly, ex, ey);
        if (toEndTime == -1)
            return -1;

        return toKeyTime + toEndTime;

    }

    public static int bfs(int sX, int sY, int eX, int eY) {
        Queue<int[]> q = new LinkedList<>();
        time = new int[row][col];

        for (int i = 0; i < row; i++)
            Arrays.fill(time[i], -1);

        q.add(new int[] { sX, sY });
        time[sX][sY] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            if (x == eX && y == eY) {
                return time[x][y];
            }

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx < 0 || ny < 0 || nx >= row || ny >= col)
                    continue;
                if (map[nx][ny] == 'X')
                    continue;
                if (time[nx][ny] != -1)
                    continue;

                time[nx][ny] = time[x][y] + 1;
                q.add(new int[] { nx, ny });
            }
        }
        return -1;
    }
}
