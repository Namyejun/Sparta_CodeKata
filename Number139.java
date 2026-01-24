public class Solution {
  public long solution(int cap, int n, int[] deliveries, int[] pickups) {
    long answer = 0;
    int idx = n - 1;
    int deliveryCapacity = 0;
    int pickupCapacity = 0;

    for (int i = idx; i >= 0; i--) {
      if (deliveries[i] != 0 || pickups[i] != 0) {
        int count = 0;

        while (deliveryCapacity < deliveries[i] || pickupCapacity < pickups[i]) {
          deliveryCapacity += cap; // 몇 번 왔다갔다 하는지의 문제랄까요
          pickupCapacity += cap;
          count += 1;
        }

        deliveryCapacity -= deliveries[i]; // 왔다갔다 5번 한다고 했을 때 해당 지역에서 픽업하고 배달할 애들은 빼야하죠
        pickupCapacity -= pickups[i];

        answer += ((i + 1) * count * 2); // 이동 거리
      }
    }
    return answer;
  }
}