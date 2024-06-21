import java.io.*;
import java.util.*;

public class Main {
	
	// treemap 의 내부 구조
	// treemap 의 key로 비교할 data가 2개 이상
	
	// 일정 관리 <- 사람 여럿명
	// 강의 일정
	// (시작일자, 끝나는 일자, 어떤 사람?)
	static class Schedule implements Comparable<Schedule>{
		int stDay;
		int enDay;
		int who;
		public Schedule(int stDay, int enDay, int who) {
			super();
			this.stDay = stDay;
			this.enDay = enDay;
			this.who = who;
		}
		
		@Override
		// 나랑 내 바로 오른쪽의 애랑 비교
		public int compareTo(Main.Schedule right) {
			// 1. 시작 일자가 빠른 순
			if(stDay < right.stDay) return -1;
			if(stDay > right.stDay) return 1;
			// 2. 끝나는 일자가 빠른 순
			if(enDay < right.enDay) return -1;
			if(enDay > right.enDay) return 1;
			
			// 같다!!!!!
			if(who < right.who) return -1;
			if(who > right.who) return 1;
			return 0;
		}
		// 왠만하면 디버깅할때만 사용하시는걸 권장
		@Override
		public String toString() {
			return "[" + stDay + ", " + enDay + ", " + who + "]";
		}
	}

	// sequence container
	// 배열 <- ArrayList
	// ArrayDeque, LinkedList

	// 한 줄로 data를 관리 X
	// TreeMap<> <- 내부적으로 data를 정렬된 형태로 관리
	// Key, value의 형태로 data를 묶어서 관리
	// key를 기준으로 data를 정렬해서 관리
	// key가 같은게 존재하지 X <- Unique Key

	public static void main(String[] args) {
		TreeMap<Integer, Integer> tm = new TreeMap<>();

		tm.put(1, 2); // {1=2}
		tm.put(5, 3); // {1=2, 5=3}
		tm.put(4, 7); // {1=2, 4=7, 5=3}
		tm.put(3, 9); // {1=2, 3=9, 4=7, 5=3}
		tm.put(8, 4); // {1=2, 3=9, 4=7, 5=3, 8=4}
		tm.put(10, 7); // {1=2, 3=9, 4=7, 5=3, 8=4, 10=7}
		tm.put(21, 9); // {1=2, 3=9, 4=7, 5=3, 8=4, 10=7, 21=9}
		tm.put(7, 4); // {1=2, 3=9, 4=7, 5=3, 7=4, 8=4, 10=7, 21=9}

		tm.put(1, 1); // {1=1, 3=9, 4=7, 5=3, 7=4, 8=4, 10=7, 21=9}

		tm.remove(1); // {3=9, 4=7, 5=3, 7=4, 8=4, 10=7, 21=9}

		tm.containsKey(3); // key가 존재하는가? <- 효율적 O(logN)

		tm.containsValue(3); // value가 존재하는가? <- 비효율적 O(N)

		int value = tm.get(3); // value = 9

		// Map.Entry<Key, Value> : Key와 Value를 묶어놓은 구조
		// TreeMap.entrySet() : treemap을 순회할 수 있는 set구조로 만들어 달라.
		// (TreeMap자체가 바뀌는 것은 아니고 set구조인 것으로 반환)
		for (Map.Entry<Integer, Integer> entry : tm.entrySet()) {
			System.out.println(entry);
			int entryKey = entry.getKey();
			int entryValue = entry.getValue();
			System.out.println("key   : " + entryKey);
			System.out.println("value : " + entryValue);
		}

		// TreeMap.subMap(fromKey, fromInclusive, toKey, toInclusive)
		// fromKey ~ toKey
		// fromInclusive : true => fromKey'이상' / false => fromKey'초과'
		// toInclusive : true => toKey'이하' / false => toKey'미만'
		Map<Integer, Integer> sub = tm.subMap(5, true, 8, true);
		for (Map.Entry<Integer, Integer> entry : sub.entrySet()) {
			System.out.println(sub); // {5=3, 7=4, 8=4}
		}

		// O(logN)
		System.out.println(tm.ceilingEntry(30)); // least key greater than or equal to the given key
		System.out.println(tm.floorEntry(30)); // greatest key less than or equal to the given key
		System.out.println(tm.lowerEntry(30)); // greatest key strictly less than the given key
		System.out.println(tm.higherEntry(30)); // least key strictly greater than the given key

		System.out.println(tm.get(30));

		if (tm.containsKey(30)) {
			int value30 = tm.get(30);
		}

		tm.size();
		tm.isEmpty();

		ArrayList<Integer> al = new ArrayList<>();
		al.add(1);
		al.add(2);
		al.add(3);
		al.add(1);

		for (int i = 0; i < al.size(); i++) {
			System.out.print(al.get(i) + " ");
		}
		System.out.println();

		for (int el : al)
			System.out.print(el + " ");
		
		TreeMap<Schedule, Integer> treeSchedule = new TreeMap<>();
		treeSchedule.put(new Schedule(1, 5, 1), 1);
		treeSchedule.put(new Schedule(6, 10, 2), 2);
		treeSchedule.put(new Schedule(3, 7, 3), 3);
		treeSchedule.put(new Schedule(1, 5, 4), 1);
		
		Schedule a = new Schedule(1, 5, 1);
		System.out.println(a);

	}

}