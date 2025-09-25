class Solution {
    public String solution(int a, int b) {
        int diff = -1;
        int[] month = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] days = {"FRI", "SAT", "SUN", "MON", "TUE", "WED", "THU"};
        for (int i = 1; i < a; i++) {
            diff += month[i];
        }
        diff += b;
        
        return days[diff % 7];
    }
}