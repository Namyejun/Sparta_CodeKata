class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int gcdA = arrayA[0];
        int gcdB = arrayB[0];
        for (int i = 1; i < arrayA.length; i++) {
            gcdA = gcd(gcdA, arrayA[i]);
            gcdB = gcd(gcdB, arrayB[i]);
        }

        for (int i = 0; i < arrayB.length; i++) {
            if (arrayB[i] % gcdA == 0) {
                gcdA = 0;
                break;
            }
        }

        for (int i = 0; i < arrayA.length; i++) {
            if (arrayA[i] % gcdB == 0) {
                gcdB = 0;
                break;
            }
        }

        return Math.max(gcdA, gcdB);
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
}