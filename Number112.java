class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] arr = new int[rows][columns];
        int temp = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                arr[i][j] = temp;
                temp++;
            }
        }
        for (int i = 0; i < queries.length; i++) {
            int t =  rotate(arr, queries[i]);
            answer[i] = t;
        }

        return answer;
    }

    public int rotate(int[][] arr, int[] query) {
        int x1 = query[0] - 1, y1 = query[1] - 1, x2 = query[2] - 1, y2 = query[3] - 1;

        int sx = x1, sy = y1;
        int min = arr[sx][sy];
        int before = arr[sx][sy];
        for (sy = y1 + 1; sy <= y2; sy++) {
            if (min > arr[sx][sy]) min = arr[sx][sy];
            int temp = arr[sx][sy];
            arr[sx][sy] = before;
            before = temp;
        }
        sy--;
        for (sx = x1 + 1; sx <= x2; sx++) {
            if (min > arr[sx][sy]) min = arr[sx][sy];
            int temp = arr[sx][sy];
            arr[sx][sy] = before;
            before = temp;
        }
        sx--;
        for (sy = y2 - 1; sy >= y1; sy--) {
            if (min > arr[sx][sy]) min = arr[sx][sy];
            int temp = arr[sx][sy];
            arr[sx][sy] = before;
            before = temp;
        }
        sy++;
        for (sx = x2 - 1; sx >= x1; sx--) {
            if (min > arr[sx][sy]) min = arr[sx][sy];
            int temp = arr[sx][sy];
            arr[sx][sy] = before;
            before = temp;
        }
        return min;
    }
}