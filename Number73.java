class Solution {
    public int[] solution(String[] park, String[] routes) {
        int[] start = findS(park);
        System.out.printf("x: %d, y: %d\n", start[0], start[1]);
        for (String route : routes) {
            start = move(park, start, route);
            System.out.printf("x: %d, y: %d\n", start[0], start[1]);
        }
        return start;
    }

    public int[] findS(String[] park) {
        for (int i = 0; i < park.length; i++) {
            for (int j = 0; j < park[0].length(); j++) {
                if (park[i].charAt(j) == 'S') return new int[] {i, j};
            }
        }
        return new int[] {-1, -1};
    }

    public int[] move(String[] park, int[] start, String route) {
        String[] call = route.split(" ");
        int x = start[0], y = start[1];
        int dist = Integer.parseInt(call[1]);
        
        if (call[0].equals("E")) {
            for (int i = 1; i < dist + 1; i++) {
                if (y + i >= park[0].length() || park[x].charAt(y + i) == 'X') return new int[] {x, y};
            }
            return new int[] {x, y + dist};
        } else if (call[0].equals("W")) {
            for (int i = 1; i < dist + 1; i++) {
                if (y - i < 0 || park[x].charAt(y - i) == 'X') return new int[] {x, y};
            }
            return new int[] {x, y - dist};
        } else if (call[0].equals("S")) {
            for (int i = 1; i < dist + 1; i++) {
                if (x + i >= park.length || park[x + i].charAt(y) == 'X') return new int[] {x, y};
            }
            return new int[] {x + dist, y};
        } else if (call[0].equals("N")) {
            for (int i = 1; i < dist + 1; i++) {
                if (x - i < 0 || park[x - i].charAt(y) == 'X') return new int[] {x, y};
            }
            return new int[] {x - dist, y};
        }
        return new int[] {x, y};
    }
}