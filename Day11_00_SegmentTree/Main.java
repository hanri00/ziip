import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	// Data
	static int vals[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

	// 전처리하여, 구간들의 합을 저장할 Segment Tree
	static int tree[] = new int[40];

	public static void main(String[] args) {

		// start, end, node
		initSegmentTree(0, vals.length - 1, 1);

		// start, end, node, targetLeft, targetRight
		int ret = getSum(0, 9, 1, 0, 4);
		System.out.println(ret);

		// start, end, node, index, updateValue
		update(0, 9, 1, 3, 100);

		ret = getSum(0, 9, 1, 0, 4);
		System.out.println(ret);

	}

	private static int update(int start, int end, int node, int index, int updateValue) {

		// 1.범위가 idx를 포함을 안할때 -> 바로 해당 노드 값 return
		if (index < start || end < index)
			return tree[node];

		// 2.st == ed == idx -> 바꿔주고 return
		if (start == end && end == index) {
			vals[index] = updateValue;
			tree[node] = updateValue;
			return tree[node];
		}

		// 3.반씩 더 나눠보기
		int mid = (start + end) / 2;
		int retL = update(start, mid, 2 * node, index, updateValue);
		int retR = update(mid + 1, end, 2 * node + 1, index, updateValue);
		tree[node] = retL + retR;

		return tree[node];

	}

	private static int getSum(int start, int end, int node, int tarLeft, int tarRight) {

		if (tarRight < start || end < tarLeft)
			return 0;

		if (tarLeft <= start && end <= tarRight)
			return tree[node];

		int mid = (start + end) / 2;
		int retL = getSum(start, mid, 2 * node, tarLeft, tarRight);
		int retR = getSum(mid + 1, end, 2 * node + 1, tarLeft, tarRight);

		return retL + retR;
	}

	private static int initSegmentTree(int start, int end, int node) {

		// 3. 구간의 시작, 끝 같아질때 까지 : 재귀 활용
		if (start == end) {
			// 현재 노드번호에 vals의 원본데이터 저장
			tree[node] = vals[start];
			return tree[node];
		}

		// 1. 구간을 반띵으로 나눠요
		// 2. 각각의 노드에 접근 (왼쪽 -> 현재노드번호 * 2, 오른쪽 -> 현재노드번호 * 2 + 1)
		int mid = (start + end) / 2;
		int retL = initSegmentTree(start, mid, 2 * node);
		int retR = initSegmentTree(mid + 1, end, 2 * node + 1);
		tree[node] = retL + retR;

		return tree[node];

	}

}
