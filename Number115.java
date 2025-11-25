
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution {
    public int solution(String[][] book_time) {
        int[][] book_time_int = new int[book_time.length][];
        for (int i = 0; i < book_time.length; i++) {
            String[] startTemp = book_time[i][0].split(":");
            int s = 60 * Integer.parseInt(startTemp[0]) + Integer.parseInt(startTemp[1]);
            String[] endTemp = book_time[i][1].split(":");
            int e = 60 * Integer.parseInt(endTemp[0]) + Integer.parseInt(endTemp[1]) + 10;
            book_time_int[i] = new int[] { s, e };
        }

        Arrays.sort(book_time_int, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        // for (int i = 0; i < book_time_int.length; i++) {
        // System.out.println(book_time_int[i][0] + ", " + book_time_int[i][1]);
        // }

        int answer = 0;
        List<Integer> rooms = new ArrayList<>();
        for (int i = 0; i < book_time_int.length; i++) {
            int[] currentReserve = book_time_int[i];
            boolean flag = false;
            for (int j = 0; j < rooms.size(); j++) {
                if (rooms.get(j) <= currentReserve[0]) {
                    rooms.set(j, currentReserve[1]);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                rooms.add(currentReserve[1]);
            }
            answer = Math.max(rooms.size(), answer);
        }

        return answer;
    }
}