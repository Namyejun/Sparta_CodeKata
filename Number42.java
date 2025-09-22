import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int solution(int[] number) {
        int answer = 0;
        List<int[]> lst = getCombinations(number, 3);
        for (int[] arr : lst) {
            if (Arrays.stream(arr).sum() == 0) {
                answer += 1;
            }
        }
        return answer;
    }

    public static List<int[]> getCombinations(int[] arr, int r) {
        List<int[]> combinations = new ArrayList<>();
        makeCombinations(arr, new int[r], 0, 0, r, combinations);
        return combinations;
    }

    // 조합 생성 메서드
    public static void makeCombinations(int[] arr, int[] selectArr, int startIdx, int pickCnt, int r, List<int[]> combinations) {
        if (pickCnt == r) {
            combinations.add(selectArr.clone()); // 조합을 리스트에 저장
            return;
        }
        for (int i = startIdx; i < arr.length; i++) {
            selectArr[pickCnt] = arr[i];
            makeCombinations(arr, selectArr, i + 1, pickCnt + 1, r, combinations); // 다음 원소 선택
        }
    }
}