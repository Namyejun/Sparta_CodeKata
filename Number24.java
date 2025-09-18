import java.util.ArrayList;
import java.util.List;

class Solution {
    int answer = 0;
    public int solution(int n) {
        getDivisors(n).forEach((i) -> {answer += i;});
        return answer;
    }
    public static List<Integer> getDivisors(int n) {
        List<Integer> lst = new ArrayList<>();
        if (n == 0) return lst;
        else if (n == 1) {
            lst.add(1);
            return lst;
        } else {
            for (int i = 1; i <= n / 2; i++) {
                if (n % i == 0) {
                    lst.add(i);
                }
            }
            lst.add(n);
            return lst;
        }
        
    }
}