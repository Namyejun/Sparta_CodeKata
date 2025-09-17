class Solution {
    public long solution(long price, long money, long count) {
        long answer = money - price * (count * (1 + count) / 2);
        System.out.println(answer);
        if (answer < 0) return -answer;
        else return 0;
    }
}