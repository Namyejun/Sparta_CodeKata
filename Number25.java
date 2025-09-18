class Solution {
    public int solution(int n) {
        for (int i = 1; i < n; i++) {
            if (n % i == 1) return i;
        }
        return -1;
    }
}
/*
 * 솔직히 -1 해서 가장 작은 약수 구하면 된다고 생각하긴 하는데 뭐 크기도 작으니
 */