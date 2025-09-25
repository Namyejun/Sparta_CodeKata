// 카드를 사용하지 않고 다음 카드로 넘어갈 수 없습니다. 없대.

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        int l = 0;
        int r = 0;
        for (int i = 0; i < goal.length; i++) {
            if (l < cards1.length && goal[i].equals(cards1[l])) {
                l++;
            } else if (r < cards2.length && goal[i].equals(cards2[r])) {
                r++;
            } else {
                return "No";
            }
        }
        return "Yes";
    }
}