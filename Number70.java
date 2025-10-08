class Solution {
    public int[] solution(String[] wallpaper) {
        int minx = 100, miny = 100, maxx = -1, maxy = -1;

        for (int i = 0; i < wallpaper.length; i++) {
            for (int j = 0; j <wallpaper[0].length(); j++) {
                if (wallpaper[i].charAt(j) == '#') {
                    if (i < minx) minx = i;
                    if (j < miny) miny = j;
                    if (i > maxx) maxx = i;
                    if (j > maxy) maxy = j;
                }
            }
        }

        return new int[] {minx, miny, maxx + 1, maxy + 1};
    }
}