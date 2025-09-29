class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 0;
        for (int i = 1; i <= number; i++) {
            int tmp_weapon = getDivisorCount(i);
            if (tmp_weapon > limit) {
                answer += power;
            } else {
                answer += tmp_weapon;
            }
        }
        return answer;
    }

    public int getDivisorCount(int n) {
        int answer = 1;
        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0) {
                answer++;
            }
        }
        return answer;
    }
}