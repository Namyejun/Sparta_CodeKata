import java.util.Arrays;

class Solution {
    public static int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for (int t = 0; t < commands.length; t++) {
            int i = commands[t][0], j = commands[t][1], k = commands[t][2];
            int[] tempArray = Arrays.copyOfRange(array, i - 1, j);
            Arrays.sort(tempArray);
            answer[t] = tempArray[k - 1];
        }
        return answer;
    }
}