import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {

		int arr[] = { 1, 2, 3, 7, 10, 19, 25, 31, 31, 31, 31, 99 };
		int num = 31;

		System.out.println(binarySearch(arr, num));

		System.out.println(flooring(arr, num));

		System.out.println(ceiling(arr, num));

		System.out.println(lower(arr, num));

	}

	// arr 에서 num 미만에 해당하는 num 에 가장 가까운 수
	private static int lower(int[] arr, int num) {

		// 1. 범위 생성
		int left = 0;
		int right = arr.length - 1;
		int ret = -1;

		// 4. 반복
		while (left <= right) {
			// 2. 가정
			int mid = (left + right) / 2;
			if (arr[mid] < num) {
				// 3. 범위 옮기기
				left = mid + 1;
				ret = mid; // mid까지는 num미만이라고 확인
			}
			// 5. num과 일치하는 data인 경우 어느쪽으로 움직여야 할지 판단
			else if (arr[mid] >= num) {
				// 3. 범위 옮기기
				right = mid - 1;
			}
		}

		return ret;
	}

	private static int ceiling(int[] arr, int num) {
		// 1. 범위 생성
		int left = 0;
		int right = arr.length - 1;
		int ret = -1;

		// 4. 반복
		while (left <= right) {
			// 2. 가정
			int mid = (left + right) / 2;
			if (arr[mid] < num) {
				// 3. 범위 옮기기
				left = mid + 1;
			} else if (arr[mid] >= num) {
				// 3. 범위 옮기기
				right = mid - 1;
				ret = mid;
			}
		}

		return ret;
	}

	// arr 에서 num 이하에 해당하는 num에 가장 가까운 수
	private static int flooring(int[] arr, int num) {

		// 1. 범위 생성
		int left = 0;
		int right = arr.length - 1;
		int ret = -1;

		// 4. 반복
		while (left <= right) {
			// 2. 가정
			int mid = (left + right) / 2;
			if (arr[mid] <= num) {
				// 3. 범위 옮기기
				left = mid + 1;
				ret = mid;
			} else if (arr[mid] > num) {
				// 3. 범위 옮기기
				right = mid - 1;
			}
		}

		return ret;
	}

	private static int binarySearch(int[] arr, int num) {

		int left = 0;
		int right = arr.length - 1;
		int ans = -1;

		// 정렬을 해서 어떤 N을 기준으로 그 왼쪽은 N보다 작고, 그 오른쪽은 N보다 큰 것이 '보장'
		// 사전에 *정렬* 필수
		Arrays.sort(arr);

		// 범위가 '정상적인 동안'
		while (left <= right) {
			// 1. mid를 가정
			int mid = (left + right) / 2;
			// 2. 가정을 확인
			if (arr[mid] < num) {
				// 원하는 수가 mid보다 큰쪽
				left = mid + 1;
			} else if (arr[mid] > num) {
				right = mid - 1;
			} else {
				ans = mid;
				break;
			}
		}

		return ans;
	}

}
