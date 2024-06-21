import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());

		int nodeCnt = Integer.parseInt(st.nextToken());
		int edgeCnt = Integer.parseInt(st.nextToken());

		// 인접 행렬
		int arr[][] = new int[nodeCnt + 1][nodeCnt + 1];

		// 인접 리스트
		ArrayList<Integer> al[] = new ArrayList[nodeCnt + 1];
		for (int i = 0; i < nodeCnt + 1; i++) {
			al[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < edgeCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int fromNode = Integer.parseInt(st.nextToken());
			int toNode = Integer.parseInt(st.nextToken());

			arr[fromNode][toNode] = 1;
			arr[toNode][fromNode] = 1;

			al[fromNode].add(toNode);
			al[toNode].add(fromNode);
		}

		// 특정 from에서 갈 수 있는 to'들'
		int fromNode = 1;

		// 인접 행렬
		for (int to = 1; to <= nodeCnt; to++) {
			if (arr[fromNode][to] == 0)
				continue;

			System.out.print(to);
		}
		System.out.println();

		// 인접 리스트
		for (int i = 0; i < al[fromNode].size(); i++) {
			int to = al[fromNode].get(i);
			System.out.print(to);
		}

	}

}
