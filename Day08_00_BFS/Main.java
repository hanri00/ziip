import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		/*
		 * [ 세팅단계 ]
		 * 1. 그래프 준비(data입력) 
		 * 2. Queue 생성 
		 * 3. 시작점 세팅
		 * 
		 * [ 실행 ]
		 * 4. now (queue에서 점 하나 꺼내기) 
		 * 5. now 에서 갈 수 있는 next'들'찾기 
		 * 6. next를 queue에 추가 
		 * 7. 4 ~ 6단계를 반복(queue가 비워질때까지)
		 * 
		 * upgrade 
		 *  - 갔던 점을 '다시'갈 수 있는 경우에 대한 고려 필요 
		 *    => 갔던 점을 다시 가지 말라!!!!!!! 
		 *    => 갔었는가? -> visited[nodeNum] : nodeNum를 들렸는가? 
		 *    => 갔던 점을 다시 가지 않는 구조인지 판단 
		 *       - 1. 트리 구조(방향이 정해져있는 경우, 양방향은 X)
		 *       - 2. 특정 node간의 경로가 유일하다. 
		 * - 몇차 감염인가?
		 * 
		 */
		st = new StringTokenizer(br.readLine());
		int nodeCnt = Integer.parseInt(st.nextToken());
		int edgeCnt = Integer.parseInt(st.nextToken());
		
		// 1. 그래프 준비(data입력)
		ArrayList<Integer> al[] = new ArrayList[nodeCnt + 1];
		for (int i = 1; i <= nodeCnt; i++)
			al[i] = new ArrayList<>();
		
		for (int i = 0; i < edgeCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			al[from].add(to);
		}

		// 2. Queue 생성
		Queue<Integer> q = new LinkedList<>();	// 앞에선 빼기만, 뒤에선 넣기만 (FIFO)
		int visited[] = new int[nodeCnt + 1];	// index: nodeNum, value: 해당 node를 들렸는가?(감염됐는가?)
												// - upgrade: 해당 node를 몇 차만에 갈 수 있는가?

		// 3. 시작점 세팅
		q.add(1); 			// queue에 시작점 넣기
		visited[1] = 1; 	// 1은 감염됐다! => 1은 1차 감염이다.
							// visited의 0은 '아직 감염되지 않았다'

		// 7. 4~6단계 반복
		while (!q.isEmpty()) {
			// 4. now꺼내기
			int now = q.remove();
			System.out.print(now);
			
			// 5. now -> next 찾기
			for (int i = 0; i < al[now].size(); i++) {
				int nxt = al[now].get(i);

				if (visited[nxt] != 0)
					continue; // 이미 감염이 됐던 점이면 무시!!!!

				// 6. next를 queue에 추가
				visited[nxt] = visited[now] + 1; // 감염이 됐다!!!!
				q.add(nxt);
			}
		}
		int de = 1;
	}

}
