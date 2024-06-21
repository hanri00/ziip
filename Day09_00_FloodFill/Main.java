import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static StringTokenizer st;

	static class Coord {
		int row;
		int col;

		public Coord(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}

	}

	static int MAP[][];
	static int visited[][];

	static int dirRow[] = { -1, 0, 1, 0 };
	static int dirCol[] = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());

		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		
		MAP = new int[5][5];
		visited = new int[5][5];

		Coord start = new Coord(row, col);

		Queue<Coord> q = new LinkedList<Main.Coord>();
		q.add(start);
		visited[start.row][start.col] = 1;

		while (!q.isEmpty()) {

			Coord now = q.remove();

			for (int i = 0; i < 4; i++) {
				int nextRow = now.row + dirRow[i];
				int nextCol = now.col + dirCol[i];

				if (nextRow < 0 || nextCol < 0 || nextRow >= 5 || nextCol >= 5)
					continue;

				if (visited[nextRow][nextCol] != 0)
					continue;

				visited[nextRow][nextCol] = visited[now.row][now.col] + 1;
				q.add(new Coord(nextRow, nextCol));
			}

		}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(visited[i][j] + " ");
			}
			System.out.println();
		}

	}

}
