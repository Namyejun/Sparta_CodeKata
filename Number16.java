class Solution {
    public boolean solution(String s) {
        if (!(s.length() == 4 || s.length() == 6)) return false;
        for (int i = 0; i < s.length(); i++) {
            if (!((int) s.charAt(i) >= (int) '0' && (int) s.charAt(i) <= (int) '9')) return false;
        }
        return true;
    }
}