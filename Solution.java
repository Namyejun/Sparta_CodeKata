import java.util.*;

class Solution {

    public int solution1(int h, int w, int[] blocks) {
        int k = blocks.length;
        int INF = 1_000_000_000;

        int[][] dp = new int[1 << k][k];
        for (int i = 0; i < (1 << k); i++) {
            Arrays.fill(dp[i], INF);
        }

        // 시작: blocks[0] = 1
        dp[1][0] = 0;

        for (int mask = 0; mask < (1 << k); mask++) {
            // 시작점 포함 안된 상태는 의미 없음
            if ((mask & 1) == 0) continue;

            for (int i = 0; i < k; i++) {
                if ((mask & (1 << i)) == 0) continue;
                if (dp[mask][i] == INF) continue;

                for (int j = 0; j < k; j++) {
                    if ((mask & (1 << j)) != 0) continue;

                    int nextMask = mask | (1 << j);
                    int dist = getDist(blocks[i], blocks[j], w);

                    dp[nextMask][j] = Math.min(
                        dp[nextMask][j],
                        dp[mask][i] + dist
                    );
                }
            }
        }

        int ans = INF;
        int fullMask = (1 << k) - 1;

        for (int i = 0; i < k; i++) {
            ans = Math.min(ans, dp[fullMask][i]);
        }

        return ans;
    }

    // 맨해튼 거리
    private int getDist(int a, int b, int w) {
        int r1 = (a - 1) / w;
        int c1 = (a - 1) % w;

        int r2 = (b - 1) / w;
        int c2 = (b - 1) % w;

        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }


    public int[] solution2ByGpt(int[] monsters) {
        int n = monsters.length;

        List<Integer> pos = new ArrayList<>();
        List<Integer> neg = new ArrayList<>();

        for (int m : monsters) {
            if (m >= 0) pos.add(m);
            else neg.add(m);
        }

        // 양수 큰 순
        pos.sort((a, b) -> b - a);
        // 음수 절댓값 작은 순
        neg.sort(Comparator.comparingInt(Math::abs));

        // 합치기
        List<Integer> list = new ArrayList<>();
        list.addAll(pos);
        list.addAll(neg);

        int[] answer = new int[n];

        for (int k = 1; k <= n; k++) {
            int need = 1; // 최소 시작 체력

            for (int i = k - 1; i >= 0; i--) {
                int m = list.get(i);
                need = Math.max(Math.abs(m) + 1, need - m);
            }

            answer[k - 1] = need;
        }

        return answer;
    }



    public long[] solution3(int[] monsters) {
        int n = monsters.length;
        long[] answer = new long[n];

        // 1. 최적의 사냥 순서를 결정하기 위해 양수와 음수를 분리
        List<Integer> positives = new ArrayList<>();
        List<Integer> negatives = new ArrayList<>();

        for (int m : monsters) {
            if (m >= 0) {
                positives.add(m);
            } else {
                negatives.add(m);
            }
        }

        // 2. 사냥 효율 극대화 정렬
        // 양수: 입장 컷(|m|+1)이 낮은 순서대로 잡아서 체력을 미리 불려야 함
        positives.sort(Comparator.comparingInt(Math::abs));
        // 음수: 피해가 적고 입장 컷이 낮은 순서대로 나중에 처리
        negatives.sort(Comparator.comparingInt(Math::abs));

        // 최적의 시퀀스: [약한 양수 ... 강한 양수, 약한 음수 ... 강한 음수]
        List<Integer> bestOrder = new ArrayList<>();
        bestOrder.addAll(positives);
        bestOrder.addAll(negatives);

        // 3. 1마리부터 n마리까지 각각의 최소 체력을 계산
        for (int k = 1; k <= n; k++) {
            answer[k - 1] = getMinInitialHealth(bestOrder.subList(0, k));
        }

        return answer;
    }

