class Solution {
    public int solution(int[] absolutes, boolean[] signs) {
        int answer = 0;
        for (int i = 0; i < absolutes.length; i++) {
            answer += (signs[i]) ? absolutes[i] : -absolutes[i];
        }
        return answer;
    }
}
/*
// 이건 그냥 내가 보고 싶어서 올린 것

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

class Solution {
    public int solution(int[] absolutes, boolean[] signs) {
            AtomicInteger index = new AtomicInteger();
            return Arrays.stream(absolutes).reduce(0, (i, i1) -> {
                index.getAndIncrement();
                return signs[index.get() - 1] ? i + i1 : i - i1;
            });
        }
}
 */