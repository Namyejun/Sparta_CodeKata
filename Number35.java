import java.util.Arrays;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        int count = 0;
        int[] temp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % divisor == 0) {
                count++;
                temp[i] = arr[i];
            }
        }
        Arrays.sort(temp);
        int[] answer = Arrays.copyOfRange(temp, temp.length - count, temp.length);

        if (answer.length == 0) {
            return new int[] {-1};
        }
        return answer;
    }
}