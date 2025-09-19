import java.util.Arrays;

class Solution {
    public boolean solution(int x) {
        if (x % Arrays.stream(String.valueOf(x).split("")).mapToInt(Integer::parseInt).sum() == 0) {
            return true;
        } return false;
    }
}