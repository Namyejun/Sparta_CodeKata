class Solution {
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < wires.length; i++) {
            UnionFind uf = new UnionFind(n);

            for (int j = 0; j < wires.length; j++) {
                if (i == j)
                    continue;
                uf.union(wires[j][0], wires[j][1]);
            }

            int count = 0;
            int root = uf.find(wires[i][0]);

            for (int k = 1; k <= n; k++) {
                if (uf.find(k) == root)
                    count++;
            }

            int diff = Math.abs((n - count) - count);
            answer = Math.min(answer, diff);
        }

        return answer;
    }

    class UnionFind {
        int[] parent;

        public UnionFind(int n) {
            parent = new int[n + 1];
            for (int i = 1; i <= n; i++)
                parent[i] = i;
        }

        public int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union(int a, int b) {
            int pa = find(a);
            int pb = find(b);
            if (pa != pb)
                parent[pb] = pa;
        }
    }
}