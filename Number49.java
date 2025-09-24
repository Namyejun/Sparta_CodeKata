import java.util.HashSet;
import java.util.Set;

class Solution {
    public int[] solution(int[] numbers) {
        Set<Integer> temp = new HashSet<>();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                temp.add(numbers[i] + numbers[j]);
            }
        }
        return temp.stream().sorted().mapToInt(o -> (int) o).toArray();
    }
}