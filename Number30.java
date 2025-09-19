// class Solution {
//     public long solution(long n) {
//         String tempAnswer = "";
//         String stringN = String.valueOf(n);
//         int arr[] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

//         for (int i = 0; i < stringN.length(); i++) {
//             arr[stringN.charAt(i) - '0'] += 1;
//         }

//         for (int i = arr.length - 1; i >= 0; i--) {
//             tempAnswer += String.valueOf(i).repeat(arr[i]); 
//         }

//         return Long.parseLong(tempAnswer);
//     }
// }

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public long solution(long n) {
        String[] t = String.valueOf(n).split("");
        Arrays.sort(t, Comparator.reverseOrder());
        return Long.parseLong(String.join("", t));
    }
}