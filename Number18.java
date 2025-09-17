class Solution {
    public int[] solution(int n, int m) {
        int gcd = gcd(n, m);
        int lcm = lcm(n, m, gcd);
        return new int[]{gcd, lcm};
    }

    public int gcd(int n, int m) {
        int a = n > m ? n : m;
        int b = n < m ? n : m;
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    public int lcm(int n, int m, int gcd) {
        return (n * m) / gcd;
    }
}