class Solution { // 일단 5진법으로 변경했을 때 2가 들어가는 인덱스는 0임.

  public long solution(int n, long l, long r) {
    return count(n, r) - count(n, l - 1);
  }

  private long count(int n, long x) {
    if (x <= 0)
      return 0;
    if (n == 0)
      return 1; // "1"

    long len = pow(5, n);
    long block = len / 5;
    long onesPerBlock = pow(4, n - 1);

    long result = 0;

    for (int i = 0; i < 5; i++) {
      long start = i * block + 1;
      long end = (i + 1) * block;

      if (x < start)
        break;

      if (i == 2)
        continue; // 가운데는 항상 0

      if (x >= end) {
        result += onesPerBlock;
      } else {
        result += count(n - 1, x - i * block);
        break;
      }
    }

    return result;
  }

  private long pow(long base, int exp) {
    long result = 1;
    while (exp-- > 0)
      result *= base;
    return result;
  }
}
