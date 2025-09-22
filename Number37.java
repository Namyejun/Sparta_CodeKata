class Solution {
    public String solution(String phone_number) {
        int t = phone_number.length() - 4;
        return ("*".repeat(t)) + phone_number.substring(t);
    }
}