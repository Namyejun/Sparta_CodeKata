import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[] solution(int[] answers) {
        int[] first = {1, 2, 3, 4, 5};
        int[] second = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] third = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int[] lst = {0, 0, 0};
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == first[i % 5]) {
                lst[0] += 1;
            }
            if (answers[i] == second[i % 8]) {
                lst[1] += 1;
            }
            if (answers[i] == third[i % 10]) {
                lst[2] += 1;
            }
        }
        List<Integer> temp = new ArrayList<>();
        int winner = Arrays.stream(lst).max().getAsInt();
        for (int i = 1; i <= 3; i++) {
            if (lst[i - 1] == winner) {
                temp.add(i);
            }
        }
        return temp.stream().mapToInt(i -> i).toArray();
    }
}