class Solution {
    public String solution(String s, String skip, int index) {
        StringBuilder sb = new StringBuilder("");
        
        for (char c : s.toCharArray()) {
            int cnt = 0;
            while (cnt < index) {
                if (c == 'z') c = 'a';
                else c++;

                if (!skip.contains(String.valueOf(c))) {
                    cnt++;
                }
            }
            sb.append(c);
        }
        
        return sb.toString();
    }
}