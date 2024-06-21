import java.io.*;
import java.util.*;

public class Main {
	
	static class Coord {
		int row;
		int col;

		public Coord(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		// 1. 그래프 구성 <= 계산해서 다음 점을 찾음
		int MAP[][] = { 
				{ 0, 0, 0, 1, 0 }, 
				{ 0, 1, 0, 1, 1 }, 
				{ 0, 1, 0, 0, 0 }, 
				{ 0, 1, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0 } 
		}; // <= 2차원 맵 (0 : 빈칸, 1 : 벽)

		// 2. queue생성
		ArrayDeque<Coord> q = new ArrayDeque<>();
		int visited[][] = new int[5][5]; // <- [row][col] : 해당 좌표를 찾았는가?

		// 3. 시작점 세팅
		st = new StringTokenizer(br.readLine());
		int stRow = Integer.parseInt(st.nextToken());
		int stCol = Integer.parseInt(st.nextToken());
		
		q.addLast(new Coord(stRow, stCol));
		visited[stRow][stCol] = 1;

		// 7. 4~6단계 반복
		while (!q.isEmpty()) {
			// 4. now꺼내기
			Coord now = q.removeFirst();
			
			// 5. now->next
			int dirRow[] = { -1, 1, 0, 0 };
			int dirCol[] = { 0, 0, -1, 1 };
			for (int i = 0; i < 4; i++) {
				int nxtRow = now.row + dirRow[i];
				int nxtCol = now.col + dirCol[i];

				if (nxtRow < 0 || nxtCol < 0 || nxtRow >= 5 || nxtCol >= 5)
					continue;
				if (visited[nxtRow][nxtCol] != 0)
					continue;
				if (MAP[nxtRow][nxtCol] == 1)
					continue; // next가 벽이라 못간다!

				// 6. next를 추가
				q.addLast(new Coord(nxtRow, nxtCol));
				visited[nxtRow][nxtCol] = visited[now.row][now.col] + 1;
			}
		}
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++)
				System.out.print(visited[row][col] + " ");
			System.out.println();
		}
	}

}