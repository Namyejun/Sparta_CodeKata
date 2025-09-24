class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;
        answer += getCoke(a, b, n);
        return answer;
    }

    public int getCoke(int a, int b, int n) { // a 개의 빈 병 당, 교환해주는 b 개의 병, 현재 갖고 있는 n 개의 빈 병, 현재 갖고 있는 k 개의 꽉 찬 병
        if (n < a) return 0;
        // (b * (n / a)) : 당장 가지고 있는 병들로 만들 수 있는 꽉 찬 병
        // (n % a) : 마트에서 바꾸고 난 이후 가지고 있는 빈 병(나머지)
        else return (b * (n / a)) + getCoke(a, b, (n % a) + b * (n / a)); 
    }
}

// class Solution {
//     public int solution(int a, int b, int n) {
//         int answer = 0;

//         while (n >= a) {
//             System.out.println(n);
//             int newCoke = b * (n / a);
//             answer += newCoke;
//             int remainCoke = (n % a);
//             n = newCoke + remainCoke;
//         }
//         return answer;
//     }
// }