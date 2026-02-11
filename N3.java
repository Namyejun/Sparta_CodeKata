public class N3 {
  public int solution(int p, int index) {
    int answer = -1;
    int k = 1;
    int t = 0;
    while (true) {
      t += k * (Math.pow(p, k) - Math.pow(p, k - 1));
      if (t > index)
        break;
      k++;
    }
    return answer;
  }
}
