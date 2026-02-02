class Solution {

  public int solution(int n, int[][] computers) {
    int answer = 0;
    int[] visited = new int[n];
    for (int i = 0; i < n; i++) {
      if (visited[i] == 0) {
        answer++;
        dfs(n, visited, computers, i);
      }
    }
    return answer;
  }

  public void dfs(int n, int[] visited, int[][] computers, int current) {
    visited[current] = 1;
    for (int i = 0; i < n; i++) {
      if (computers[current][i] == 1 && visited[i] == 0) {
        visited[i] = 1;
        dfs(n, visited, computers, i);
      }
    }
  }
}