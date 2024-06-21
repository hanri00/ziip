import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static class Edge implements Comparable<Edge> {
		int to;
		int cost;

		public Edge(int to, int cost) {
			super();
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge right) {

			if (cost < right.cost)
				return -1;
			if (cost > right.cost)
				return 1;

			if (to < right.to)
				return -1;
			if (to > right.to)
				return 1;

			return 0;
		}
	}

	public static void main(String[] args) throws IOException {

		int dist[]; // <- *dijkstra*

		st = new StringTokenizer(br.readLine());
		int nodeCnt = Integer.parseInt(st.nextToken());
		int edgeCnt = Integer.parseInt(st.nextToken());

		dist = new int[nodeCnt + 1];

		ArrayList<Edge> al[] = new ArrayList[nodeCnt + 1];
		for (int i = 1; i <= nodeCnt; i++) {
			al[i] = new ArrayList<>();
			dist[i] = 1234567890; // 10억 : 갈 수 없다. or 아직 모른다.
		}

		for (int i = 0; i < edgeCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			al[from].add(new Edge(to, cost));
			al[to].add(new Edge(from, cost)); // 양방향
		}

		boolean selected[] = new boolean[nodeCnt + 1]; // 확정 여부

		// BFS : Queue
		// dijkstra : PriorityQueue
		PriorityQueue<Edge> pq = new PriorityQueue<>(); // 우선순위 : 거리, 필요한 정보 : 어느 점?

		// 1. 시작점 세팅
		dist[1] = 0;
		pq.add(new Edge(1, 0));

		while (!pq.isEmpty()) {
			// 2. 아직 확정되지 않은 점들 '중' 제일 짧은 거리 <- 우리가 알게된 새로운 거리들 '중' 제일 짧은 거리
			Edge now = pq.remove();

			// dijkstra : 확정한걸 다시 확정하지 말라!!!!
			if (selected[now.to])
				continue; // 이미 확정해서 계산했던 점이면 무시!!!!!
			selected[now.to] = true; // 확정

			// 3. 확정된 점을 기반으로 거리 갱신
			for (int i = 0; i < al[now.to].size(); i++) {
				Edge nxt = al[now.to].get(i);

				if (dist[nxt.to] <= dist[now.to] + nxt.cost)
					continue; // now를 통해가는게 더 좋은게 아니라면 무시

				// BFS : 찾았을때 다시 찾지 말라! visited<-

				dist[nxt.to] = dist[now.to] + nxt.cost;
				pq.add(new Edge(nxt.to, dist[nxt.to]));
			}
		}

		// E : edge개수
		// V : node개수
		// ElogV == ElogE

		int de = 1;
	
		/*
		for(int j = 0; j < nodeCnt - 1; j++) {
			// 2. 아직 확정되지 않은 점들 '중' 제일 짧은 거리 <- 우리가 알게된 새로운 거리들 '중' 제일 짧은 거리
			int minDist = 1234567890;
			int now = -1;
			for(int i = 1; i <= nodeCnt; i++) {
				if(minDist > dist[i] && !selected[i]) {
					minDist = dist[i];
					now = i;
				}
			}
			
			selected[now] = true; // 확정
			
			// 3. 확정된 점을 기반으로 거리 갱신
			for(int i = 0; i <al[now].size(); i++) {
				Edge nxt = al[now].get(i);
				
				if(dist[nxt.to] <= dist[now] + nxt.cost) continue; // now를 통해가는게 더 좋은게 아니라면 무시
				
				dist[nxt.to] = dist[now] + nxt.cost;
			}
		}
		*/

	}

}
