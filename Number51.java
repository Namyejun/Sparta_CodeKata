// class Solution {
//     public String solution(int[] food) {
//         String answer = "0";
//         for (int i = food.length - 1; i >= 1; i--) {
//             answer = String.valueOf(i).repeat(food[i] / 2) + answer + String.valueOf(i).repeat(food[i] / 2);
//         }
//         return answer;
//     }
// }
// StringBuilder 때문에 그런가?

class Solution {
    public String solution(int[] food) {
        String answer = "";
        for (int i = 1; i < food.length; i++) {
            answer += String.valueOf(i).repeat(food[i] / 2);
        }
        StringBuilder sb = new StringBuilder(answer);
        String back = sb.reverse().toString();
        
        return answer + '0' + back;
    }
}