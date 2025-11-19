
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public int[] solution(String[] maps) {
        int yLen = maps.length;
        int xLen = maps[0].length();

        int[][] move = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        boolean[][] visited = new boolean[yLen][xLen];
        List<Integer> list = new ArrayList<>();
        
        for (int y = 0; y < yLen; y++) {
            for (int x = 0; x < xLen; x++) {
                if (visited[y][x]) continue;
                if (maps[y].charAt(x) == 'X') continue;

                int[] first = new int[]{x,y};
                visited[y][x] = true;
                Queue<int[]> que = new LinkedList<>();
                que.offer(first);
                int sum = Integer.parseInt(String.valueOf(maps[y].charAt(x)));
                while(!que.isEmpty()){
                    int[] now = que.poll();
                    int nx = now[0];
                    int ny = now[1];
                    for(int i=0;i<4;i++){
                        int dx = nx+move[i][0];
                        int dy = ny+move[i][1];
                        if(dx<0 || dy<0 || dx > xLen - 1 || dy > yLen - 1) continue;
                        if(visited[dy][dx]) continue;
                        if(maps[dy].charAt(dx) == 'X') continue;
                        visited[dy][dx] = true;
                        sum+=Integer.parseInt(String.valueOf(maps[dy].charAt(dx)));
                        que.offer(new int[]{dx,dy});
                        
                    }
                }
                list.add(sum);
            }
        }
        if(list.isEmpty()) list.add(-1);
        Collections.sort(list);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}