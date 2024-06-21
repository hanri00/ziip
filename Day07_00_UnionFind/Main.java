import java.io.*;
import java.util.*;

public class Main {

	static int parent[];
	static int groupCnt[];

	private static void Union(int a, int b) {

		int pa = findParent(a);
		int pb = findParent(b);

		if (pa == pb)
			return;

		int minParent = Math.min(pa, pb);
		int maxParent = Math.max(pa, pb);

		parent[maxParent] = minParent;

		groupCnt[minParent] += groupCnt[maxParent];
		groupCnt[maxParent] = 0;

	}

	private static int findParent(int a) {

		if (parent[a] == a)
			return a;

		int root = findParent(parent[a]);
		parent[a] = root;

		return root;
	}

	public static void main(String[] args) {

		parent = new int[6];
		groupCnt = new int[6];
		for (int i = 0; i < 6; i++) {
			parent[i] = i;
			groupCnt[i] = 1;
		}

		Union(1, 2);
		Union(3, 4);
		Union(2, 4);

		if (parent[2] == parent[4]) // 2와 4가 같은 그룹
			System.out.println("같은 그룹");
		else
			System.out.println("다른 그룹");

		if (findParent(2) == findParent(4)) // 2와 4가 같은 그룹
			System.out.println("같은 그룹");
		else
			System.out.println("다른 그룹");

		System.out.println((findParent(2) == findParent(3))); // 2랑 3이랑 같은 그룹? True
		System.out.println((findParent(4) == findParent(5))); // 4랑 5이랑 같은 그룹? False
		System.out.println((findParent(1) == findParent(4))); // 1랑 4이랑 같은 그룹? True

		System.out.println(groupCnt[findParent(4)]); // 4가 속한 group의 인원 수

	}

}
