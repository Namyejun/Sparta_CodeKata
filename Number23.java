import java.util.Arrays;

class Solution {
    public int solution(int n) {
        String[] s = Integer.toString(n).split("");
        return Arrays.stream(s).mapToInt(Integer::parseInt).sum();
    }
}