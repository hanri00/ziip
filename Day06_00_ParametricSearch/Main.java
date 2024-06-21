import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static boolean test(int value, int N, int arr[]) {
		// value를 받았을 때, 답으로 가능한지 여부
		// value만큼 지자체들에게 예산을 편성
		int sum = 0;
		for (int i = 0; i < arr.length; i++) { // => 10만번
			if (arr[i] < value) // 상한선보다 적게 요구 : 요구한만큼
				sum += arr[i];
			else // 상한선보다 더 크게 요구 : 상한선만큼
				sum += value;
		}
		if (sum > N)
			return false;
		else
			return true;
	}

	static int ps(int N, int arr[]) {
		// 1. 답으로 가능한 범위
		int left = 0;
		int right = 1000000000;
		int ans = 0;
		while (left <= right) { // log(10억) => 30번
			// 2. 답을 가정
			int mid = (left + right) / 2;
			// 3. 검증
			if (test(mid, N, arr)) {
				// 범위 조정 <- 가능하면 돈을 더 주면 좋음
				left = mid + 1;
				ans = mid; // mid까진 답으로 가능
			} else {
				right = mid - 1;
			}
		}
		return ans;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		int N = Integer.parseInt(br.readLine()); // 국고
		int M = Integer.parseInt(br.readLine()); // 지자체 개수
		int arr[] = new int[M]; // 지자체가 요구한 예산
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		System.out.println(ps(N, arr));

	}

}