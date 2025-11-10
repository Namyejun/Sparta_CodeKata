import java.util.Arrays;

class Solution {
    public String solution(int[] numbers) {
        String[] nums = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            nums[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(nums, (a, b) -> (b + a).compareTo(a + b));
        String result = String.join("", nums);
        if (result.charAt(0) == '0') return "0";
        return result;
    }
}