    /**
     * 주어진 몬스터 리스트를 순서대로 잡기 위해 필요한 최소 초기 체력을 역산합니다.
     */
    private long getMinInitialHealth(List<Integer> targets) {
        // 모든 전투가 끝난 후 최소 체력은 1이 남아야 함
        long required = 1;

        // 리스트의 마지막 몬스터부터 거꾸로 계산하며 올라옴
        for (int i = targets.size() - 1; i >= 0; i--) {
            int m = targets.get(i);
            
            /*
             * m을 처리하기 전 필요한 체력(current) 결정 조건:
             * 1. m을 처리한 후 결과가 required가 되어야 함: current + m = required => current = required - m
             * 2. m을 처리하기 위한 최소 입장 조건: current >= |m| + 1
             */
            long entryBarrier = Math.abs((long)m) + 1;
            long afterCombatRequirement = required - m;

            // 두 조건 중 더 큰 값을 선택하여 '이 단계 이전에 반드시 필요한 체력'으로 갱신
            required = Math.max(entryBarrier, afterCombatRequirement);
        }
        
        return required;
    }


    public long[] solution4(int[] monsters) {
        int n = monsters.length;
        long[] answer = new long[n];
        
        for (int k = 1; k <= n; k++) {
            long low = 1;
            long high = 2000000000L; // 넉넉한 최대치
            long minH = high;
            
            while (low <= high) {
                long mid = low + (high - low) / 2;
                if (canCatch(mid, k, monsters)) {
                    minH = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            answer[k - 1] = minH;
        }
        return answer;
    }

    private boolean canCatch(long h, int k, int[] monsters) {
        PriorityQueue<Integer> pos = new PriorityQueue<>(Comparator.comparingInt(Math::abs));
        PriorityQueue<Integer> neg = new PriorityQueue<>(Comparator.comparingInt(Math::abs));
        
        for (int m : monsters) {
            if (m >= 0) pos.add(m);
            else neg.add(m);
        }

        int count = 0;
        long currentH = h;
        boolean progress = true;

        while (count < k && progress) {
            progress = false;
            // 1. 잡을 수 있는 양수는 다 잡기 (체력 뻥튀기)
            while (!pos.isEmpty() && currentH > Math.abs(pos.peek())) {
                currentH += pos.poll();
                count++;
                progress = true;
                if (count == k) return true;
            }
            // 2. 더 이상 양수를 못 잡으면, 가장 약한 음수 하나 잡기
            if (count < k && !neg.isEmpty() && currentH > Math.abs(neg.peek())) {
                currentH += neg.poll();
                count++;
                progress = true;
            }
        }
        return count >= k;
    }


    public long[] solution5ByGpt(int[] monsters) {
        int n = monsters.length;
        long[] answer = new long[n];

        for (int k = 1; k <= n; k++) {
            long left = 1;
            long right = (long) 2e9;
            long result = right;

            while (left <= right) {
                long mid = (left + right) / 2;

                if (can(mid, k, monsters)) {
                    result = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            answer[k - 1] = result;
        }

        return answer;
    }

    private boolean can(long H, int k, int[] monsters) {

        PriorityQueue<Integer> pos = new PriorityQueue<>();
        PriorityQueue<Integer> neg = new PriorityQueue<>(Comparator.comparingInt(Math::abs));

        for (int m : monsters) {
            if (m >= 0) pos.add(m);
            else neg.add(m);
        }

        long cur = H;
        int cnt = 0;

        boolean progress = true;

        while (cnt < k && progress) {
            progress = false;

            // 1️⃣ 잡을 수 있는 양수 다 먹기
            while (!pos.isEmpty() && cur > Math.abs(pos.peek())) {
                cur += pos.poll();
                cnt++;
                progress = true;
                if (cnt == k) return true;
            }

            // 2️⃣ 음수 하나 먹기 (가장 안전한 것)
            if (!neg.isEmpty() && cur > Math.abs(neg.peek())) {
                cur += neg.poll();
                cnt++;
                progress = true;
            }
        }

        return cnt >= k;
    }
}