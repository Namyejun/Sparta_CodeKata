import java.util.Arrays;
import java.util.function.IntPredicate;

class Solution {
    public int[] solution(int[] arr) {
        if (arr.length == 1) return new int[] {-1};
        int minVal = Arrays.stream(arr).min().getAsInt();
        IntPredicate p = x -> x != minVal;
        return Arrays.stream(arr).filter(p).toArray();
    }
}